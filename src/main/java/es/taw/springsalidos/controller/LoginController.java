package es.taw.springsalidos.controller;

import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.dto.TransaccionDTO;
import es.taw.springsalidos.entity.InteresEntity;
import es.taw.springsalidos.service.PersonaService;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            return "redirect:/administrador/";
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

    @PostMapping("/registro")
    public String doRegistro    (  Model model,
                                HttpSession session,
                                @RequestParam("email_register") String email,
                                @RequestParam("pass_register") String pass,
                                @RequestParam("nombre") String nombre,
                                @RequestParam("apellidos") String apellidos,
                                @RequestParam("domicilio") String domicilio,
                                @RequestParam("ciudad") String ciudad,
                                @RequestParam("f_nacimiento") String f_nacimiento,
                                @RequestParam("sexo") String sexo,
                                @RequestParam("rol") String rol


                                )
                                {

        String StrError="";

        PersonaDTO personaDTO = new PersonaDTO();

        if(this.personaService.findPersonaByEmail(personaDTO.getEmail())!=null)
        {
            StrError="Email ya en uso";
            model.addAttribute("reg_error",StrError);
            return "redirect:/";
        }
        else {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha_nac = null;


            try {
                fecha_nac = formato.parse(f_nacimiento);
            } catch (ParseException ignored) {

            }


            assert fecha_nac != null;
            java.sql.Date dateSql = new java.sql.Date(fecha_nac.getYear(), fecha_nac.getMonth(), fecha_nac.getDay());


            personaDTO.setEmail(email);
            personaDTO.setPassword(pass);
            personaDTO.setNombre(nombre);
            personaDTO.setApellidos(apellidos);
            personaDTO.setCiudad(ciudad);
            personaDTO.setRol(rol);
            personaDTO.setMonedero(0.0);
            personaDTO.setFecha_nacimiento(dateSql);
            personaDTO.setDomicilio(domicilio);
            personaDTO.setSexo(sexo);


            this.personaService.actualizarPersona(personaDTO);
            session.setAttribute("persona", personaDTO);
            assert false;
            model.addAttribute("reg_error",StrError);

            return switch (personaDTO.getRol()) {
                case "Administrador" ->
                    //Enviar a tu controller
                        "redirect:/administrador/";
                case "Analista" -> "redirect:/analista/";
                case "Marketing" ->
                    //Enviar a tu controller
                        "redirect:/";
                default ->
                    //Enviar a tu controller
                        "redirect:/administrador/";
            };


        }
    }



    @GetMapping("/cerrarSesion")
    public String doCerrarSesion (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
