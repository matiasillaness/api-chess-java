
package com.example.Backend.controllers;

import com.example.Backend.domain.MovimientoResultado;
import com.example.Backend.domain.Players;

import com.example.Backend.domain.RegistrarMovimientos;
import com.example.Backend.domain.pieces.Color;
import com.example.Backend.domain.pieces.Pieza;

import com.example.Backend.service.imp.JuegoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class juegoController {

    @Autowired
    JuegoServiceImp ServicioJuegoImp;

    public juegoController(JuegoServiceImp servicioJuegoImpMock) {
    }

    @PostMapping("/crearPartida")
    public ResponseEntity<?> crearPartida() {
        try {
            Pieza[][] piezas = ServicioJuegoImp.iniciarJuego();
            return ResponseEntity.ok(piezas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPartidaEnBase() {
        try {
            ServicioJuegoImp.guardarTableroConPiezasYJugadoresEnBase();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(200);
        }
        return ResponseEntity.ok(200);
    }

    //TODO: TEST /traerPartidaBase/{id}
    @GetMapping("/traerPartidaBase/{id}")
    public ResponseEntity<?> traerPartidaDesdeBasePorId(@PathVariable int id) {
        try {
            Pieza[][] piezas = ServicioJuegoImp.traerTableroConPiezasYJugadoresPorId(id);
            return ResponseEntity.ok(piezas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //TODO: TEST /traerMovimientosDeUnaPartida/{id}
    @GetMapping("/traerMovimientosDeUnaPartida/{id}")
    public ResponseEntity<?> traerMovimientosDeUnaPartida(@PathVariable int id) {
        try {
            List<RegistrarMovimientos> rp = ServicioJuegoImp.getMovimientosDeUnaPartida(id);
            return ResponseEntity.ok(rp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //TODO: TEST /obtener
    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerPartidaEnJuego() {
        try {
            Pieza[][] piezas = ServicioJuegoImp.obtenerTablero();
            return ResponseEntity.ok(piezas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/mover/{filaOrigen}/{columnaOrigen}/{filaDestino}/{columnaDestino}")
    public ResponseEntity<MovimientoResultado> mover(@PathVariable int filaOrigen, @PathVariable int columnaOrigen,
                                                     @PathVariable int filaDestino, @PathVariable int columnaDestino) {
        MovimientoResultado resultado = ServicioJuegoImp.mover(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
        return ResponseEntity.ok(resultado);
    }
}















