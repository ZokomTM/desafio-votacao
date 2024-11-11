package com.br.elton.teste_pratico_votacao.cpf.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe responsável para criar e retornar na API como modelo de resposta
 */
@Getter
@Setter
@AllArgsConstructor
public class CpfResponse {
    private String message;
    private int statusCode;
}