package com.fiap.pet.mspetemaillistener.dto;

public class RabbitListenerEmailDto {

    private EmailEnum tipoEmail;
    private String emailCliente;


    public RabbitListenerEmailDto() {}

    public RabbitListenerEmailDto(EmailEnum tipoEmail, String emailCliente) {
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
