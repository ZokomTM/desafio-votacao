package com.br.elton.teste_pratico_votacao.cpf.service;

import com.br.elton.teste_pratico_votacao.cpf.response.CpfResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfService {

    private static final Random random = new Random();

    public CpfResponse validateCPF(String cpf) {
        boolean isValid = isCPFValid(cpf);
        if (!isValid) {
            return new CpfResponse("CPF inválido", 404);
        } else {
            if(randomVotingStatus()){
                return new CpfResponse("ABLE_TO_VOTE", 200);
            }else {
                return new CpfResponse("UNABLE_TO_VOTE", 404);
            }
        }
    }

    private boolean isCPFValid(String cpf) {
        String cleanCpf = cpf.replaceAll("[^0-9]", "");

        return cleanCpf.length() == 11 && cleanCpf.matches("\\d{11}");
    }

    private Boolean randomVotingStatus() {
        return random.nextBoolean();
    }
}
