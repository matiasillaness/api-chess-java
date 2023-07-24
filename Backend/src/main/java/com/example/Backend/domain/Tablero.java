package com.example.Backend.domain;

import com.example.Backend.domain.pieces.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tablero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tablero")
    @Column(name = "lista_piezas")
    @JsonBackReference
    private List<Pieza> tableroBase = new ArrayList<>(64);

    @OneToMany(mappedBy = "tablero")
    @Column(name = "lista_jugadores")
    private List<Players> jugadores = new ArrayList<>(2);

    @Column(name = "estado_jaque")
    private boolean estadoJaque = false;

    @Column(name = "estado_jaque_mate")
    private boolean estadoJaqueMate = false;
}
