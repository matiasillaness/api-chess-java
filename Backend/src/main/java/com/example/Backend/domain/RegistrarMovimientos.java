package com.example.Backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarMovimientos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movimiento_origen")
    private String movOrigen;

    @Column(name = "movimiento_destino")
    private String movDestino;

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    @JsonBackReference
    private Tablero tablero;

    public RegistrarMovimientos(String movOrigen, String movDestino, Tablero tablero) {
        this.movOrigen = movOrigen;
        this.movDestino = movDestino;
        this.tablero = tablero;
    }
}
