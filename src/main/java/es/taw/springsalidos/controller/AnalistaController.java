package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
        PersonaEntity persona = (PersonaEntity) session.getAttribute("persona");

        List <AnalisisEntity> listaAnalisis = this.analistaRepository.findAllByPersonaByPersonaId(persona);

        model.addAttribute("analisis", listaAnalisis);

        return "analista";
    }

}
