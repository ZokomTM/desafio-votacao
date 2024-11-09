package com.br.elton.teste_pratico_votacao.votacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}