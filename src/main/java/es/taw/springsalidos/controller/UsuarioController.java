package es.taw.springsalidos.controller;

import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsuarioController {

    protected ProductoService productoservice;

    public ProductoService getProductoservice() {
        return productoservice;
    }

    @Autowired
    public void setProductoService (ProductoService ps){
        this.productoservice = ps;
    }

    @GetMapping("/usuario")
    public String cargarUsuario (Model model, HttpSession session){

        PersonaDTO persona = (PersonaDTO) session.getAttribute("persona");


        List<ProductoDTO> ventas = this.productoservice.getVentas(persona.getIdPersona());
        List<ProductoDTO> pujas = this.productoservice.getPujas(persona.getIdPersona());


        model.addAttribute("persona",persona);



        if(ventas.isEmpty() && pujas.isEmpty()){

            model.addAttribute("error","No hay ventas ni pujas para este usuario");
            return "inicio";

        }else if(ventas.isEmpty()){

            model.addAttribute("pujas",pujas);

            return "inicio";

        }else if(pujas.isEmpty()){

            model.addAttribute("ventas",ventas);
            return "inicio";

        }else{

            model.addAttribute("pujas",pujas);
            model.addAttribute("ventas",ventas);
            return "inicio";

        }


    }


}
