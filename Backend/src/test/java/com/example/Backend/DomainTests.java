package com.example.Backend;
import com.example.Backend.domain.MovimientoResultado;
import com.example.Backend.domain.Players;
import com.example.Backend.domain.RegistrarMovimientos;
import com.example.Backend.domain.Tablero;
import com.example.Backend.domain.pieces.Color;
import com.example.Backend.domain.pieces.Pieza;
import com.example.Backend.domain.pieces.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DomainTests {
    @Test
    public void testConstructorRegistrarMovimiento() {
        String movOrigen = "A1";
        String movDestino = "B2";
        Tablero tablero = new Tablero();

        RegistrarMovimientos registrarMovimientos = new RegistrarMovimientos(movOrigen, movDestino, tablero);

        assertEquals(movOrigen, registrarMovimientos.getMovOrigen());
        assertEquals(movDestino, registrarMovimientos.getMovDestino());
        assertEquals(tablero, registrarMovimientos.getTablero());
    }
    @Test
    public void testConstructorConRegistrarMovimientos() {
        RegistrarMovimientos registrarMovimientos = new RegistrarMovimientos();

        MovimientoResultado resultado = new MovimientoResultado(true, "Mensaje de éxito", registrarMovimientos);

        assertTrue(resultado.isExito());
        assertEquals("Mensaje de éxito", resultado.getMensaje());
        assertEquals(registrarMovimientos, resultado.getRegistrarMovimientos());
    }

    @Test
    public void testConstructorSinRegistrarMovimientos() {
        MovimientoResultado resultado = new MovimientoResultado(false, "Mensaje de error");

        assertFalse(resultado.isExito());
        assertEquals("Mensaje de error", resultado.getMensaje());
        assertNull(resultado.getRegistrarMovimientos());
    }

    @Test
    public void testConstructorConListaDeMovimientos() {
        List<RegistrarMovimientos> movimientos = new ArrayList<>();
        movimientos.add(new RegistrarMovimientos());

        MovimientoResultado resultado = new MovimientoResultado(true, "Mensaje de éxito", movimientos);

        assertTrue(resultado.isExito());
        assertEquals("Mensaje de éxito", resultado.getMensaje());
        assertEquals(movimientos, resultado.getMovimientos());
    }

    @Test
    void testPlayersConstructor1() {
        String name = "Jugador 1";
        Color color = Color.BLANCO;

        Tablero tablero = new Tablero();
        Players players = new Players(tablero, name, color);

        Assertions.assertEquals(tablero, players.getTablero());
        Assertions.assertEquals(name, players.getName());
        Assertions.assertEquals(color, players.getColor());
    }


    @Test
    void testPlayersConstructor2() {
        String name = "Jugador 2";
        Color color = Color.NEGRO;

        Players players = new Players(name, color);

        assertNull(players.getTablero());
        assertEquals(name, players.getName());
        assertEquals(color, players.getColor());
        assertFalse(players.isTurno());
    }

    @Test
    void testPlayersConstructor3() {
        String name = "Jugador 1";
        Color color = Color.BLANCO;
        boolean turno = true;

        Players players = new Players(name, color, turno);

        assertNull(players.getTablero());
        assertEquals(name, players.getName());
        assertEquals(color, players.getColor());
        assertTrue(players.isTurno());
    }

    @Test
    void testPlayersConstructor4() {
        String name = "Jugador 2";
        Color color = Color.NEGRO;
        boolean turno = true;

        Tablero tablero = new Tablero();
        Players players = new Players(name, color, turno, tablero);

        Assertions.assertEquals(name, players.getName());
        Assertions.assertEquals(color, players.getColor());
        Assertions.assertTrue(players.isTurno());
        Assertions.assertEquals(tablero, players.getTablero());
    }

    @Test
    void testSetTablero() {
        Tablero tablero = new Tablero();
        tablero.setId(123L);
        Players players = new Players();
        long tableroId = 123;

        long result = players.setTablero(tablero);

        assertEquals(tableroId, result);
    }

    @Test
    public void testPiezaConstructor1() {
        boolean viva = true;
        Color color = Color.BLANCO;
        Tipo tipo = Tipo.PEON;
        int fila = 2;
        int columna = 3;

        Pieza pieza = new Pieza(viva, color, tipo, fila, columna);

        assertEquals(viva, pieza.isViva());
        assertEquals(color, pieza.getColor());
        assertEquals(tipo, pieza.getTipo());
        assertEquals(fila, pieza.getFila());
        assertEquals(columna, pieza.getColumna());
    }

    @Test
    public void testPiezaConstructor2() {
        Color color = Color.NEGRO;
        Tipo tipo = Tipo.CABALLO;

        Pieza pieza = new Pieza(color, tipo);

        assertTrue(pieza.isViva());
        assertEquals(color, pieza.getColor());
        assertEquals(tipo, pieza.getTipo());
        assertEquals(0, pieza.getFila());
        assertEquals(0, pieza.getColumna());
    }

    @Test
    public void testPiezaConstructor3() {
        boolean viva = true;
        Color color = Color.BLANCO;
        Tipo tipo = Tipo.TORRE;
        int fila = 4;
        int columna = 5;
        Tablero tablero = new Tablero();

        Pieza pieza = new Pieza(viva, color, tipo, fila, columna, tablero);

        assertEquals(viva, pieza.isViva());
        assertEquals(color, pieza.getColor());
        assertEquals(tipo, pieza.getTipo());
        assertEquals(fila, pieza.getFila());
        assertEquals(columna, pieza.getColumna());
        assertEquals(tablero, pieza.getTablero());
    }

    @Test
    public void testPiezaConstructor4() {
        boolean viva = true;
        Color color = Color.NEGRO;
        Tipo tipo = Tipo.REINA;
        int fila = 3;
        int columna = 4;
        Long id = 12345L;

        Pieza pieza = new Pieza(viva, color, tipo, fila, columna, id);

        assertEquals(viva, pieza.isViva());
        assertEquals(color, pieza.getColor());
        assertEquals(tipo, pieza.getTipo());
        assertEquals(fila, pieza.getFila());
        assertEquals(columna, pieza.getColumna());
        assertEquals(id, pieza.getId());
    }
    @Test
    void testPiezaConstructor5() {
        boolean b = true;
        Color color = Color.NEGRO;
        Tipo tipo = Tipo.PEON;
        int fila = 3;
        int columna = 4;
        Tablero tablero = new Tablero();

        Pieza pieza = new Pieza(b, color, tipo, fila, columna, tablero);

        Assertions.assertTrue(b);
        Assertions.assertEquals(color, pieza.getColor());
        Assertions.assertEquals(tipo, pieza.getTipo());
        Assertions.assertEquals(fila, pieza.getFila());
        Assertions.assertEquals(columna, pieza.getColumna());
        Assertions.assertEquals(tablero, pieza.getTablero());
    }

    @Test
    public void testEsCoordenadaValida() {
        Pieza pieza = new Pieza(Color.BLANCO, Tipo.PEON);

        assertTrue(pieza.esCoordenadaValida(0, 0));
        assertTrue(pieza.esCoordenadaValida(7, 7));
        assertFalse(pieza.esCoordenadaValida(-1, 0));
        assertFalse(pieza.esCoordenadaValida(8, 0));
        assertFalse(pieza.esCoordenadaValida(0, -1));
        assertFalse(pieza.esCoordenadaValida(0, 8));
    }
}
