package com.br.elton.teste_pratico_votacao.cpf.response;

public class CpfResponse {
    private String message;
    private int statusCode;

    public CpfResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}