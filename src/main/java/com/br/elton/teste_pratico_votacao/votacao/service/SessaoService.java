package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.Sessao;
import com.br.elton.teste_pratico_votacao.votacao.repository.SessaoVotacaoRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public SessaoService(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    public ApiResponse<Sessao> iniciar(Pauta pauta, int minutos) {
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(minutos));

        sessao = sessaoVotacaoRepository.save(sessao);
        return new ApiResponse<>(sessao, "Sessão iniciada.", true);
    }

    public ApiResponse<Sessao> incrementarTempo(Long id, int minutos) {
        Sessao sessao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sessão com ID " + id + " não encontrada"));

        sessao.setDataFim(sessao.getDataFim().plusMinutes(minutos));
        sessaoVotacaoRepository.save(sessao);

        return new ApiResponse<>(sessao, "Tempo incrementado com sucesso.", true);
    }

    public ApiResponse<List<Sessao>> listar() {
        return new ApiResponse<>(sessaoVotacaoRepository.findAll(), "Todos registros encontrados.", true);
    }
}