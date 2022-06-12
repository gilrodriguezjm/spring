package es.taw.springsalidos.controller;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.service.PersonaService;
import es.taw.springsalidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("administrador")
public class PersonaController {

    //Declaracion de los Services + Getter & Setter


    //PERSONA
    protected PersonaService personaService;

    public PersonaService getPersonaService() {
        return personaService;
    }

    @Autowired
    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }




    //PRODUCTO

    protected ProductoService productoService;

    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }





    //-------------------------------------------------------------------
    //Mapeo

    //LISTAR PERSONAS
    @GetMapping("/")
    public String doListarPersonas (Model model) {

        List<PersonaDTO> listaPersonas = this.personaService.listarTodasPersonas();
        List<ProductoDTO> listaProductos = this.productoService.getAllProductos();
        model.addAttribute("listaPersonas", listaPersonas);
        model.addAttribute("listaProductos", listaProductos);
        return "administrador";
    }

    //EDITAR PERSONAS
    @GetMapping("/{id}/editarPersona")
    public String doMostrarEditar (@PathVariable("id") Integer id,
                            Model model) {

        PersonaDTO personaDTO = this.personaService.findPersonaByID(id);

        model.addAttribute("persona_editar", personaDTO);

        return "editarPersona";
    }


    @PostMapping("/actualizarPersona")
    public String doEditar(@ModelAttribute("persona") PersonaDTO personaDTO, Model model)  {


        this.personaService.actualizarPersona(personaDTO);
        String message = "Persona Actualizada";
        model.addAttribute("Control", message);
        return "redirect:/administrador/";
    }


    //BORRAR PERSONAS
    @GetMapping("/{id}/borrarPersona")
    public String doBorrarPersona (@PathVariable("id") Integer id) {
        this.personaService.borrarPersona(id);
        return "redirect:/administrador/";
    }



}