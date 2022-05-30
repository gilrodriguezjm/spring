package es.taw.springsalidos.controller;

import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    protected PersonaService personaService;

    public PersonaService getPersonaService() {
        return personaService;
    }
    @Autowired
    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }


    @PostMapping("/iniciarSesion")
    public String doIniciarSesion(Model model, HttpSession session,
                                  @RequestParam("email") String email,
                                  @RequestParam("pass") String pass){

        PersonaDTO persona = this.personaService.comprobarCredenciales(email, pass);

        session.setAttribute("persona", persona);
        if (persona == null) {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "index";
        } else if (persona.getRol().equals("Administrador")) {
            //Enviar a tu controller
            return "redirect:/";
        } else if (persona.getRol().equals("Analista")) {
            return "redirect:/analista/";
        } else if (persona.getRol().equals("Marketing")) {
            //Enviar a tu controller
            return "redirect:/";
        } else {
            //Enviar a tu controller
            return "redirect:/usuario/";
        }
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
