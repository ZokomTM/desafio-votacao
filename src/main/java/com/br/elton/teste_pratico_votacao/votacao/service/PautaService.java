package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.repository.PautaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }
}