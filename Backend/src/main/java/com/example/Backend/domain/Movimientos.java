package com.example.Backend.domain;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movimientos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private List<String> movimientos;

    @Column(name = "movimientos")
    private String movimientosString;

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    private Tablero tablero;
}
