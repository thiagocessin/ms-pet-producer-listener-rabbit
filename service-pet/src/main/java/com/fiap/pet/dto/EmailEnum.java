package com.fiap.pet.dto;

public enum EmailEnum {
    EMAIL_PEDIDO("order-email"),
    EMAIL_CADASTRO("register-email");

    private String tipo;

    EmailEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
