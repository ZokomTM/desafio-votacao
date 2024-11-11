package com.br.elton.teste_pratico_votacao.cpf.service;

import com.br.elton.teste_pratico_votacao.cpf.response.CpfResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service responsável apenas para validar o CPF e aplicar as devidas regras.
 */
@Service
public class CpfService {

    /**
     * Instância de Random para simular aleatoriamente se o CPF estará liberado ou não.
     */
    private static final Random random = new Random();

    /**
     * Método responsável por validar as regras do CPF e construir a resposta final para a API.
     *
     * @param cpf CPF no formato String a ser validado.
     * @return Objeto CpfResponse com a mensagem de status do CPF e o código HTTP correto.
     */
    public CpfResponse validateCPF(String cpf) {
        boolean isValid = isCPFValid(cpf);

        if (!isValid) {
            return new CpfResponse("CPF inválido", 404);
        } else {
            if (randomVotingStatus()) {
                return new CpfResponse("ABLE_TO_VOTE", 200);
            } else {
                return new CpfResponse("UNABLE_TO_VOTE", 404);
            }
        }
    }

    /**
     * Método auxiliar para validar o CPF fornecido.
     *
     * @param cpf CPF no formato String a ser validado.
     * @return true se o CPF é válido (11 dígitos numéricos), caso contrário, false.
     */
    private boolean isCPFValid(String cpf) {
        String cleanCpf = cpf.replaceAll("[^0-9]", "");
        return cleanCpf.length() == 11 && cleanCpf.matches("\\d{11}");
    }

    /**
     * Método auxiliar que gera um status de votação aleatório.
     *
     * @return true se o titular está apto a votar, false caso contrário.
     */
    private Boolean randomVotingStatus() {
        return random.nextBoolean();
    }
}
