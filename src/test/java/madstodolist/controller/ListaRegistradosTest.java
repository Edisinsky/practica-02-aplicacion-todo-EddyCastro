package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListaRegistradosTest {

    private HomeController homeController;
    private UsuarioRepository usuarioRepository;
    private Model model;

    @BeforeEach
    public void setup() {
        usuarioRepository = mock(UsuarioRepository.class);
        model = mock(Model.class);

        homeController = new HomeController(usuarioRepository); // Si tenés @Autowired, crea un constructor
    }

    @Test
    public void testListaUsuarios_retornaVistaYUsuariosEnModelo() {
        // Given
        Usuario u1 = new Usuario("u1@email.com");
        Usuario u2 = new Usuario("u2@email.com");
        List<Usuario> listaUsuarios = Arrays.asList(u1, u2);

        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        // When
        String vista = homeController.listaUsuarios(model);

        // Then
        assertEquals("registrados", vista);

        // Verifica que el modelo recibió la lista de usuarios con la clave "usuarios"
        verify(model).addAttribute("usuarios", listaUsuarios);
    }
}
