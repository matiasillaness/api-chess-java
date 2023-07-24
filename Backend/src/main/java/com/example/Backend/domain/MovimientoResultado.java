package com.example.Backend.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovimientoResultado {
    private boolean exito;
    private String mensaje;
    private RegistrarMovimientos registrarMovimientos;
    private List<RegistrarMovimientos> movimientos;

    public MovimientoResultado(boolean exito, String mensaje, RegistrarMovimientos registrarMovimientos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.registrarMovimientos = registrarMovimientos;
    }

    public MovimientoResultado(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;

    }
    public MovimientoResultado(boolean exito, String mensaje, List<RegistrarMovimientos> movimientos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.movimientos = movimientos;
    }
}