package com.monitorllantas.controller;

import com.monitorllantas.model.LlantaDTO;
import com.monitorllantas.service.LlantaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController {

    private final LlantaService llantaService;

    public DashboardController(LlantaService llantaService) {
        this.llantaService = llantaService;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(HttpSession session, Model model) {
        model.addAttribute("nombreCompleto", session.getAttribute("usuarioNombreCompleto"));
        model.addAttribute("llantas", llantaService.obtenerTodas());
        model.addAttribute("tipos", llantaService.obtenerTiposDisponibles());
        return "dashboard";
    }

    @GetMapping("/dashboard/llantas")
    @ResponseBody
    public List<LlantaDTO> filtrarLlantas(@RequestParam(required = false) String tipo) {
        return llantaService.obtenerPorTipo(tipo);
    }
}
