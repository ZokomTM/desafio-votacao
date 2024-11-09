package com.br.elton.teste_pratico_votacao.votacao.controller;

import com.br.elton.teste_pratico_votacao.votacao.record.RegistrarVoto;
import com.br.elton.teste_pratico_votacao.votacao.model.Voto;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import com.br.elton.teste_pratico_votacao.votacao.service.PautaService;
import com.br.elton.teste_pratico_votacao.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {
    @Autowired
    private VotacaoService votacaoService;
    @Autowired
    private PautaService pautaService;

    @PostMapping("/{pautaId}/voto")
    public ResponseEntity<ApiResponse<Voto>> votar(@PathVariable Long pautaId, @RequestBody RegistrarVoto registrarVoto) {
        var pauta = pautaService.buscarPautaPorId(pautaId);
        return ResponseEntity.ok(votacaoService.contabilizar(pauta, registrarVoto.cpfAssociado(), registrarVoto.voto()));
    }

    @GetMapping("/{pautaId}")
    public ResponseEntity<ApiResponse<List<Voto>>> listar(@PathVariable Long pautaId) {
        return ResponseEntity.ok(votacaoService.listarPorPauta(pautaId));
    }
}
