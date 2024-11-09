package com.br.elton.teste_pratico_votacao.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfValidatorService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CPF_VALIDATION_API_URL = "http://localhost:8080/api/cpf/validate";

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
