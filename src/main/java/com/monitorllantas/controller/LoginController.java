package com.monitorllantas.controller;

import com.monitorllantas.model.Usuario;
import com.monitorllantas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String raiz() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombre,
                                @RequestParam String pass,
                                HttpSession session,
                                Model model) {
        Optional<Usuario> usuario = usuarioService.autenticar(nombre, pass);

        if (usuario.isPresent()) {
            session.setAttribute("usuarioNombre", usuario.get().getNombre());
            session.setAttribute("usuarioNombreCompleto", usuario.get().getNombreCompleto());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
