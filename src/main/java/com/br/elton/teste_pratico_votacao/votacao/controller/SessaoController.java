package com.br.elton.teste_pratico_votacao.votacao.controller;

import com.br.elton.teste_pratico_votacao.votacao.model.Sessao;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import com.br.elton.teste_pratico_votacao.votacao.service.PautaService;
import com.br.elton.teste_pratico_votacao.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoController {
    @Autowired
    private PautaService pautaService;
    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/{pautaId}")
    public ResponseEntity<ApiResponse<Sessao>> iniciar(@PathVariable Long pautaId, @RequestParam(defaultValue = "10") int minutos) {
        var pauta = pautaService.buscarPautaPorId(pautaId);
        ApiResponse<Sessao> response = sessaoService.iniciar(pauta, minutos);

        URI location = URI.create("/sessoes/" + response.getData().getId());

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/{sessaoId}/incrementar")
    public ResponseEntity<ApiResponse<Sessao>> adicionarTempo(@PathVariable Long sessaoId, @RequestParam int minutos) {
        ApiResponse<Sessao> response = sessaoService.incrementarTempo(sessaoId, minutos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Sessao>>> listar() {
        return ResponseEntity.ok(sessaoService.listar());
    }
}