package com.br.elton.teste_pratico_votacao.votacao.service;

import com.br.elton.teste_pratico_votacao.cpf.response.CpfResponse;
import com.br.elton.teste_pratico_votacao.cpf.service.CpfService;
import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.model.Sessao;
import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import com.br.elton.teste_pratico_votacao.votacao.repository.SessaoVotacaoRepository;
import com.br.elton.teste_pratico_votacao.votacao.repository.VotoRepository;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service responsável pelo gerenciamento dos votos em uma pauta específica.
 * Inclui funcionalidades para contabilizar um voto e listar votos por pauta.
 */
@Service
public class VotacaoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    private CpfService cpfService;

    /**
     * Contabiliza um voto de acordo com a pauta, contabiliza esse voto por Cpf, não permite mais de um voto por Cpf e
     * também valida o Cpf se é válido.
     *
     * @param pauta a pauta na qual o voto será contabilizado
     * @param cpfAssociado CPF do associado que está votando
     * @param tipoVoto o tipo de voto (SIM ou NÃO)
     * @return ApiResponse contendo o voto contabilizado e uma mensagem de sucesso ou erro
     */
    public ApiResponse<Voto> contabilizar(Pauta pauta, String cpfAssociado, Voto.TipoVoto tipoVoto) {
        CpfResponse cpfResponse = cpfService.validateCPF(cpfAssociado);
        if (cpfResponse.getStatusCode() == 404) {
            return new ApiResponse<>(null, "CPF inválido. Não é possível contabilizar o voto.", false);
        }

        if (votoRepository.existsByPautaIdAndCpfAssociado(pauta.getId(), cpfAssociado)) {
            return new ApiResponse<>(null, "Associado já votou nessa pauta", false);
        }

        Sessao sessaoPauta = sessaoVotacaoRepository.findAllByPautaId(pauta.getId());

        LocalDateTime dataAtual = LocalDateTime.now();
        if ((sessaoPauta == null) || dataAtual.isBefore(sessaoPauta.getDataInicio()) || dataAtual.isAfter(sessaoPauta.getDataFim())) {
            return new ApiResponse<>(null, "Nenhuma sessão em aberto para a pauta informada.", false);
        }

        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setCpfAssociado(cpfAssociado);
        voto.setVoto(tipoVoto);

        return new ApiResponse<>(votoRepository.save(voto), "Voto contabilizado.", true);
    }

    /**
     * Lista todos os votos associados a uma pauta específica.
     *
     * @param pautaId o ID da pauta para a qual os votos serão listados
     * @return ApiResponse contendo uma lista de votos e uma mensagem de sucesso
     */
    public ApiResponse<List<Voto>> listarPorPauta(Long pautaId) {
        return new ApiResponse<>(votoRepository.findAllByPautaId(pautaId), "Listagem Votos.", true);
    }
}
