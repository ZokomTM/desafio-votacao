package com.br.elton.teste_pratico_votacao.votacao.repository;

import com.br.elton.teste_pratico_votacao.votacao.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<Sessao, Long> {

    Sessao findAllByPautaId(Long pautaId);
}