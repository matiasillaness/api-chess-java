package com.example.Backend.domain;


import com.example.Backend.domain.pieces.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Players implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    private Tablero tablero;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    private boolean turno = false;

    public Players(Tablero tablero,String name, Color color) {
        this.tablero = tablero;
        this.name = name;
        this.color = color;
    }
    public Players(String jugador2, Color color) {
        this.name = jugador2;
        this.color = color;
    }
    public Players(String jugador2, Color color, boolean turno) {
        this.name = jugador2;
        this.color = color;
        this.turno = turno;
    }
    public Players(String s, Color color, boolean b, Tablero tablero) {
        this.name = s;
        this.color = color;
        this.turno = b;
        this.tablero = tablero;
    }
    public long setTablero(Tablero tablero) {
        return tablero.getId();
    }
}
