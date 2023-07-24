package com.example.Backend.service.imp;

import com.example.Backend.domain.MovimientoResultado;
import com.example.Backend.domain.Players;
import com.example.Backend.domain.RegistrarMovimientos;
import com.example.Backend.domain.Tablero;
import com.example.Backend.domain.pieces.Color;
import com.example.Backend.domain.pieces.Pieza;
import com.example.Backend.domain.pieces.Tipo;
import com.example.Backend.repository.PiezaRepository;
import com.example.Backend.repository.PlayersRepository;
import com.example.Backend.repository.RegistrarMovimientosRepository;
import com.example.Backend.repository.TableroRepository;
import com.example.Backend.service.IJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuegoServiceImp implements IJuegoService {

    //REPOSITORIOS
    @Autowired
    private PiezaRepository piezaRepository;
    @Autowired
    private TableroRepository tableroRepository;
    @Autowired
    private PlayersRepository playersRepository;
    @Autowired
    private RegistrarMovimientosRepository registrarMovimientosRepository;

    //ATRIBUTOS
    Pieza[][] tableroLogica = new Pieza[8][8];
    List<Pieza> piezas = new ArrayList<>(64);
    List<Players> players = new ArrayList<>(2);
    List<RegistrarMovimientos> movimientos = new ArrayList<>();
    Tablero tablero = new Tablero();

    //METODOS
    @Override
    public Pieza[][] iniciarJuego()
    {
        // Si el tablero tiene piezas, eliminar todas las piezas que contenga
        if (tableroLogica != null){
            for (int fila = 0; fila < tableroLogica.length; fila++) {
                for (int columna = 0; columna < tableroLogica[fila].length; columna++) {
                    tableroLogica[fila][columna] = null;
                }
            }
        }

        // Si la lista de jugadores tiene al menos cargado un jugador, eliminar todos los jugadores de la lista
        if (players.size() >= 0) players.clear();

        // Si la lista de piezas tiene al menos cargada una pieza, eliminar todas las piezas de la lista
        if (piezas != null){
            for (int i = 0; i < piezas.size(); i++) piezas.remove(i);
        }

        // Si hay un tablero creado, borrarlo y crear uno nuevo
        if (tablero != null) tablero = new Tablero();

        // Crear jugadores y asignarlos en el tablero
        Players player1 = new Players("Jugador 1", Color.BLANCO, true, tablero);
        Players player2 = new Players("Jugador 2", Color.NEGRO, false, tablero);
        players.add(player1);
        players.add(player2);

        tablero.setJugadores(players);

        // Piezas blancas
        tableroLogica[0][0] = new Pieza(true, Color.NEGRO, Tipo.TORRE, 0, 0, tablero);
        tableroLogica[0][1] = new Pieza(true, Color.NEGRO, Tipo.CABALLO, 0, 1, tablero);
        tableroLogica[0][2] = new Pieza(true, Color.NEGRO, Tipo.ALFIL, 0, 2, tablero);
        tableroLogica[0][3] = new Pieza(true, Color.NEGRO, Tipo.REINA, 0, 3, tablero);
        tableroLogica[0][4] = new Pieza(true, Color.NEGRO, Tipo.REY, 0, 4, tablero);
        tableroLogica[0][5] = new Pieza(true, Color.NEGRO, Tipo.ALFIL, 0, 5, tablero);
        tableroLogica[0][6] = new Pieza(true, Color.NEGRO, Tipo.CABALLO, 0, 6, tablero);
        tableroLogica[0][7] = new Pieza(true, Color.NEGRO, Tipo.TORRE, 0, 7, tablero);
        for (int i = 0; i < 8; i++) {
            tableroLogica[1][i] = new Pieza(true, Color.NEGRO, Tipo.PEON, 1, i, tablero);
        }

        // Piezas negras
        tableroLogica[7][0] = new Pieza(true, Color.BLANCO, Tipo.TORRE, 7, 0, tablero);
        tableroLogica[7][1] = new Pieza(true, Color.BLANCO, Tipo.CABALLO, 7, 1, tablero);
        tableroLogica[7][2] = new Pieza(true, Color.BLANCO, Tipo.ALFIL, 7, 2, tablero);
        tableroLogica[7][3] = new Pieza(true, Color.BLANCO, Tipo.REINA, 7, 3, tablero);
        tableroLogica[7][4] = new Pieza(true, Color.BLANCO, Tipo.REY, 7, 4, tablero);
        tableroLogica[7][5] = new Pieza(true, Color.BLANCO, Tipo.ALFIL, 7, 5, tablero);
        tableroLogica[7][6] = new Pieza(true, Color.BLANCO, Tipo.CABALLO, 7, 6, tablero);
        tableroLogica[7][7] = new Pieza(true, Color.BLANCO, Tipo.TORRE, 7, 7, tablero);
        for (int i = 0; i < 8; i++) {
            tableroLogica[6][i] = new Pieza(true, Color.BLANCO, Tipo.PEON, 6, i, tablero);
        }

        for (Pieza[] fila : tableroLogica) {
            for (Pieza pieza : fila) {
                if (pieza != null) {
                    piezas.add(pieza);
                }
            }
        }

        return tableroLogica;
    }

    public List<Players> getPlayers() {
        return players;
    }
    public void setPlayers(List<Players> players) {
        this.players = players;
    }
    public List<Pieza> getPiezas() {
        return piezas;
    }
    public Pieza[][] obtenerTablero(){
        return tableroLogica;
    }
    public void setTableroLogica(Pieza[][] tableroLogica) {
        this.tableroLogica = tableroLogica;
    }
    public void setTableroRepository(TableroRepository tableroRepository) {
        this.tableroRepository = tableroRepository;
    }

    public void setPiezaRepository(PiezaRepository piezaRepository) {
        this.piezaRepository = piezaRepository;
    }

    public void setRegistrarMovimientosRepository(RegistrarMovimientosRepository registrarMovimientosRepository) {
        this.registrarMovimientosRepository = registrarMovimientosRepository;
    }

    public void setPlayersRepository(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }
    public TableroRepository getTableroRepository() {
        return tableroRepository;
    }

    public PiezaRepository getPiezaRepository() {
        return piezaRepository;
    }

    public RegistrarMovimientosRepository getRegistrarMovimientosRepository() {
        return registrarMovimientosRepository;
    }

    public PlayersRepository getPlayersRepository() {
        return playersRepository;
    }

    public MovimientoResultado mover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        Players jugadorActual = obtenerJugadorActual();
        Players jugadorOponente = obtenerJugadorOponente();
        Pieza pieza = tableroLogica[filaOrigen][columnaOrigen];

        if (!validarMovimiento(jugadorActual, pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
            if (estadoJaqueMate(jugadorActual))
                return new MovimientoResultado(false, "Movimiento inválido: el rey está en jaque mate. Por ende no puedes mover mas fichas de la partida actual");
            return new MovimientoResultado(false, "Movimiento inválido");
        }

        realizarMovimiento(pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino);
        if (estadoJaque(jugadorActual)) {
            // Deshacer el movimiento si el rey sigue en jaque
            realizarMovimiento(pieza, filaDestino, columnaDestino, filaOrigen, columnaOrigen);
            if (estadoJaqueMate(obtenerJugadorActual()))
                return new MovimientoResultado(true, "¡Jaque mate! El jugador " + jugadorOponente.getColor() + " ha ganado. Por ende no puedes mover mas fichas de la partida actual");
            return new MovimientoResultado(false, "Movimiento inválido: el rey sigue en jaque");
        }

        cambiarTurno();

        if (estadoJaqueMate(obtenerJugadorActual())) {
            String mensaje = "¡Jaque mate! El jugador " + jugadorActual.getColor() + " ha ganado.";
            RegistrarMovimientos rjaque = new RegistrarMovimientos(convertidorMovimientos(filaOrigen, columnaOrigen), convertidorMovimientos(filaDestino, columnaDestino), tablero);
            movimientos.add(rjaque);
            return new MovimientoResultado(true, mensaje, movimientos);
        } else if (estadoJaque(obtenerJugadorActual())) {
            String mensaje = "¡Jaque! El jugador " + obtenerJugadorActual().getColor() + " está en jaque.";
            return new MovimientoResultado(true, mensaje);
        }

        RegistrarMovimientos r = new RegistrarMovimientos(convertidorMovimientos(filaOrigen, columnaOrigen), convertidorMovimientos(filaDestino, columnaDestino), tablero);
        movimientos.add(r);

        return new MovimientoResultado(true, "Movimiento realizado con éxito", r);
    }

    public String convertidorMovimientos(int x1, int y1) {
        String x = "";
        String y = "";
        switch (x1) {
            case 0:
                x = "A";
                break;
            case 1:
                x = "B";
                break;
            case 2:
                x = "C";
                break;
            case 3:
                x = "D";
                break;
            case 4:
                x = "E";
                break;
            case 5:
                x = "F";
                break;
            case 6:
                x = "G";
                break;
            case 7:
                x = "H";
                break;
        }
        switch (y1) {
            case 0:
                y = "8";
                break;
            case 1:
                y = "7";
                break;
            case 2:
                y = "6";
                break;
            case 3:
                y = "5";
                break;
            case 4:
                y = "4";
                break;
            case 5:
                y = "3";
                break;
            case 6:
                y = "2";
                break;
            case 7:
                y = "1";
                break;
        }
        return x + y;

    }

    public Players obtenerJugadorOponente() {
        return players.get(0).isTurno() ? players.get(1) : players.get(0);
    }

    public boolean verificarAmenazaDiagonal(Players jugadorActual, int filaRey, int columnaRey) {
        // Verificar amenaza de alfiles y reinas en diagonal
        int[][] direcciones = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] direccion : direcciones) {
            int filaActual = filaRey + direccion[0];
            int columnaActual = columnaRey + direccion[1];

            while (filaActual >= 0 && filaActual < 8 && columnaActual >= 0 && columnaActual < 8) {
                Pieza pieza = tableroLogica[filaActual][columnaActual];
                if (pieza != null) {
                    if (pieza.getColor() != jugadorActual.getColor()) {
                        if (pieza.getTipo() == Tipo.ALFIL || pieza.getTipo() == Tipo.REINA) {
                            return true;
                        }
                    }
                    break;
                }
                filaActual += direccion[0];
                columnaActual += direccion[1];
            }
        }
        return false;
    }

    public boolean verificarAmenazaLineal(Players jugadorActual, int filaRey, int columnaRey) {
        // Verificar amenaza de torres y reinas en línea recta
        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direccion : direcciones) {
            int filaActual = filaRey + direccion[0];
            int columnaActual = columnaRey + direccion[1];

            while (filaActual >= 0 && filaActual < 8 && columnaActual >= 0 && columnaActual < 8) {
                Pieza pieza = tableroLogica[filaActual][columnaActual];
                if (pieza != null) {
                    if (pieza.getColor() != jugadorActual.getColor()) {
                        if (pieza.getTipo() == Tipo.TORRE || pieza.getTipo() == Tipo.REINA) {
                            return true;
                        }
                    }
                    break;
                }
                filaActual += direccion[0];
                columnaActual += direccion[1];
            }
        }

        return false;
    }

    public boolean verificarAmenazaCaballo(Players jugadorActual, int filaRey, int columnaRey) {
        // Verificar amenaza de caballos
        int[][] movimientosCaballo = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] movimiento : movimientosCaballo) {
            int filaActual = filaRey + movimiento[0];
            int columnaActual = columnaRey + movimiento[1];

            if (filaActual >= 0 && filaActual < 8 && columnaActual >= 0 && columnaActual < 8) {
                Pieza pieza = tableroLogica[filaActual][columnaActual];
                if (pieza != null && pieza.getColor() != jugadorActual.getColor() && pieza.getTipo() == Tipo.CABALLO) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean verificarAmenazaPeon(Players jugadorActual, int filaRey, int columnaRey) {
        // Verificar amenaza de peones
        int direccion = (jugadorActual.getColor() == Color.BLANCO) ? 1 : -1;
        int[][] movimientosPeon = {{direccion, -1}, {direccion, 1}};

        for (int[] movimiento : movimientosPeon) {
            int filaActual = filaRey + movimiento[0];
            int columnaActual = columnaRey + movimiento[1];

            if (filaActual >= 0 && filaActual < 8 && columnaActual >= 0 && columnaActual < 8) {
                Pieza pieza = tableroLogica[filaActual][columnaActual];
                if (pieza != null && pieza.getColor() != jugadorActual.getColor() && pieza.getTipo() == Tipo.PEON) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean verificarAmenazaRey(Players jugadorActual, int filaRey, int columnaRey) {
        // Verificar amenaza de rey
        int[][] movimientosRey = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, /* REY */ {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] movimiento : movimientosRey) {
            int filaActual = filaRey + movimiento[0];
            int columnaActual = columnaRey + movimiento[1];

            if (filaActual >= 0 && filaActual < 8 && columnaActual >= 0 && columnaActual < 8) {
                Pieza pieza = tableroLogica[filaActual][columnaActual];
                if (pieza != null && pieza.getColor() != jugadorActual.getColor() && pieza.getTipo() == Tipo.REY) {
                    return true;
                }
            }
        }

        return false;
    }

    //TODO: TEST estadoJaque (hecho test de ReyNulo, faltan otros 2)
    public boolean estadoJaque(Players jugadorActual) {
        Pieza rey = obtenerRey(jugadorActual);
        if (rey == null) {
            return false;
        }

        int filaRey = rey.getFila();
        int columnaRey = rey.getColumna();

        // Verificar amenaza de alfiles y reinas en diagonal
        if (verificarAmenazaDiagonal(jugadorActual, filaRey, columnaRey)) {
            return true;
        }

        // Verificar amenaza de torres y reinas en línea recta
        if (verificarAmenazaLineal(jugadorActual, filaRey, columnaRey)) {
            return true;
        }

        // Verificar amenaza de caballos
        if (verificarAmenazaCaballo(jugadorActual, filaRey, columnaRey)) {
            return true;
        }

        // Verificar amenaza de peones
        if (verificarAmenazaPeon(jugadorActual, filaRey, columnaRey)) {
            return true;
        }

        // Verificar amenaza de rey
        if (verificarAmenazaRey(jugadorActual, filaRey, columnaRey)) {
            return true;
        }

        return false;
    }

    //TODO: TEST estadoJaqueMate (hecho test de ReyNulo, faltan otros 2)
    public boolean estadoJaqueMate(Players jugadorActual) {
        Pieza rey = obtenerRey(jugadorActual);
        if (rey == null) {
            return false;
        }

        if (!estadoJaque(jugadorActual)) {
            return false;
        }

        Pieza[] piezas = obtenerPiezas(jugadorActual);
        for (Pieza pieza : piezas) {
            int filaOrigen = pieza.getFila();
            int columnaOrigen = pieza.getColumna();

            for (int filaDestino = 0; filaDestino < 8; filaDestino++) {
                for (int columnaDestino = 0; columnaDestino < 8; columnaDestino++) {
                    if (validarMovimiento(jugadorActual, pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {

                        Pieza piezaCapturada = tableroLogica[filaDestino][columnaDestino];
                        realizarMovimiento(pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino);

                        boolean sigueEnJaque = estadoJaque(jugadorActual);


                        realizarMovimiento(pieza, filaDestino, columnaDestino, filaOrigen, columnaOrigen);
                        tableroLogica[filaDestino][columnaDestino] = piezaCapturada;

                        if (!sigueEnJaque) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public Pieza[] obtenerPiezas(Players jugadorActual) {
        ArrayList<Pieza> piezas = new ArrayList<>();

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tableroLogica[fila][columna];
                if (pieza != null && pieza.getColor() == jugadorActual.getColor()) {
                    piezas.add(pieza);
                }
            }
        }
        return piezas.toArray(new Pieza[0]);
    }

    public boolean esMovimientoValido(Pieza pieza, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        int movimientoFila = filaDestino - filaOrigen;
        int movimientoColumna = columnaDestino - columnaOrigen;

        switch (pieza.getTipo()) {
            case TORRE:
                return moverTorre(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            case PEON:
                return moverPeon(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            case ALFIL:
                return moverAlfil(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            case CABALLO:
                return moverCaballo(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            case REINA:
                return moverReyna(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            case REY:
                return moverRey(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
            default:
                return false;
        }
    }

    public Pieza obtenerRey(Players jugadorActual) {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = tableroLogica[fila][columna];
                if (pieza != null && pieza.getTipo() == Tipo.REY && pieza.getColor() == jugadorActual.getColor()) {
                    return pieza;
                }
            }
        }
        return null;
    }

    public boolean validarJugadorActual(Players jugadorActual, Pieza pieza) {
        if (pieza != null && jugadorActual != null && pieza.getColor() == jugadorActual.getColor()) {
            return true;
        }
        return false;
    }

    public boolean validarDestino(int filaDestino, int columnaDestino) {
        if (filaDestino >= 0 && filaDestino <= 7 && columnaDestino >= 0 && columnaDestino <= 7) {
            return true;
        }
        return false;
    }

    //TODO: TEST validarMovimiento
    public boolean validarMovimiento(Players jugadorActual, Pieza pieza, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        if (!validarJugadorActual(jugadorActual, pieza)) {
            return false;
        }

        if (!validarDestino(filaDestino, columnaDestino)) {
            return false;
        }

        if (tableroLogica[filaDestino][columnaDestino] != null && tableroLogica[filaDestino][columnaDestino].getColor() == jugadorActual.getColor()) {
            return false;
        }

        return esMovimientoValido(pieza, filaOrigen, columnaOrigen, filaDestino, columnaDestino);
    }

    public void realizarMovimiento(Pieza pieza, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        tableroLogica[filaOrigen][columnaOrigen] = null;
        pieza.setFila(filaDestino);
        pieza.setColumna(columnaDestino);
        if (tableroLogica[filaDestino][columnaDestino] != null) {
            tableroLogica[filaDestino][columnaDestino] = null;
        }
        tableroLogica[filaDestino][columnaDestino] = pieza;
    }

    public Players obtenerJugadorActual() {
        // Buscar el jugador cuyo turno sea verdadero
        for (Players jugador : players) {
            if (jugador.isTurno()) {
                return jugador;
            }
        }

        return null; // No se encontró ningún jugador con el turno activo
    }

    public void cambiarTurno() {
        // Buscar el jugador cuyo turno sea verdadero
        for (Players jugador : players) {
            if (jugador.isTurno()) {
                jugador.setTurno(false);
            } else {
                jugador.setTurno(true);
            }
        }
    }

    @Override
    public List<Pieza> guardarPieza(List<Pieza> piezas) {
        return piezaRepository.saveAll(piezas);
    }

    @Override
    public boolean moverTorre(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (filaOrigen == filaDestino) { // Se ejecuta si el movimiento de la torre es horizontal
            if (movimientoColumna > 0) { // Se mueve a la derecha
                for (int i = 0; i < movimientoColumna; i++) {
                    int valorColumna = columnaOrigen + 1 + i;

                    if (tableroLogica[filaOrigen][valorColumna] != null) {   // Se ejecuta si detecta una pieza en el trayecto
                        if (pieza.getColor() == tableroLogica[filaOrigen][valorColumna].getColor()) return false;
                        else if (valorColumna != columnaDestino) return false;
                    }
                }
                return true;
            } else {  // Se mueve a la izquierda
                for (int i = 0; i > movimientoColumna; i--) {
                    int valorColumna = columnaOrigen - 1 + i;

                    if (tableroLogica[filaOrigen][valorColumna] != null) {   // Se ejecuta si detecta una pieza en el trayecto
                        if (pieza.getColor() == tableroLogica[filaOrigen][valorColumna].getColor()) return false;
                        else if (valorColumna != columnaDestino) return false;
                    }
                }
                return true;
            }
        } else if (columnaOrigen == columnaDestino) { // Se ejecuta si el movimiento de la torre es vertical
            if (movimientoFila > 0) {    // Se mueve hacia arriba
                for (int i = 0; i < movimientoFila; i++) {
                    int valorFila = filaOrigen + 1 + i;

                    if (tableroLogica[valorFila][columnaOrigen] != null) {   // Se ejecuta si detecta una pieza en el trayecto
                        if (pieza.getColor() == tableroLogica[valorFila][columnaOrigen].getColor()) return false;
                        else if (valorFila != filaDestino) return false;
                    }
                }
                return true;
            } else {  // Se mueve hacia abajo
                for (int i = 0; i > movimientoFila; i--) {
                    int valorFila = filaOrigen - 1 + i;

                    if (tableroLogica[valorFila][columnaOrigen] != null) {   // Se ejecuta si detecta una pieza en el trayecto
                        if (pieza.getColor() == tableroLogica[valorFila][columnaOrigen].getColor()) return false;
                        else if (valorFila != filaDestino) return false;
                    }
                }
                return true;
            }
        } else return false; //Se ejecuta si el movimiento no es valido para una torre
    }

    @Override
    public boolean moverPeon(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (movimientoColumna == 0) {
            return moverPeonSinCapturar(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
        } else if (Math.abs(movimientoColumna) == 1 && Math.abs(movimientoFila) == 1) {
            return capturarPiezaPeon(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna);
        } else {
            return false;
        }
    }

    public boolean moverPeonSinCapturar(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (pieza.getColor() == Color.BLANCO && movimientoFila > 0) return false;
        if (pieza.getColor() == Color.NEGRO && movimientoFila < 0) return false;

        if (Math.abs(movimientoFila) > 2 || movimientoFila == 0 || movimientoColumna != 0) return false;

        if (pieza.getColor() == Color.BLANCO && filaOrigen != 6) return false;
        if (pieza.getColor() == Color.NEGRO && filaOrigen != 1) return false;

        if (Math.abs(movimientoFila) == 2 && pieza.isPrimerMovimiento()) {
            int filaIntermedia = filaOrigen + movimientoFila / 2;
            if (movimientoFila > 0) {
                if (tableroLogica[filaIntermedia][columnaOrigen] != null) return false;
            } else {
                if (tableroLogica[filaIntermedia][columnaOrigen] != null) return false;
            }
        }

        if (tableroLogica[filaDestino][columnaDestino] != null) return false;

        return true;
    }

    public boolean capturarPiezaPeon(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (pieza.getColor() == Color.BLANCO && movimientoFila < 0) return false;
        if (pieza.getColor() == Color.NEGRO && movimientoFila > 0) return false;

        if (Math.abs(movimientoFila) != 1 || Math.abs(movimientoColumna) != 1) return false;

        Pieza piezaCapturada = tableroLogica[filaDestino][columnaDestino];
        if (piezaCapturada == null || piezaCapturada.getColor() == pieza.getColor()) return false;

        return true;
    }

    @Override
    public boolean moverRey(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (movimientoFila < -1 || movimientoFila > 1 || movimientoColumna < -1 || movimientoColumna > 1) return false;

        if (!moverReyna(pieza,filaOrigen,filaDestino,columnaOrigen,columnaDestino,movimientoFila,movimientoColumna)) return false;
        else return true;
    }

    @Override
    public boolean moverReyna(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (moverTorre(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna)) {
            return true;
        } else if (moverAlfil(pieza, filaOrigen, filaDestino, columnaOrigen, columnaDestino, movimientoFila, movimientoColumna)) {
            return true;
        }
        return false;
    }

    public boolean moverCaballo(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (movimientoFila > 2 || movimientoFila < -2 || movimientoFila == 0 || movimientoColumna > 2 || movimientoColumna < -2 || movimientoColumna == 0) return false;
        if (movimientoColumna < 0 || movimientoFila < 0) {   // Verifica que la pieza no se mueva la misma cantidad de filas y columnas
            int movRealColumna = 0;
            int movRealFila = 0;
            if (movimientoColumna < 0) movRealColumna = movimientoColumna * -1;
            if (movimientoFila < 0) movRealFila = movimientoFila * -1;
            if (movRealFila == movRealColumna) return false;
        }
        else if (movimientoFila == movimientoColumna) return false;

        if (tableroLogica[filaDestino][columnaDestino] != null) {   // Se ejecuta si detecta una pieza en el trayecto
            if (pieza.getColor() == tableroLogica[filaDestino][columnaDestino].getColor()) return false;
        }
        return true;
    }

    @Override
    public boolean moverAlfil(Pieza pieza, int filaOrigen, int filaDestino, int columnaOrigen, int columnaDestino, int movimientoFila, int movimientoColumna) {
        if (movimientoColumna == 0 || movimientoFila == 0) return false;

        if (movimientoColumna < 0 || movimientoFila < 0) {
            int movRealColumna = Math.abs(movimientoColumna);
            int movRealFila = Math.abs(movimientoFila);
            if (movRealFila != movRealColumna) return false;
        } else if (movimientoFila != movimientoColumna) return false;

        if (movimientoFila > 0) {
            if (movimientoColumna > 0) {
                for (int i = 0; i < movimientoFila; i++) {
                    int valorFila = filaOrigen + 1 + i;
                    int valorColumna = columnaOrigen + 1 + i;
                    if (tableroLogica[valorFila][valorColumna] != null) {
                        if (pieza.getColor() == tableroLogica[valorFila][valorColumna].getColor()) return false;
                        else if (valorFila != filaDestino || valorColumna != columnaDestino) return false;
                    }
                }
            } else {
                for (int i = 0; i < movimientoFila; i++) {
                    int valorFila = filaOrigen + 1 + i;
                    int valorColumna = columnaOrigen - 1 - i;
                    if (tableroLogica[valorFila][valorColumna] != null) {
                        if (pieza.getColor() == tableroLogica[valorFila][valorColumna].getColor()) return false;
                        else if (valorFila != filaDestino || valorColumna != columnaDestino) return false;
                    }
                }
            }
        } else {
            if (movimientoColumna > 0) {
                for (int i = 0; i > movimientoFila; i--) {
                    int valorFila = filaOrigen - 1 + i;
                    int valorColumna = columnaOrigen + 1 - i;
                    if (tableroLogica[valorFila][valorColumna] != null) {
                        if (pieza.getColor() == tableroLogica[valorFila][valorColumna].getColor()) return false;
                        else if (valorFila != filaDestino || valorColumna != columnaDestino) return false;
                    }
                }
            } else {
                for (int i = 0; i > movimientoFila; i--) {
                    int valorFila = filaOrigen - 1 + i;
                    int valorColumna = columnaOrigen - 1 + i;
                    if (tableroLogica[valorFila][valorColumna] != null) {
                        if (pieza.getColor() == tableroLogica[valorFila][valorColumna].getColor()) return false;
                        else if (valorFila != filaDestino || valorColumna != columnaDestino) return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean esCoordenadaValida(int x, int y, int filas, int columnas) {
        return x >= 0 && x < filas && y >= 0 && y < columnas;
    }

    public boolean estadoTablas(Pieza[][] tablero) {
        return false;
    }
    @Override
    public Pieza[][] guardarJuego(Pieza[][] piezas) {
        return piezas;
    }
    @Override
    public Pieza[][] actualizarTablero(Pieza[][] tableroActualizado) {
        return tableroLogica = tableroActualizado;
    }

    public Pieza[][] traerTableroConPiezasYJugadoresPorId(long id) {
        Tablero tablero = tableroRepository.findById(id).orElse(null);
        if (tablero != null) {
            Pieza[][] tableroLogica = new Pieza[8][8];
            // Obtener las piezas correspondientes al tablero
            piezas = piezaRepository.findByTablero(tablero);
            // Iterar sobre las piezas y asignarlas a la matriz según su posición
            for (Pieza pieza : piezas) {
                int fila = pieza.getFila();
                int columna = pieza.getColumna();
                tableroLogica[fila][columna] = pieza;
            }
            movimientos = registrarMovimientosRepository.findByTablero(tablero);
            // Obtener los jugadores correspondientes al tablero
            players = playersRepository.findByTablero(tablero);
            return tableroLogica;
        }
        return null;
    }

    public void guardarPlayersEnBase(List<Players> players) {
        playersRepository.saveAll(players);
    }

    @Override
    public Tablero guardarTableroEnBasedeDatos(Tablero tablero) {
        return tableroRepository.save(tablero);
    }

    @Override
    public Players guardarPlayerEnBaseDeDatos(Players player) {
        return playersRepository.save(player);
    }

    public List<RegistrarMovimientos> getMovimientosDeUnaPartida(long id) {
        Tablero tablero = tableroRepository.findById(id).orElse(null);
        if (tablero != null) {
            return registrarMovimientosRepository.findByTablero(tablero);
        }
        return null;
    }

    //TODO: TEST guardarTableroConPiezasYJugadoresEnBase
    public void guardarTableroConPiezasYJugadoresEnBase() {
        tableroRepository.save(tablero);
        piezaRepository.saveAll(piezas);
        for (Players player : players) {
            player.setTablero(tablero);
        }
        playersRepository.saveAll(players);
        registrarMovimientosRepository.saveAll(movimientos);
    }

    public Pieza obtenerPieza(int x1, int y1) {
        return tableroLogica[x1][y1];
    }
}