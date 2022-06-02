package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.InteresRepository;
import es.taw.springsalidos.dao.ProductoInteresRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.*;
import es.taw.springsalidos.service.EstadoService;
import es.taw.springsalidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    private ProductoService productoService;

    public ProductoService getProductoService(){return productoService;}

    @Autowired
    public void setProductoRepository(ProductoService productoService){this.productoService = productoService;}

    private EstadoService estadoService;

    public EstadoService getEstadoService(){return estadoService;}

    @Autowired
    public void setEstadoService(EstadoService estadoService){this.estadoService = estadoService;}

    private InteresRepository interesRepository;

    public InteresRepository getInteresRepository(){return interesRepository;}

    @Autowired
    public void setInteresRepository(InteresRepository interesRepository){this.interesRepository = interesRepository;}


    @GetMapping("{id}/eliminarVenta")
    public String eliminarVenta(@PathVariable("id") int id){

        this.productoService.borrarVenta(id);
        return "inicio";

    }



    private ProductoInteresRepository productoInteresRepository;

    public ProductoInteresRepository getProductoInteresRepository(){return productoInteresRepository;}

    @Autowired
    public void setProductoInteresRepository(ProductoInteresRepository productoInteresRepository){this.productoInteresRepository = productoInteresRepository;}

    @GetMapping("{id}/modificarVenta")
    public String modificarVenta(@PathVariable("id") int id, Model model){

        ProductoDTO producto_a_editar = this.productoService.findProductoById(id);

        model.addAttribute("producto",producto_a_editar);

        return "editarVenta";

    }

    @PostMapping("/confrimarActualizarVenta")
    public String confirmarActualizarVenta (@ModelAttribute("producto") ProductoDTO productoDTO) {

        this.productoService.actualizarProducto(productoDTO);

        return "inicio";
    }



    @GetMapping("/nuevaVenta")
    public String irNuevaVenta(Model model){

        ProductoDTO producto = new ProductoDTO();

        List<EstadoEntity> estados = this.estadoService.buscarTodos();
        List<InteresEntity> intereses = this.interesRepository.findAll();

        model.addAttribute("producto",producto);
        model.addAttribute("estados",estados);
        model.addAttribute("intereses",intereses);


        return "nuevaVenta";

    }

    @GetMapping("/crearVenta")
    public String crearVenta(@ModelAttribute("producto") ProductoDTO producto, HttpSession session){

        Date fecha_venta = new Date(System.currentTimeMillis());

        producto.setFechaVenta(fecha_venta);

        PersonaDTO persona = (PersonaDTO) session.getAttribute("persona");

        PersonaEntity persona_transaccion = new PersonaEntity(persona);

        ProductoEntity producto_nuevo = new ProductoEntity(producto);


        EstadoEntity estado = this.estadoService.buscarPorId(producto_nuevo.getId());
        producto_nuevo.setEstadoByEstadoId(estado);

        List<ProductoInteresEntity> intereses = this.productoInteresRepository.findByProductoId(producto_nuevo.getId());
        producto_nuevo.setProductoInteresById(intereses);

        List<TransaccionEntity> nueva_transaccion = crearTransaccion(producto_nuevo,persona_transaccion);

        producto_nuevo.setTransaccionsById(nueva_transaccion);

        return "inicio";

    }

    public List<TransaccionEntity> crearTransaccion(ProductoEntity producto,PersonaEntity persona){

        Date fecha = new Date(System.currentTimeMillis());

        List<TransaccionEntity> listaTransacciones = new ArrayList<TransaccionEntity>();

        TransaccionEntity nueva_transaccion = new TransaccionEntity();

        nueva_transaccion.setFechaTransaccion(fecha);
        nueva_transaccion.setPrecioCompra(null);
        nueva_transaccion.setPersonaByPersonaId(persona);
        nueva_transaccion.setProductoByProductoId(producto);
        nueva_transaccion.setTipo("puja");

        listaTransacciones.add(nueva_transaccion);

        return listaTransacciones;


    }


}
