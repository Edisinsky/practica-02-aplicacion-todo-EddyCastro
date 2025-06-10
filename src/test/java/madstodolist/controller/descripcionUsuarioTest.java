package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class descripcionUsuarioTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    Model model;

    @InjectMocks
    HomeController homeController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void descripcionUsuariosDevuelveVistaYUsuarioEnModelo() {
        // Arrange
        Long id = 1L;
        Usuario usuario = new Usuario("ejemplo@email.com");
        usuario.setId(id);
        usuario.setNombre("Usuario de prueba");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Act
        String vista = homeController.descripcionUsuarios(id, model);

        // Assert
        verify(usuarioRepository).findById(id); // verifica que se haya llamado
        verify(model).addAttribute("usuario", usuario); // verifica que se añade al modelo
        assertEquals("descripcionUsuarios", vista); // nombre de la vista devuelta
    }


    @Test
    void descripcionUsuariosConIdInexistenteDevuelveVistaConUsuarioNull() {
        // Arrange
        Long idInexistente = 99L;

        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        String vista = homeController.descripcionUsuarios(idInexistente, model);

        // Assert
        verify(usuarioRepository).findById(idInexistente);
        verify(model).addAttribute("usuario", null);
        assertEquals("descripcionUsuarios", vista);
    }


}
