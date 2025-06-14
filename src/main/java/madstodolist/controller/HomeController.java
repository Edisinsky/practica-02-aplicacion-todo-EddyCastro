package madstodolist.controller;
import madstodolist.authentication.ManagerUserSession;
import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;
import madstodolist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public HomeController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/registrados")
    public String listaUsuarios(Model model) {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "registrados";
    }

    @GetMapping("/registrados/{id}")
    public String descripcionUsuarios( @PathVariable Long id,Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "descripcionUsuarios";
    }

}
