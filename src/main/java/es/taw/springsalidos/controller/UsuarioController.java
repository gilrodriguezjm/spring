package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.ProductoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsuarioController {

    protected ProductoRepository productorepository;

    public ProductoRepository getProductorepository() {
        return productorepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository pr) {
        this.productorepository = pr;
    }

    @GetMapping("/usuario")
    public String cargarUsuario (Model model, HttpSession session){

        PersonaEntity persona = (PersonaEntity) session.getAttribute("persona");

        List<ProductoEntity> ventas = this.productorepository.findVentas(persona.getId());

        model.addAttribute("persona",persona);



        if(ventas == null){
            model.addAttribute("error","No hay ventas para este usuario");
            return "inicio";
        }else{

            model.addAttribute("ventas",ventas);

            return "inicio";
        }


    }


}
