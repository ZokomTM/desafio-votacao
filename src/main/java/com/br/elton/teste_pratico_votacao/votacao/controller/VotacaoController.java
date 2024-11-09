package com.br.elton.teste_pratico_votacao.votacao.controller;

import com.br.elton.teste_pratico_votacao.votacao.record.RegistrarVoto;
import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import com.br.elton.teste_pratico_votacao.votacao.service.PautaService;
import com.br.elton.teste_pratico_votacao.votacao.service.SessaoVotacaoService;
import com.br.elton.teste_pratico_votacao.votacao.service.VotacaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {
    private final VotacaoService votacaoService;
    private final PautaService pautaService;
    private final SessaoVotacaoService sessaoService;

    public VotacaoController(VotacaoService votacaoService, PautaService pautaService, SessaoVotacaoService sessaoService) {
        this.votacaoService = votacaoService;
        this.pautaService = pautaService;
        this.sessaoService = sessaoService;
    }

    @PostMapping("/{pautaId}/sessao")
    public void abrirSessao(@PathVariable Long pautaId, @RequestParam(defaultValue = "1") int minutos) {
        var pauta = pautaService.buscarPautaPorId(pautaId);
        sessaoService.abrirSessao(pauta, minutos);
    }

    @PostMapping("/{pautaId}/voto")
    public Voto efetuarVoto(@PathVariable Long pautaId, @RequestBody RegistrarVoto registrarVoto) {
        var pauta = pautaService.buscarPautaPorId(pautaId);
        return votacaoService.registrarVoto(pauta, registrarVoto.cpfAssociado(), registrarVoto.voto());
    }
}
