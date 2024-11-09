package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.cpf.response.CpfResponse;
import com.br.elton.teste_pratico_votacao.cpf.service.CpfService;
import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import com.br.elton.teste_pratico_votacao.votacao.repository.VotoRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotacaoService {
    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private CpfService cpfService;

    public ApiResponse<Voto> contabilizar(Pauta pauta, String cpfAssociado, Voto.TipoVoto tipoVoto) {

        CpfResponse cpfResponse = cpfService.validateCPF(cpfAssociado);
        if (cpfResponse.getStatusCode() == 404) {
            return new ApiResponse<>(null, "CPF inválido. Não é possível contabilizar o voto.", false);
        }

        if (votoRepository.existsByPautaIdAndCpfAssociado(pauta.getId(), cpfAssociado)) {
            return new ApiResponse<>(null, "Associado já votou nessa pauta", false);
        }

        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setCpfAssociado(cpfAssociado);
        voto.setVoto(tipoVoto);

        return new ApiResponse<>(votoRepository.save(voto), "Voto contabilizado.", true);
    }

    public ApiResponse<List<Voto>> listarPorPauta(Long pautaId) {
        return new ApiResponse<>(votoRepository.findAllByPautaId(pautaId), "Listagem Votos.", true);
    }
}