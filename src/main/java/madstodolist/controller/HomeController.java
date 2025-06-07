package madstodolist.controller;

import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UsuarioRepository usuarioRepository;

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

}
