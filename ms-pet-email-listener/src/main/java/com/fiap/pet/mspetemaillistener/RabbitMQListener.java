package com.fiap.pet.mspetemaillistener;

import com.fiap.pet.mspetemaillistener.dto.EmailEnum;
import com.fiap.pet.mspetemaillistener.dto.RabbitListenerEmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQListener {

    @Autowired
    private SendgridSender sgs;


    @RabbitListener(queues="${rabbit.queuename}", containerFactory="jsaFactory")
    public void receivedMessage(RabbitListenerEmailDto message) throws IOException {
        sgs.setEmailFrom("gabriel.chimenezleme@gmail.com");
        sgs.setEmailTo(message.getEmailCliente());

        if(message.getTipoEmail().equals(EmailEnum.EMAIL_PEDIDO)) {
            sgs.setSubject("Email de pedido!!");
            sgs.setContent("Você fez um pedido!!");
        }
        else if(message.getTipoEmail().equals(EmailEnum.EMAIL_CADASTRO)) {
            sgs.setSubject("Email de cadastro!!");
            sgs.setContent("Você se cadastrou!!");
        }

        try {
            sgs.sendEmail();
        } catch (Exception e) {
            throw e;
        }
    }
}
