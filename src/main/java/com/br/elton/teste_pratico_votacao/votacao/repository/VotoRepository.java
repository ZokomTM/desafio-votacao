package com.br.elton.teste_pratico_votacao.votacao.repository;

import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByPautaIdAndCpfAssociado(Long pautaId, String cpfAssociado);

    List<Voto> findAllByPautaId(Long pautaId);
}