package com.br.elton.teste_pratico_votacao.votacao.repository;

import com.br.elton.teste_pratico_votacao.votacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
}