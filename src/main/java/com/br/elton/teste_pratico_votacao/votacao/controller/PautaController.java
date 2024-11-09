package com.br.elton.teste_pratico_votacao.votacao.controller;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.response.ApiResponse;
import com.br.elton.teste_pratico_votacao.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<ApiResponse<Pauta>> criar(@RequestBody Pauta pauta) {

        ApiResponse<Pauta> response = pautaService.salvar(pauta);

        URI location = URI.create("/pautas/" + response.getData().getId());

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{pautaId}")
    public ResponseEntity<ApiResponse<Pauta>> atualizar(@PathVariable Long pautaId, @RequestBody Pauta pauta) {
        pauta.setId(pautaId);

        ApiResponse<Pauta> response = pautaService.salvar(pauta);

        URI location = URI.create("/pautas/" + response.getData().getId());

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Pauta>>> listar() {
        return ResponseEntity.ok(pautaService.listar());
    }

    @GetMapping("/{pautaId}")
    public ResponseEntity<ApiResponse<Pauta>> get(@PathVariable Long pautaId) {
        return ResponseEntity.ok(pautaService.buscarPorId(pautaId));
    }
}