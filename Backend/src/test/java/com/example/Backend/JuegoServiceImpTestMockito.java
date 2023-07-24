package com.example.Backend;
import com.example.Backend.domain.*;
import com.example.Backend.domain.pieces.Color;
import com.example.Backend.domain.pieces.Pieza;
import com.example.Backend.domain.pieces.Tipo;
import com.example.Backend.repository.PiezaRepository;
import com.example.Backend.repository.PlayersRepository;
import com.example.Backend.repository.RegistrarMovimientosRepository;
import com.example.Backend.repository.TableroRepository;
import com.example.Backend.service.imp.JuegoServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JuegoServiceImpTestMockito {
    @Mock
    private TableroRepository tableroRepository;
    @Mock
    private PiezaRepository piezaRepository;
    @Mock
    private RegistrarMovimientosRepository registrarMovimientosRepository;
    @Mock
    private PlayersRepository playersRepository;
    @InjectMocks
    private JuegoServiceImp juegoServiceImpMock;

    @Test
    public void testTraerTableroConPiezasYJugadoresPorId() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setTableroRepository(tableroRepository);
        juegoService.setPiezaRepository(piezaRepository);
        juegoService.setRegistrarMovimientosRepository(registrarMovimientosRepository);
        juegoService.setPlayersRepository(playersRepository);

        long id = 1L;
        Tablero tablero = new Tablero();
        Pieza pieza = new Pieza();
        List<Pieza> piezas = new ArrayList<>();
        piezas.add(pieza);
        List<RegistrarMovimientos> movimientos = new ArrayList<>();
        List<Players> players = new ArrayList<>();
        Players jugador = new Players();

        when(tableroRepository.findById(id)).thenReturn(Optional.of(tablero));
        when(piezaRepository.findByTablero(tablero)).thenReturn(piezas);
        when(registrarMovimientosRepository.findByTablero(tablero)).thenReturn(movimientos);
        when(playersRepository.findByTablero(tablero)).thenReturn(players);

        Pieza[][] resultado = juegoService.traerTableroConPiezasYJugadoresPorId(id);

        assertNotNull(resultado);
        assertSame(pieza, resultado[pieza.getFila()][pieza.getColumna()]);
        verify(tableroRepository, times(1)).findById(id);
        verify(piezaRepository, times(1)).findByTablero(tablero);
        verify(registrarMovimientosRepository, times(1)).findByTablero(tablero);
        verify(playersRepository, times(1)).findByTablero(tablero);
    }
    @Test
    public void testSetTableroRepository() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setTableroRepository(tableroRepository);
        assertNotNull(juegoService.getTableroRepository());
    }

    @Test
    public void testSetPiezaRepository() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setPiezaRepository(piezaRepository);
        assertNotNull(juegoService.getPiezaRepository());
    }

    @Test
    public void testSetRegistrarMovimientosRepository() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setRegistrarMovimientosRepository(registrarMovimientosRepository);
        assertNotNull(juegoService.getRegistrarMovimientosRepository());
    }

    @Test
    public void testSetPlayersRepository() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setPlayersRepository(playersRepository);
        assertNotNull(juegoService.getPlayersRepository());
    }
    @Test
    public void testGetTableroRepository() {

        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setTableroRepository(tableroRepository);

        TableroRepository tableroRepository = juegoService.getTableroRepository();
        assertNotNull(tableroRepository);
    }

    @Test
    public void testGetPiezaRepository() {

        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setPiezaRepository(piezaRepository);

        PiezaRepository piezaRepository = juegoService.getPiezaRepository();
        assertNotNull(piezaRepository);
    }

    @Test
    public void testGetRegistrarMovimientosRepository() {

        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setRegistrarMovimientosRepository(registrarMovimientosRepository);

        RegistrarMovimientosRepository registrarMovimientosRepository = juegoService.getRegistrarMovimientosRepository();
        assertNotNull(registrarMovimientosRepository);
    }

    @Test
    public void testGetPlayersRepository() {

        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.setPlayersRepository(playersRepository);

        PlayersRepository playersRepository = juegoService.getPlayersRepository();
        assertNotNull(playersRepository);
    }


    @Test
    public void testGuardarPieza() {
        JuegoServiceImp juegoService = new JuegoServiceImp();

        List<Pieza> piezas = Arrays.asList(
                new Pieza(Color.BLANCO, Tipo.TORRE),
                new Pieza(Color.NEGRO, Tipo.CABALLO),
                new Pieza(Color.BLANCO, Tipo.ALFIL)
        );

        PiezaRepository piezaRepository = mock(PiezaRepository.class);

        when(piezaRepository.saveAll(piezas)).thenReturn(piezas);

        juegoService.setPiezaRepository(piezaRepository);

        List<Pieza> resultado = juegoService.guardarPieza(piezas);

        verify(piezaRepository, times(1)).saveAll(piezas);

        assertEquals(piezas, resultado);
    }

    @Test
    public void testGetMovimientosDeUnaPartida() {

        JuegoServiceImp juegoService = new JuegoServiceImp();

        long partidaId = 123;

        Tablero tablero = new Tablero();

        List<RegistrarMovimientos> movimientos = new ArrayList<>();
        movimientos.add(new RegistrarMovimientos());
        movimientos.add(new RegistrarMovimientos());

        TableroRepository tableroRepository = mock(TableroRepository.class);

        when(tableroRepository.findById(partidaId)).thenReturn(Optional.of(tablero));

        RegistrarMovimientosRepository registrarMovimientosRepository = mock(RegistrarMovimientosRepository.class);

        when(registrarMovimientosRepository.findByTablero(tablero)).thenReturn(movimientos);

        juegoService.setTableroRepository(tableroRepository);
        juegoService.setRegistrarMovimientosRepository(registrarMovimientosRepository);

        List<RegistrarMovimientos> resultado = juegoService.getMovimientosDeUnaPartida(partidaId);

        assertEquals(movimientos, resultado);

        verify(tableroRepository, times(1)).findById(partidaId);

        verify(registrarMovimientosRepository, times(1)).findByTablero(tablero);
    }

    @Test
    public void testGuardarPlayerEnBaseDeDatos() {

        JuegoServiceImp playersService = new JuegoServiceImp();

        Players player = new Players();
        player.setId(1L);
        player.setName("John Doe");

        PlayersRepository playersRepository = mock(PlayersRepository.class);

        when(playersRepository.save(player)).thenReturn(player);

        playersService.setPlayersRepository(playersRepository);

        Players resultado = playersService.guardarPlayerEnBaseDeDatos(player);

        assertEquals(player, resultado);

        verify(playersRepository, times(1)).save(player);
    }

    @Test
    public void testGuardarTableroEnBasedeDatos() {

        JuegoServiceImp tableroService = new JuegoServiceImp();

        Tablero tablero = new Tablero();
        tablero.setId(1L);

        TableroRepository tableroRepository = mock(TableroRepository.class);

        when(tableroRepository.save(tablero)).thenReturn(tablero);

        tableroService.setTableroRepository(tableroRepository);

        Tablero resultado = tableroService.guardarTableroEnBasedeDatos(tablero);

        assertEquals(tablero, resultado);

        verify(tableroRepository, times(1)).save(tablero);
    }

    @Test
    public void testGuardarPlayersEnBase() {

        JuegoServiceImp playersService = new JuegoServiceImp();

        List<Players> players = new ArrayList<>();
        Players player1 = new Players("Player1",Color.BLANCO);
        Players player2 = new Players("Player2",Color.NEGRO);
        players.add(player1);
        players.add(player2);

        PlayersRepository playersRepository = mock(PlayersRepository.class);

        playersService.setPlayersRepository(playersRepository);

        playersService.guardarPlayersEnBase(players);

        verify(playersRepository, times(1)).saveAll(players);
    }

    @Test
    public void testMoverRey_MovimientoValidoDevuelveTrue() {

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza pieza = new Pieza();

        boolean resultado = piezaService.moverRey(pieza, 1, 2, 1, 1, 1, 0);

        assertTrue(resultado);
    }

    @Test
    public void testMoverRey_MovimientoInvalidoDevuelveFalse() {

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza pieza = new Pieza();

        boolean resultado = piezaService.moverRey(pieza, 1, 3, 1, 1, 2, 2);

        assertFalse(resultado);
    }


    @Test
    public void testEstadoJaqueMate_ReyNulo() {

        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.estadoJaqueMate(jugadorActual);

        assertFalse(resultado);
    }


    @Test
    public void testEstadoJaque_ReyNulo() {
        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.estadoJaque(jugadorActual);

        assertFalse(resultado);
    }

    @Test
    public void testVerificarAmenazaRey_NoAmenaza() {

        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.verificarAmenazaRey(jugadorActual, 3, 3);

        assertFalse(resultado);
    }

    @Test
    public void testVerificarAmenazaRey_Amenaza() {

        Players jugadorActual = mock(Players.class);

        Pieza pieza = mock(Pieza.class);
        when(pieza.getColor()).thenReturn(Color.BLANCO);
        when(pieza.getTipo()).thenReturn(Tipo.REY);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[2][2] = pieza;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaRey(jugadorActual, 3, 3);

        assertTrue(resultado);
    }

    @Test
    public void testVerificarAmenazaCaballo_NoAmenaza() {

        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.verificarAmenazaCaballo(jugadorActual, 3, 3);

        assertFalse(resultado);
    }

    @Test
    public void testVerificarAmenazaCaballo_Amenaza() {
        Players jugadorActual = mock(Players.class);
        when(jugadorActual.getColor()).thenReturn(Color.BLANCO);

        Pieza pieza = mock(Pieza.class);
        when(pieza.getColor()).thenReturn(Color.NEGRO);
        when(pieza.getTipo()).thenReturn(Tipo.CABALLO);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[1][2] = pieza;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaCaballo(jugadorActual, 3, 3);

        assertTrue(resultado);
    }


    @Test
    public void testVerificarAmenazaLineal_NoAmenaza() {
        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.verificarAmenazaLineal(jugadorActual, 3, 3);

        assertFalse(resultado);
    }

    @Test
    public void testVerificarAmenazaLineal_AmenazaTorre() {
        Players jugadorActual = mock(Players.class);
        when(jugadorActual.getColor()).thenReturn(Color.BLANCO);

        Pieza torre = mock(Pieza.class);
        when(torre.getColor()).thenReturn(Color.NEGRO);
        when(torre.getTipo()).thenReturn(Tipo.TORRE);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[3][5] = torre;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaLineal(jugadorActual, 3, 3);

        assertTrue(resultado);
    }

    @Test
    public void testVerificarAmenazaLineal_AmenazaReina() {
        Players jugadorActual = mock(Players.class);
        when(jugadorActual.getColor()).thenReturn(Color.BLANCO);

        Pieza reina = mock(Pieza.class);
        when(reina.getColor()).thenReturn(Color.NEGRO);
        when(reina.getTipo()).thenReturn(Tipo.REINA);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[3][1] = reina;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaLineal(jugadorActual, 3, 3);

        assertTrue(resultado);
    }

    @Test
    public void testVerificarAmenazaDiagonal_NoAmenaza() {

        Players jugadorActual = mock(Players.class);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        boolean resultado = piezaService.verificarAmenazaDiagonal(jugadorActual, 3, 3);

        assertFalse(resultado);
    }

    @Test
    public void testVerificarAmenazaDiagonal_AmenazaAlfil() {

        Players jugadorActual = mock(Players.class);
        when(jugadorActual.getColor()).thenReturn(Color.BLANCO);

        Pieza alfil = mock(Pieza.class);
        when(alfil.getColor()).thenReturn(Color.NEGRO);
        when(alfil.getTipo()).thenReturn(Tipo.ALFIL);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[1][1] = alfil;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaDiagonal(jugadorActual, 3, 3);

        assertTrue(resultado);
    }

    @Test
    public void testVerificarAmenazaDiagonal_AmenazaReina() {

        Players jugadorActual = mock(Players.class);
        when(jugadorActual.getColor()).thenReturn(Color.BLANCO);

        Pieza reina = mock(Pieza.class);
        when(reina.getColor()).thenReturn(Color.NEGRO);
        when(reina.getTipo()).thenReturn(Tipo.REINA);

        JuegoServiceImp piezaService = new JuegoServiceImp();

        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[2][4] = reina;
        piezaService.setTableroLogica(tableroLogica);

        boolean resultado = piezaService.verificarAmenazaDiagonal(jugadorActual, 3, 3);

        assertTrue(resultado);
    }


}
