package com.br.elton.teste_pratico_votacao.cpf.controller;

import com.br.elton.teste_pratico_votacao.cpf.response.CpfResponse;
import com.br.elton.teste_pratico_votacao.cpf.service.CpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cpf")
public class CPFController {

    @Autowired
    public CpfService cpfService;

    @GetMapping("/validate")
    public CpfResponse validateCPF(@RequestParam String cpf) {
        return cpfService.validateCPF(cpf);
    }
}