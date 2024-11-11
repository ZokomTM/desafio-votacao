package com.br.elton.teste_pratico_votacao.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service responsável por chamar as Apis de validações de Cpf,
 * criei esse service para separar as chamadas de validação das outras regras de negócios
 */
@Service
public class CpfValidatorService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Contem a URL de chamada da minha Api, no caso como estou rodando localmente deixei localhost, porem pode mudar de acordo
     * do local onde for rodado.
     */
    private static final String CPF_VALIDATION_API_URL = "http://localhost:8080/api/cpf/validate";

    /**
     * Metodo que irá efetuar a chamada para a Api e tratar a resposta recebida.
     *
     * @param cpf (String formato 999.999.999-99)
     * @return True ou False, tudo depende do retorno da Api
     */
    public boolean isCpfValid(String cpf) {
        String url = CPF_VALIDATION_API_URL + "?cpf=" + cpf;

        try {
            Boolean isValid = restTemplate.getForObject(url, Boolean.class);
            return isValid != null && isValid;
        } catch (Exception e) {
            return false;
        }
    }
}
