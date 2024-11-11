package com.br.elton.teste_pratico_votacao.votacao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe genérica de resposta para a API, encapsulando dados de resposta,
 * uma mensagem informativa e o status de sucesso ou falha da operação.
 *
 * @param <T> o tipo de dado que será retornado na resposta
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    /**
     * Dados retornados na resposta da API.
     */
    private T data;

    /**
     * Mensagem informativa sobre o resultado da operação.
     */
    private String message;

    /**
     * Indica se a operação foi bem-sucedida (true) ou falhou (false).
     */
    private boolean success;
}
