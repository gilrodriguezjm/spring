package es.taw.springsalidos.controller;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.dto.TransaccionDTO;
import es.taw.springsalidos.service.PersonaService;
import es.taw.springsalidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("administrador")
public class AdministradorController {

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
        List<TransaccionDTO> listaTransacciones = this.personaService.getAllTransaccion();

        model.addAttribute("listaPersonas", listaPersonas);
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("listaTransacciones", listaTransacciones);

        return "administrador";
    }




    //VER PERSONAS

    @GetMapping("/{id}/verPersona")
    public String doMostrarVerPersonas (@PathVariable("id") Integer id,
                                   Model model) {

        PersonaDTO personaDTO = this.personaService.findPersonaByID(id);

        model.addAttribute("persona_ver", personaDTO);

        return "verPersona";
    }






    //-----------------------------------------------------------


    //EDITAR PERSONAS
    @GetMapping("/{id}/editarPersona")
    public String doMostrarEditar (@PathVariable("id") Integer id,
                            Model model) {

        PersonaDTO personaDTO = this.personaService.findPersonaByID(id);

        model.addAttribute("persona_editar", personaDTO);

        return "editarPersona";
    }


    @PostMapping("/actualizarPersona")
    public String doEditar(@ModelAttribute("persona_editar") PersonaDTO personaDTO, Model model)  {


        this.personaService.actualizarPersona(personaDTO);
        String message = "Persona Actualizada";
        model.addAttribute("Control", message);
        return "redirect:/administrador/editarPersona/"+personaDTO.getIdPersona()+"/";
    }




    //-----------------------------------------------------------


    //BORRAR PERSONAS
    @GetMapping("/{id}/borrarPersona")
    public String doBorrarPersona (@PathVariable("id") Integer id) {
        this.personaService.borrarPersona(id);
        return "redirect:/administrador/";
    }



    //-----------------------------------------------------------





    //VER PRODUCTOS

    @GetMapping("/{id}/verProducto")
    public String doMostrarVerProducto(@PathVariable("id") Integer id,
                                        Model model) {

        ProductoDTO productoDTO = this.productoService.findProductoById(id);
        List<TransaccionDTO> listaTransacciones = this.personaService.getAllTransaccion();


        model.addAttribute("listaTransacciones", listaTransacciones);
        model.addAttribute("producto_ver", productoDTO);

        return "verProducto";
    }






    //-----------------------------------------------------------


    //EDITAR PRODUCTOS
    @GetMapping("/{id}/editarProducto")
    public String doMostrarEditarProducto (@PathVariable("id") Integer id,
                                   Model model) {

        ProductoDTO productoDTO = this.productoService.findProductoById(id);
        model.addAttribute("producto_editar", productoDTO);

        return "editarProducto";
    }


    @PostMapping("/actualizarProducto")
    public String doEditarProducto(@ModelAttribute("producto_editar") ProductoDTO productoDTO, Model model)  {


        this.productoService.actualizarProducto(productoDTO);
        String message = "Producto ActualizadO";
        model.addAttribute("Control", message);
        return "redirect:/administrador/editarProducto/"+productoDTO.getId()+"/";
    }

    //-----------------------------------------------------------


    //BORRAR PRODUCTO
    @GetMapping("/{id}/borrarProducto")
    public String doBorrarProducto (@PathVariable("id") Integer id) {
        this.productoService.borrarVenta(id);
        return "redirect:/administrador/";
    }



    //-----------------------------------------------------------

}