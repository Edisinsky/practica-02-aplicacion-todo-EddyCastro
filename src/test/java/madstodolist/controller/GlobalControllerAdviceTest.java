package madstodolist.controller;

import madstodolist.dto.UsuarioData;
import madstodolist.service.UsuarioService;
import madstodolist.authentication.ManagerUserSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalControllerAdviceTest {

    private GlobalControllerAdvice globalControllerAdvice;
    protected UsuarioService usuarioService;
    protected ManagerUserSession managerUserSession;
    private HttpSession session;

    @BeforeEach
    public void setup() {
        usuarioService = mock(UsuarioService.class);
        managerUserSession = mock(ManagerUserSession.class);
        session = mock(HttpSession.class);

        globalControllerAdvice = new GlobalControllerAdvice();

        // Inyección manual de dependencias (ya que no usamos @SpringBootTest)
        globalControllerAdvice.usuarioService = usuarioService;
        globalControllerAdvice.managerUserSession = managerUserSession;
    }

    @Test
    public void testAgregarUsuarioSesion_conIdUsuarioEnSesion() {
        // Given
        Long idUsuario = 1L;
        UsuarioData usuarioData = new UsuarioData();
        usuarioData.setId(idUsuario);
        usuarioData.setNombre("Eddy");

        when(session.getAttribute("idUsuarioLogeado")).thenReturn(idUsuario);
        when(usuarioService.findById(idUsuario)).thenReturn(usuarioData);

        // When
        UsuarioData resultado = globalControllerAdvice.agregarUsuarioSesion(session);

        // Then
        assertNotNull(resultado);
        assertEquals("Eddy", resultado.getNombre());
        verify(usuarioService).findById(idUsuario);
    }

    @Test
    public void testAgregarUsuarioSesion_sinIdUsuarioEnSesion() {
        // Given
        when(session.getAttribute("idUsuarioLogeado")).thenReturn(null);

        // When
        UsuarioData resultado = globalControllerAdvice.agregarUsuarioSesion(session);

        // Then
        assertNull(resultado);
        verify(usuarioService, never()).findById(any());
    }
}
