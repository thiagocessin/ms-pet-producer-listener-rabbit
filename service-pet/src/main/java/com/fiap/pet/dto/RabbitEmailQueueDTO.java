package com.fiap.pet.dto;


public class RabbitEmailQueueDTO {

    private EmailEnum tipoEmail;
    private String emailCliente;

    public RabbitEmailQueueDTO() {}

    public RabbitEmailQueueDTO(EmailEnum tipoEmail, String emailCliente) {
        this.tipoEmail = tipoEmail;
        this.emailCliente = emailCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public EmailEnum getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(EmailEnum tipoEmail) {
        this.tipoEmail = tipoEmail;
    }
}
