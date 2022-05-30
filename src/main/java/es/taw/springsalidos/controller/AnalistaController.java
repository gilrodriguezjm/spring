package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
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

    public AnalistaRepository getAnalistaRepository() {
        return analistaRepository;
    }

    @Autowired
    public void setAnalistaRepository(AnalistaRepository analistaRepository) {
        this.analistaRepository = analistaRepository;
    }

    @GetMapping("/")
    public String doListarInformes (Model model, HttpSession session) {
        PersonaEntity persona = (PersonaEntity)session.getAttribute("persona");

        List <AnalisisEntity> listaAnalisis = this.analistaRepository.findAllByPersonaByPersonaId(persona);

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

        PersonaEntity persona = (PersonaEntity)session.getAttribute("persona");

        AnalisisEntity analisisEntity = new AnalisisEntity();

        analisisEntity.setDescripcion("AUTO");
        analisisEntity.setTabla(tabla);
        analisisEntity.setColumna(columna);
        analisisEntity.setOrden(orden);
        analisisEntity.setFechaInicio(fechaInicio);
        analisisEntity.setFechaFinal(fechaFinal);
        analisisEntity.setPersonaByPersonaId(persona);

        this.analistaRepository.save(analisisEntity);

        return "redirect:/analista/";
    }

    @GetMapping("/{id}/borrarInforme")
    public String doBorrar (@PathVariable("id") Integer id) {
        this.analistaRepository.deleteById(id);
        return "redirect:/analista/";
    }

    @GetMapping("/{id}/editarInforme")
    public String doEditar (@PathVariable("id") Integer id,
                            Model model) {

        AnalisisEntity analisis = this.analistaRepository.findById(id).orElse(null);

        String informe;
        if (analisis.getTabla() == 0)
            informe = "Personas";
        else
            informe = "Producto";

        model.addAttribute("informe", informe);
        model.addAttribute("analisis", analisis.toDTO());

        return "editarInforme";
    }

    @PostMapping("/actualizarInforme")
    public String doActualizarInforme(@ModelAttribute("analisis") AnalisisDTO analisis) {



        return "redirect:/analista/";
    }
}
