package com.example.Backend.domain.pieces;


import com.example.Backend.domain.Tablero;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pieza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "estado")
    private boolean viva = true;

    @Column(name = "fila")
    private int fila;

    @Column(name = "columna")
    private int columna;

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    @JsonBackReference
    private Tablero tablero;

    @Column(name = "primer_movimiento")
    private boolean primerMovimiento = false;
    public Pieza(Color color) {

    }

    public Pieza(boolean b, Color color, Tipo tipo, int i, int i1) {
        this.viva = b;
        this.color = color;
        this.tipo = tipo;
        this.fila = i;
        this.columna = i1;
    }
    public Pieza(Color color, Tipo tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    public Pieza(boolean b, Color color, Tipo tipo, int i, int i1, Tablero tablero) {
        this.viva = b;
        this.color = color;
        this.tipo = tipo;
        this.fila = i;
        this.columna = i1;
        this.tablero = tablero;
    }

    public Pieza(boolean b, Color color, Tipo tipo, int i, int i1, Long id) {
        this.viva = b;
        this.color = color;
        this.tipo = tipo;
        this.fila = i;
        this.columna = i1;
        this.id = id;
    }

    public Pieza(boolean b, Color color, Tipo tipo, int fila, int columna, Pieza[][] tablero) {
    }

    public boolean esCoordenadaValida(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
