package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.service.AnalisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("analista")
public class AnalistaController {

    private AnalistaRepository analistaRepository;
    private PersonaRepository personaRepository;
    private AnalisisService analisisService;

    public AnalistaRepository getAnalistaRepository() {
        return analistaRepository;
    }

    @Autowired
    public void setAnalistaRepository(AnalistaRepository analistaRepository) {
        this.analistaRepository = analistaRepository;
    }

    public PersonaRepository getPersonaRepository() { return personaRepository; }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public AnalisisService getAnalisisService() {
        return analisisService;
    }

    @Autowired
    public void setAnalisisService(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @GetMapping("/")
    public String doListarInformes (Model model, HttpSession session) {
        PersonaDTO persona = (PersonaDTO)session.getAttribute("persona");

        List <AnalisisDTO> listaAnalisis = this.analisisService.listarInformes(persona);

        model.addAttribute("analisis", listaAnalisis);

        return "analista";
    }

    @GetMapping("/generador")
    public String doGenerador () {
        return "generarInforme";
    }

    @PostMapping("/generarInforme")
    public String doGenerarInforme (Model model,
                                    @RequestParam("tabla") String tabla) {

        String informe;
        if (tabla.equals("0"))
            informe = "Informe sobre PERSONAS";
        else
            informe = "Informe sobre PRODUCTOS";

        model.addAttribute("tabla", tabla);
        model.addAttribute("informe", informe);
        return "generarInforme";
    }

    @PostMapping("/guardarInforme")
    public String doGuardarInforme (HttpSession session,
                                    @RequestParam("tabla") int tabla,
                                    @RequestParam("columna") int columna,
                                    @RequestParam("orden") int orden,
                                    @RequestParam("fIni") String fIni,
                                    @RequestParam("fFin") String fFin) {

        java.sql.Date fechaInicio = java.sql.Date.valueOf(fIni);
        java.sql.Date fechaFinal = java.sql.Date.valueOf(fFin);

        PersonaDTO personaDTO = (PersonaDTO)session.getAttribute("persona");
        String descripcion = this.analisisService.generarDescripcion(tabla, columna, orden);

        this.analisisService.crearInforme(descripcion, tabla, columna, orden, fechaInicio, fechaFinal, personaDTO);

        return "redirect:/analista/";
    }

    @GetMapping("/{id}/borrarInforme")
    public String doBorrar (@PathVariable("id") Integer id) {
        this.analisisService.borrarAnalisis(id);
        return "redirect:/analista/";
    }

    @GetMapping("/{id}/editarInforme")
    public String doEditar (@PathVariable("id") Integer id,
                            Model model) {

        AnalisisDTO analisisDTO = this.analisisService.findAnalisisById(id);

        String informe;
        if (analisisDTO.getTabla() == 0)
            informe = "Personas";
        else
            informe = "Producto";

        model.addAttribute("informe", informe);
        model.addAttribute("analisis", analisisDTO);

        return "editarInforme";
    }

    @PostMapping("/actualizarInforme")
    public String doActualizarInforme(@ModelAttribute("analisis") AnalisisDTO analisisDTO) {

        this.analisisService.actualizarInforme(analisisDTO);

        return "redirect:/analista/";
    }
}
