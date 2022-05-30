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

        model.addAttribute("persona",persona);



        if(ventas.isEmpty()){
            model.addAttribute("error","No hay ventas para este usuario");
            return "inicio";
        }else{

            model.addAttribute("ventas",ventas);

            return "inicio";
        }


    }


}
