package com.fiap.pet.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fiap.pet.dto.EmailEnum;
import com.fiap.pet.dto.RabbitEmailQueueDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.pet.domain.Categoria;
import com.fiap.pet.domain.Cliente;
import com.fiap.pet.domain.ItemPedido;
import com.fiap.pet.domain.PagamentoComBoleto;
import com.fiap.pet.domain.Pedido;
import com.fiap.pet.domain.Produto;
import com.fiap.pet.domain.enums.EstadoPagamento;
import com.fiap.pet.repositories.ItemPedidoRepository;
import com.fiap.pet.repositories.PagamentoRepository;
import com.fiap.pet.repositories.PedidoRepository;
import com.fiap.pet.security.UserSS;
import com.fiap.pet.services.exceptions.AuthorizationException;
import com.fiap.pet.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private RabbitTemplate rabbit;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}

	public Pedido save(Pedido pedido) {
		return repo.save(pedido);
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		// usar clienteService inves de clienteRepository
		// usar produto service

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}

		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			Produto produto = produtoService.find(ip.getProduto().getId());
			ip.setProduto(produto);
			ip.setPreco(produto.getPreco());
			ip.setPedido(obj);
		}

		itemPedidoRepository.saveAll(obj.getItens());

		RabbitEmailQueueDTO emailConf = new RabbitEmailQueueDTO(EmailEnum.EMAIL_PEDIDO, obj.getCliente().getEmail());

		rabbit.convertAndSend(emailConf);

		return obj;
	}

	// findPage > atenção a atualização
	// consulta para recuperar página de dados
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		UserSS user = UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		// obj que prepara as informações para que eu faça a conulta que me retorna a
		// página de dados
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Cliente cliente =  clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);

	}

}
