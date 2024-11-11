package com.br.elton.teste_pratico_votacao.votacao.record;

import com.br.elton.teste_pratico_votacao.votacao.model.Voto;

/**
 * Record para registro de voto, ele tem apenas informações necessárias para registrar um voto,
 * no caso decidi criar um record, pois quero que as informações do voto não seja alterado após chegada.
 *
 * @param cpfAssociado (formato 999.999.999-99)
 * @param voto (SIM ou NAO)
 */
public record RegistrarVoto(String cpfAssociado, Voto.TipoVoto voto) {}
