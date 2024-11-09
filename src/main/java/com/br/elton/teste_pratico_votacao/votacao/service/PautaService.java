package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.repository.PautaRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public ApiResponse<Pauta> salvar(Pauta pauta) {
        return new ApiResponse<>(pautaRepository.save(pauta), "Pauta registrada.", true);
    }

    public ApiResponse<List<Pauta>> listar() {
        return new ApiResponse<>(pautaRepository.findAll(), "Todos registros encontrados.", true);
    }

    public ApiResponse<Pauta> buscarPorId(Long id) {
        return new ApiResponse<>(pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada.")), "Pauta solicitada.", true);
    }

    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada."));
    }
}