package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.Sessao;
import com.br.elton.teste_pratico_votacao.votacao.repository.SessaoVotacaoRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service responsável pelas operações relacionadas à Sessão de Votação.
 */
@Service
public class SessaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    /**
     * Inicia uma nova sessão de votação para uma pauta específica com duração em minutos.
     *
     * @param pauta a pauta que será votada na sessão
     * @param minutos a duração da sessão em minutos
     * @return ApiResponse contendo a sessão iniciada e uma mensagem de sucesso
     */
    public ApiResponse<Sessao> iniciar(Pauta pauta, int minutos) {
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(minutos));

        sessao = sessaoVotacaoRepository.save(sessao);
        return new ApiResponse<>(sessao, "Sessão iniciada.", true);
    }

    /**
     * Incrementa o tempo de uma sessão de votação específica.
     *
     * @param id o ID da sessão a ser incrementada
     * @param minutos o número de minutos a ser adicionado ao tempo de término
     * @return ApiResponse contendo a sessão atualizada e uma mensagem de sucesso
     * @throws EntityNotFoundException se a sessão não for encontrada pelo ID
     */
    public ApiResponse<Sessao> incrementarTempo(Long id, int minutos) {
        Sessao sessao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessão com ID " + id + " não encontrada"));

        sessao.setDataFim(sessao.getDataFim().plusMinutes(minutos));
        sessaoVotacaoRepository.save(sessao);

        return new ApiResponse<>(sessao, "Tempo incrementado com sucesso.", true);
    }

    /**
     * Lista todas as sessões de votação registradas no sistema.
     *
     * @return ApiResponse contendo uma lista de todas as sessões e uma mensagem de sucesso
     */
    public ApiResponse<List<Sessao>> listar() {
        return new ApiResponse<>(sessaoVotacaoRepository.findAll(), "Todos registros encontrados.", true);
    }
}
