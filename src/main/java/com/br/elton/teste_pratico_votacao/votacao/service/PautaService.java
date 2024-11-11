package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.repository.PautaRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service responsável pelo gerenciamento das operações relacionadas à Pauta.
 */
@Service
public class PautaService {


    @Autowired
    private PautaRepository pautaRepository;

    /**
     * Salva uma nova pauta no sistema.
     *
     * @param pauta a pauta a ser registrada
     * @return ApiResponse contendo a pauta salva e uma mensagem de sucesso
     */
    public ApiResponse<Pauta> salvar(Pauta pauta) {
        return new ApiResponse<>(pautaRepository.save(pauta), "Pauta registrada.", true);
    }

    /**
     * Lista todas as pautas registradas no sistema.
     *
     * @return ApiResponse contendo uma lista de todas as pautas e uma mensagem de sucesso
     */
    public ApiResponse<List<Pauta>> listar() {
        return new ApiResponse<>(pautaRepository.findAll(), "Todos registros encontrados.", true);
    }

    /**
     * Busca uma pauta pelo ID especificado.
     *
     * @param id o ID da pauta a ser buscada
     * @return ApiResponse contendo a pauta encontrada e uma mensagem de sucesso
     * @throws RuntimeException se a pauta não for encontrada
     */
    public ApiResponse<Pauta> buscarPorId(Long id) {
        return new ApiResponse<>(pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada.")), "Pauta solicitada.", true);
    }

    /**
     * Busca uma pauta pelo ID especificado sem encapsulamento em ApiResponse.
     * Metodo utilizado apenas em outros services, não tem retorno na Api.
     *
     * @param id o ID da pauta a ser buscada
     * @return a pauta encontrada
     * @throws RuntimeException se a pauta não for encontrada
     */
    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada."));
    }
}
