package com.br.elton.teste_pratico_votacao.votacao.repository;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}