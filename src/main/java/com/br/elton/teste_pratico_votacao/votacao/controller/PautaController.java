package com.br.elton.teste_pratico_votacao.votacao.controller;

import com.br.elton.teste_pratico_votacao.votacao.model.Pauta;
import com.br.elton.teste_pratico_votacao.votacao.service.PautaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public Pauta criarPauta(@RequestBody Pauta pauta) {
        return pautaService.criarPauta(pauta);
    }

    @GetMapping
    public List<Pauta> listarPautas() {
        return pautaService.listarPautas();
    }
}