package com.br.elton.teste_pratico_votacao.votacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long associadoId;

    @Enumerated(EnumType.STRING)
    private TipoVoto voto;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private LocalDateTime dataVoto;

    public enum TipoVoto {
        SIM, NAO
    }
}