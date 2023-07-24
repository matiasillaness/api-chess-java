package com.example.Backend;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.example.Backend.controllers.juegoController;
import com.example.Backend.domain.MovimientoResultado;
import com.example.Backend.domain.pieces.Pieza;
import com.example.Backend.service.imp.JuegoServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(juegoController.class)
public class JuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JuegoServiceImp servicioJuegoImpMock;

    @Test
    void testCrearPartida() throws Exception {
        Pieza[][] piezasMock = new Pieza[8][8];
        Mockito.when(servicioJuegoImpMock.iniciarJuego()).thenReturn(piezasMock);

        MvcResult result = mockMvc.perform(post("/crearPartida")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), statusCode);
        String contentType = result.getResponse().getContentType();
        assertEquals(MediaType.APPLICATION_JSON.toString(), contentType);
    }
    @Test
    void testGuardarPartidaEnBase_Success() throws Exception {
        mockMvc.perform(post("/guardar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(servicioJuegoImpMock).guardarTableroConPiezasYJugadoresEnBase();
    }
    @Test
    void testMover() throws Exception {
        int filaOrigen = 2;
        int columnaOrigen = 3;
        int filaDestino = 4;
        int columnaDestino = 5;
        MovimientoResultado resultadoMock = new MovimientoResultado(true,"Exito");
        Mockito.when(servicioJuegoImpMock.mover(filaOrigen, columnaOrigen, filaDestino, columnaDestino))
                .thenReturn(resultadoMock);
        MvcResult result = mockMvc.perform(post("/mover/{filaOrigen}/{columnaOrigen}/{filaDestino}/{columnaDestino}",
                        filaOrigen, columnaOrigen, filaDestino, columnaDestino))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), statusCode);
        String contentType = result.getResponse().getContentType();
        assertEquals(MediaType.APPLICATION_JSON.toString(), contentType);
    }
}
