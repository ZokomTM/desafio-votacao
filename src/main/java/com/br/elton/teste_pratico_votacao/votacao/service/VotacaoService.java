package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import com.br.elton.teste_pratico_votacao.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotacaoService {
    private final VotoRepository votoRepository;

    public VotacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public Voto registrarVoto(Pauta pauta, String cpfAssociado, Voto.TipoVoto tipoVoto) {
        if (votoRepository.existsByPautaIdAndCpfAssociado(pauta.getId(), cpfAssociado)) {
            throw new RuntimeException("Associado já votou nessa pauta");
        }

        // validar CPF numa api mockada

        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setCpfAssociado(cpfAssociado);
        voto.setVoto(tipoVoto);
        return votoRepository.save(voto);
    }
}