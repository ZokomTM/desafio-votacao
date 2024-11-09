package com.br.elton.teste_pratico_votacao.votacao.repository;

import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByPautaIdAndCpfAssociado(Long pautaId, Long cpfAssociado);
}