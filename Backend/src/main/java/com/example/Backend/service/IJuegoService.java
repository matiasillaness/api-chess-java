package com.example.Backend.service;

import com.example.Backend.domain.Players;
import com.example.Backend.domain.Tablero;
import com.example.Backend.domain.pieces.Pieza;

import java.util.List;

public interface IJuegoService {
    Pieza[][] iniciarJuego();
    boolean moverTorre(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna);
    boolean moverPeon(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna);
    boolean moverRey(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna);
    boolean moverReyna(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna);
    boolean moverAlfil(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna);
    Pieza[][] guardarJuego(Pieza[][] piezas);
    Pieza[][] actualizarTablero(Pieza[][] tableroActualizado);
    Tablero guardarTableroEnBasedeDatos(Tablero tablero);
    Players guardarPlayerEnBaseDeDatos(Players player);
    List<Pieza> guardarPieza(List<Pieza> piezas);
}