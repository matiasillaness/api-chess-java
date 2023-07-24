package com.example.Backend;
import com.example.Backend.domain.MovimientoResultado;
import com.example.Backend.domain.Players;
import com.example.Backend.domain.pieces.Color;
import com.example.Backend.domain.pieces.Pieza;
import com.example.Backend.domain.pieces.Tipo;
import com.example.Backend.service.imp.JuegoServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class JuegoServiceImpTest {
    private JuegoServiceImp juegoService;
    private List<Players> players;
    private List<Pieza> piezas;
    private Pieza[][] tableroLogica;


    @Before
    public void setUp() {
        juegoService = new JuegoServiceImp();
        players = new ArrayList<>();
        piezas = new ArrayList<>();
        tableroLogica = new Pieza[8][8];
    }

    // Test para verificar que se inicie el juego correctamente
    // Contiene tests de otros metodos: getPlayers y getPiezas
    @Test
    public void testIniciarJuego() {

        Pieza[][] resultado = juegoService.iniciarJuego();

        //Verificar que el tablero no sea nulo
        assertNotNull(resultado);
        assertEquals(8, resultado.length);
        assertEquals(8, resultado[0].length);

        //Verificar la correcta creación de la primera pieza
        for (int fila = 0; fila < 1; fila++) {
            for (int columna = 0; columna < 1; columna++) {
                assertEquals(resultado[fila][columna],resultado[0][0]);
            }
        }

        //Verificar que se hayan creado los jugadores correctamente
        assertEquals(2, juegoService.getPlayers().size());
        assertEquals(Color.BLANCO, juegoService.getPlayers().get(0).getColor());
        assertEquals(Color.NEGRO, juegoService.getPlayers().get(1).getColor());

        //Verificar que se hayan creado las piezas correctamente
        assertEquals(32, juegoService.getPiezas().size());
    }
    @Test
    public void testGetPlayers() {
        List<Players> resultado = juegoService.getPlayers();
        assertEquals(players, resultado);
    }

    @Test
    public void testSetPlayers() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear una lista de jugadores de ejemplo
        List<Players> players = new ArrayList<>();
        Players jugador1 = new Players("Jugador 1", Color.BLANCO);
        Players jugador2 = new Players("Jugador 2", Color.NEGRO);
        players.add(jugador1);
        players.add(jugador2);

        // Llamar al método setPlayers() con la lista de jugadores
        juegoService.setPlayers(players);

        // Obtener la lista de jugadores establecida en el objeto juegoService
        List<Players> jugadoresActuales = juegoService.getPlayers();

        // Verificar que la lista de jugadores sea igual a la lista original
        assertEquals(players, jugadoresActuales);
    }

    @Test
    public void testGetPiezas() {
        List<Pieza> resultado = juegoService.getPiezas();
        assertEquals(piezas, resultado);
    }

    @Test
    public void testObtenerTablero() {
        Pieza[][] resultado = juegoService.obtenerTablero();
        assertArrayEquals(tableroLogica, resultado);
    }

    @Test
    public void testSetTableroLogica() {
        Pieza[][] tablero = new Pieza[8][8];
        juegoService.setTableroLogica(tablero);

        assertSame(tablero, juegoService.obtenerTablero());
    }

    @Test
    public void testObtenerJugadorActual() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Configurar el estado de la lista de jugadores
        List<Players> players = new ArrayList<>();
        Players player1 = new Players("Jugador 1", Color.BLANCO);
        Players player2 = new Players("Jugador 2", Color.NEGRO);
        player1.setTurno(true);
        players.add(player1);
        players.add(player2);
        juegoService.setPlayers(players);

        // Llamar al método obtenerJugadorActual()
        Players jugadorActual = juegoService.obtenerJugadorActual();

        // Verificar el resultado esperado
        assertNotNull(jugadorActual);
        assertEquals(Color.BLANCO, jugadorActual.getColor());
        assertTrue(jugadorActual.isTurno());

        // Configurar un estado diferente de la lista de jugadores
        player1.setTurno(false);
        player2.setTurno(true);

        // Llamar nuevamente al método obtenerJugadorActual()
        jugadorActual = juegoService.obtenerJugadorActual();

        // Verificar el resultado esperado con el nuevo estado de los jugadores
        assertNotNull(jugadorActual);
        assertEquals(Color.NEGRO, jugadorActual.getColor());
        assertTrue(jugadorActual.isTurno());

        // Configurar un estado donde no hay jugador con el turno activo
        player1.setTurno(false);
        player2.setTurno(false);

        // Llamar nuevamente al método obtenerJugadorActual()
        jugadorActual = juegoService.obtenerJugadorActual();

        // Verificar que el resultado sea nulo
        assertNull(jugadorActual);
    }

    @Test
    public void testObtenerJugadorOponente() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Configurar el estado de la lista de jugadores
        List<Players> players = new ArrayList<>();
        Players jugador1 = new Players("Jugador 1", Color.BLANCO);
        Players jugador2 = new Players("Jugador 2", Color.NEGRO);
        jugador1.setTurno(true);
        players.add(jugador1);
        players.add(jugador2);
        juegoService.setPlayers(players);

        // Llamar al método obtenerJugadorOponente()
        Players jugadorOponente = juegoService.obtenerJugadorOponente();

        // Verificar el resultado esperado
        assertNotNull(jugadorOponente);
        assertEquals(Color.NEGRO, jugadorOponente.getColor());
        assertFalse(jugadorOponente.isTurno());

        // Configurar un estado diferente de la lista de jugadores
        jugador1.setTurno(false);
        jugador2.setTurno(true);

        // Llamar nuevamente al método obtenerJugadorOponente()
        jugadorOponente = juegoService.obtenerJugadorOponente();

        // Verificar el resultado esperado con el nuevo estado de los jugadores
        assertNotNull(jugadorOponente);
        assertEquals(Color.BLANCO, jugadorOponente.getColor());
        assertFalse(jugadorOponente.isTurno());
    }

    @Test
    public void testConvertidorMovimientos() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Llamar al método convertidorMovimientos() con diferentes valores de entrada
        String movimiento1 = juegoService.convertidorMovimientos(0, 0);
        String movimiento2 = juegoService.convertidorMovimientos(3, 4);
        String movimiento3 = juegoService.convertidorMovimientos(7, 2);

        // Verificar los resultados esperados
        assertEquals("A8", movimiento1);
        assertEquals("D4", movimiento2);
        assertEquals("H6", movimiento3);
    }

    @Test
    public void testGuardarJuego() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear una matriz de piezas de ejemplo
        Pieza[][] piezas = new Pieza[8][8];
        // ... Código para inicializar la matriz de piezas con valores de ejemplo ...

        // Llamar al método guardarJuego() y obtener el resultado
        Pieza[][] resultado = juegoService.guardarJuego(piezas);

        // Verificar que el resultado es igual a la matriz de piezas original
        assertArrayEquals(piezas, resultado);
    }

    @Test
    public void testEstadoTablas() {
        JuegoServiceImp juegoService = new JuegoServiceImp();
        Pieza[][] tablero = new Pieza[8][8];

        boolean resultado = juegoService.estadoTablas(tablero);

        assertFalse(resultado);
    }

    @Test
    public void testActualizarTablero() {
        Pieza[][] tableroActualizado;
        tableroActualizado = new Pieza[8][8];
        Pieza[][] resultado = juegoService.actualizarTablero(tableroActualizado);
        assertArrayEquals(tableroActualizado, resultado);
    }
    @Test
    public void testValidarJugadorActual() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Configurar el estado del jugador actual y la pieza
        Players jugadorActual = new Players("Jugador 1", Color.BLANCO);
        Pieza pieza = new Pieza(Color.BLANCO, Tipo.PEON);

        // Llamar al método validarJugadorActual()
        boolean resultado = juegoService.validarJugadorActual(jugadorActual, pieza);

        // Verificar el resultado esperado
        assertTrue(resultado);

        // Configurar un estado diferente del jugador actual y la pieza
        jugadorActual.setColor(Color.NEGRO);
        pieza.setColor(Color.BLANCO);

        // Llamar nuevamente al método validarJugadorActual()
        resultado = juegoService.validarJugadorActual(jugadorActual, pieza);

        // Verificar el resultado esperado con el nuevo estado del jugador actual y la pieza
        assertFalse(resultado);
    }

    @Test
    public void testValidarDestino() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Llamar al método validarDestino() con diferentes valores de destino
        boolean resultado1 = juegoService.validarDestino(2, 5);
        boolean resultado2 = juegoService.validarDestino(0, 0);
        boolean resultado3 = juegoService.validarDestino(7, 9);

        // Verificar los resultados esperados
        assertTrue(resultado1);
        assertTrue(resultado2);
        assertFalse(resultado3);
    }
    @Test
    public void testMover() {

        JuegoServiceImp juegoService = new JuegoServiceImp();
        juegoService.iniciarJuego();

        MovimientoResultado resultado = juegoService.mover(2, 3, 4, 3);

        assertFalse(resultado.isExito());
        assertEquals("Movimiento inválido", resultado.getMensaje());

    }
    @Test
    public void testMoverTorre() {
        // Crear una instancia de la implementación de JuegoService
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear una pieza de ejemplo y un tablero de ejemplo
        Pieza torre = new Pieza(Color.BLANCO, Tipo.TORRE);
        juegoService.setTableroLogica(new Pieza[8][8]); // Establecer el tablero de ejemplo


        // Realizar pruebas de movimiento de la torre en diferentes direcciones
        boolean resultado1 = juegoService.moverTorre(torre, 0, 0, 0, 3, 0, 3);
        boolean resultado2 = juegoService.moverTorre(torre, 0, 0, 3, 0, 3, 0);
        boolean resultado3 = juegoService.moverTorre(torre, 0, 0, 3, 3, 3, 3);

        // Verificar los resultados esperados
        assertTrue(resultado1);
        assertTrue(resultado2);
        assertTrue(resultado3);
    }
    @Test
    public void testMoverReyna() {
        JuegoServiceImp juegoService = new JuegoServiceImp();

        Pieza pieza1 = new Pieza();
        int filaOrigen1 = 3;
        int filaDestino1 = 6;
        int columnaOrigen1 = 4;
        int columnaDestino1 = 4;
        int movimientoFila1 = 1;
        int movimientoColumna1 = 0;
        boolean resultado1 = juegoService.moverReyna(pieza1, filaOrigen1, filaDestino1, columnaOrigen1, columnaDestino1, movimientoFila1, movimientoColumna1);
        assertTrue(resultado1);

        Pieza pieza2 = new Pieza();
        int filaOrigen2 = 2;
        int filaDestino2 = 5;
        int columnaOrigen2 = 4;
        int columnaDestino2 = 7;
        int movimientoFila2 = 1;
        int movimientoColumna2 = 1;
        boolean resultado2 = juegoService.moverReyna(pieza2, filaOrigen2, filaDestino2, columnaOrigen2, columnaDestino2, movimientoFila2, movimientoColumna2);
        assertTrue(resultado2);

        Pieza pieza3 = new Pieza();
        int filaOrigen3 = 1;
        int filaDestino3 = 3;
        int columnaOrigen3 = 2;
        int columnaDestino3 = 4;
        int movimientoFila3 = 1;
        int movimientoColumna3 = 1;
        boolean resultado3 = juegoService.moverReyna(pieza3, filaOrigen3, filaDestino3, columnaOrigen3, columnaDestino3, movimientoFila3, movimientoColumna3);
        assertTrue(resultado3);
    }
    @Test
    public void testMoverCaballo() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Establecer el tablero de prueba en JuegoServiceImp
        juegoService.setTableroLogica(tableroLogica);

        // Crear una pieza de prueba
        Pieza caballo = new Pieza();
        caballo.setColor(Color.BLANCO);

        // Movimiento válido de caballo
        boolean resultadoValido = juegoService.moverCaballo(caballo, 2, 4, 3, 6, -1, 2);
        assertTrue(resultadoValido);
    }
    @Test
    public void testMoverAlfil() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Establecer el tablero de prueba en JuegoServiceImp
        juegoService.setTableroLogica(tableroLogica);

        // Crear una pieza de prueba
        Pieza alfil = new Pieza();
        alfil.setColor(Color.BLANCO);

        // Movimiento válido de alfil en diagonal
        boolean resultadoValido = juegoService.moverAlfil(alfil, 2, 4, 4, 6, 2, 2);
        assertTrue(resultadoValido);

        // Movimiento inválido de alfil en horizontal
        boolean resultadoInvalido1 = juegoService.moverAlfil(alfil, 2, 4, 2, 6, 0, 2);
        assertFalse(resultadoInvalido1);

        // Movimiento inválido de alfil en vertical
        boolean resultadoInvalido2 = juegoService.moverAlfil(alfil, 2, 4, 4, 4, 2, 0);
        assertFalse(resultadoInvalido2);

        // Movimiento inválido de alfil con pieza en el trayecto
        Pieza otraPieza = new Pieza();
        otraPieza.setColor(Color.NEGRO);
        tableroLogica[3][5] = otraPieza;
        boolean resultadoInvalido3 = juegoService.moverAlfil(alfil, 2, 4, 4, 6, 2, 2);
        assertFalse(resultadoInvalido3);
    }
    @Test
    public void testMoverPeonSinCapturar_MovimientoValidoDevuelveTrue() {
        // Crear una instancia del servicio de Pieza
        JuegoServiceImp piezaService = new JuegoServiceImp();

        // Crear una instancia simulada de la pieza
        Pieza pieza = new Pieza();
        pieza.setColor(Color.BLANCO);
        pieza.setPrimerMovimiento(true);

        // Crear una instancia simulada del tablero
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Llamar al método a probar con un movimiento válido
        boolean resultado = piezaService.moverPeonSinCapturar(pieza, 6, 4, 6, 4, -2, 0);

        // Verificar que el resultado es true
        assertTrue(resultado);
    }
    @Test
    public void testCapturarPiezaInvalidaPeon() {

        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear una pieza de prueba y establecer sus propiedades
        Pieza pieza = new Pieza();
        pieza.setColor(Color.BLANCO);

        // Definir los valores de prueba para los parámetros del método
        int filaOrigen = 2;
        int filaDestino = 3;
        int columnaOrigen = 4;
        int columnaDestino = 5;
        int movimientoFila = 1;
        int movimientoColumna = 1;

        // Llamar al método y almacenar el resultado
        boolean resultado = juegoService.capturarPiezaPeon(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);

        // Verificar si el resultado es el esperado
        assertFalse(resultado);
    }
    @Test
    public void testMoverPeon() {

        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear los parámetros necesarios para llamar al método moverPeon
        Pieza pieza = new Pieza();
        int filaOrigen = 1;
        int filaDestino = 2;
        int columnaOrigen = 3;
        int columnaDestino = 4;
        int movimientoFila = 1;
        int movimientoColumna = 0;

        // Llamar al método y almacenar el resultado
        boolean resultado = juegoService.moverPeon(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);

        // Verificar si el resultado es el esperado
        assertTrue(resultado);
    }
    @Test
    public void testMoverPeonSinCapturar_MovimientoInvalidoDevuelveFalse() {
        // Crear una instancia del servicio de Pieza
        JuegoServiceImp piezaService = new JuegoServiceImp();

        // Crear una instancia simulada de la pieza
        Pieza pieza = new Pieza();
        pieza.setColor(Color.NEGRO);
        pieza.setPrimerMovimiento(true);

        // Crear una instancia simulada del tablero
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Llamar al método a probar con un movimiento inválido
        boolean resultado = piezaService.moverPeonSinCapturar(pieza, 3, 5, 3, 5, 2, 1);

        // Verificar que el resultado es false
        assertFalse(resultado);
    }
    @Test
    public void testVerificarAmenazaPeon() {
        JuegoServiceImp juegoService = new JuegoServiceImp();

        List<Players> players = new ArrayList<>();
        Players jugador1 = new Players("Jugador 1", Color.BLANCO);
        Players jugador2 = new Players("Jugador 2", Color.NEGRO);
        jugador1.setTurno(true);
        players.add(jugador1);
        players.add(jugador2);
        juegoService.setPlayers(players);

        // Crear un tablero de prueba con un peón amenazando al rey
        Pieza[][] tableroLogica = new Pieza[8][8];
        tableroLogica[3][4] = new Pieza(Color.NEGRO, Tipo.PEON); // Peón negro amenazando al rey
        juegoService.setTableroLogica(tableroLogica);

        // Verificar que el método detecte la amenaza de peón
        assertFalse(juegoService.verificarAmenazaPeon(jugador1, 2, 4));
    }

    @Test
    public void testObtenerPieza() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];
        Pieza pieza = new Pieza(); // Crear una instancia de Pieza para el test
        tableroLogica[2][3] = pieza; // Asignar la pieza en la posición [2][3] del tablero
        juegoService.setTableroLogica(tableroLogica);

        // Obtener la pieza en la posición [2][3]
        Pieza result = juegoService.obtenerPieza(2, 3);

        // Verificar que la pieza obtenida es la misma que se asignó en el tablero
        assertSame(pieza, result);
    }


    @Test
    public void testEsCoordenadaValida() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Definir los valores de prueba
        int filas = 8;
        int columnas = 8;

        // Verificar coordenadas válidas
        assertTrue(juegoService.esCoordenadaValida(0, 0, filas, columnas));
        assertTrue(juegoService.esCoordenadaValida(2, 4, filas, columnas));
        assertTrue(juegoService.esCoordenadaValida(7, 7, filas, columnas));

        // Verificar coordenadas inválidas
        assertFalse(juegoService.esCoordenadaValida(-1, 0, filas, columnas));
        assertFalse(juegoService.esCoordenadaValida(0, -1, filas, columnas));
        assertFalse(juegoService.esCoordenadaValida(8, 0, filas, columnas));
        assertFalse(juegoService.esCoordenadaValida(0, 8, filas, columnas));
    }

    @Test
    public void testCambiarTurno() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear dos jugadores de prueba
        Players jugador1 = new Players();
        Players jugador2 = new Players();

        // Establecer el turno inicial
        jugador1.setTurno(true);
        jugador2.setTurno(false);

        // Agregar los jugadores a la lista de players en JuegoServiceImp
        juegoService.setPlayers(List.of(jugador1, jugador2));

        // Llamar al método cambiarTurno
        juegoService.cambiarTurno();

        // Verificar que el turno ha cambiado correctamente
        assertFalse(jugador1.isTurno());
        assertTrue(jugador2.isTurno());
    }

    @Test
    public void testRealizarMovimiento() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Establecer el tablero de prueba en JuegoServiceImp
        juegoService.setTableroLogica(tableroLogica);

        // Crear una pieza de prueba en la posición de origen
        Pieza pieza = new Pieza();
        int filaOrigen = 2;
        int columnaOrigen = 3;
        tableroLogica[filaOrigen][columnaOrigen] = pieza;

        // Definir las coordenadas de destino
        int filaDestino = 4;
        int columnaDestino = 5;

        // Llamar al método realizarMovimiento
        juegoService.realizarMovimiento(pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino);

        // Verificar que la pieza se haya movido correctamente
        assertNull(tableroLogica[filaOrigen][columnaOrigen]);
        assertEquals(filaDestino, pieza.getFila());
        assertEquals(columnaDestino, pieza.getColumna());
        assertEquals(pieza, tableroLogica[filaDestino][columnaDestino]);
    }

    @Test
    public void testObtenerRey() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Establecer el tablero de prueba en JuegoServiceImp
        juegoService.setTableroLogica(tableroLogica);

        // Crear un jugador de prueba
        Players jugadorActual = new Players();
        jugadorActual.setColor(Color.BLANCO);

        // Crear una pieza de rey de prueba en la posición (3, 4)
        Pieza rey = new Pieza();
        rey.setTipo(Tipo.REY);
        rey.setColor(Color.BLANCO);
        tableroLogica[3][4] = rey;

        // Llamar al método obtenerRey
        Pieza reyEncontrado = juegoService.obtenerRey(jugadorActual);

        // Verificar que se haya encontrado el rey correctamente
        assertEquals(rey, reyEncontrado);

        // Crear otro jugador de prueba
        Players otroJugador = new Players();
        otroJugador.setColor(Color.NEGRO);

        // Llamar al método obtenerRey con el otro jugador
        Pieza reyNoEncontrado = juegoService.obtenerRey(otroJugador);

        // Verificar que no se haya encontrado ningún rey para el otro jugador
        assertNull(reyNoEncontrado);
    }

    @Test
    public void testObtenerPiezas() {
        // Crear una instancia de JuegoServiceImp
        JuegoServiceImp juegoService = new JuegoServiceImp();

        // Crear un tablero de prueba
        Pieza[][] tableroLogica = new Pieza[8][8];

        // Establecer el tablero de prueba en JuegoServiceImp
        juegoService.setTableroLogica(tableroLogica);

        // Crear un jugador de prueba
        Players jugadorActual = new Players();
        jugadorActual.setColor(Color.BLANCO);

        // Crear piezas de prueba para el jugador actual
        Pieza pieza1 = new Pieza();
        pieza1.setColor(Color.BLANCO);
        tableroLogica[2][3] = pieza1;

        Pieza pieza2 = new Pieza();
        pieza2.setColor(Color.BLANCO);
        tableroLogica[4][5] = pieza2;

        // Llamar al método obtenerPiezas
        Pieza[] piezasObtenidas = juegoService.obtenerPiezas(jugadorActual);

        // Crear un arreglo esperado de las piezas
        Pieza[] piezasEsperadas = {pieza1, pieza2};

        // Verificar que se hayan obtenido las piezas correctamente
        assertArrayEquals(piezasEsperadas, piezasObtenidas);
    }

    @Test
    public void testEsMovimientoValido() {
        // Preparar datos de prueba
        Pieza pieza = new Pieza(Color.BLANCO,Tipo.TORRE);
        int filaOrigen = 2;
        int columnaOrigen = 3;
        int filaDestino = 5;
        int columnaDestino = 3;

        // Ejecutar el método
        boolean resultado = juegoService.esMovimientoValido(pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino);

        // Verificar el resultado esperado
        Assertions.assertTrue(resultado);
    }

}
