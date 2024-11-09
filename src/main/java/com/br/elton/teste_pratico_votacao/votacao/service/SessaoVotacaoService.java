package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.SessaoVotacao;
import com.br.elton.teste_pratico_votacao.votacao.repository.SessaoVotacaoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    public SessaoVotacao abrirSessao(Pauta pauta, int minutos) {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusMinutes(minutos));
        return sessaoVotacaoRepository.save(sessao);
    }
}