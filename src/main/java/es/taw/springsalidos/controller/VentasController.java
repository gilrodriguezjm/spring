package es.taw.springsalidos.controller;

import es.taw.springsalidos.dao.InteresRepository;
import es.taw.springsalidos.dao.ProductoInteresRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.*;
import es.taw.springsalidos.service.EstadoService;
import es.taw.springsalidos.service.InteresService;
import es.taw.springsalidos.service.ProductoInteresService;
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
    public void setProductoService(ProductoService productoService){this.productoService = productoService;}

    private EstadoService estadoService;

    public EstadoService getEstadoService(){return estadoService;}

    @Autowired
    public void setEstadoService(EstadoService estadoService){this.estadoService = estadoService;}

    private InteresService interesService;

    public InteresService getInteresService() {
        return interesService;
    }

    @Autowired
    public void setInteresService(InteresService interesService) {
        this.interesService = interesService;
    }

    @GetMapping("{id}/eliminarVenta")
    public String eliminarVenta(@PathVariable("id") int id){

        this.productoService.borrarVenta(id);
        return "inicio";

    }

    private ProductoInteresService pis;

    public ProductoInteresService getPis() {
        return pis;
    }

    @Autowired
    public void setPis(ProductoInteresService pis) {
        this.pis = pis;
    }

    @GetMapping("{id}/modificarVenta")
    public String modificarVenta(@PathVariable("id") int id, Model model){

        ProductoDTO producto_a_editar = this.productoService.findProductoById(id);

        List<InteresEntity> intereses = this.interesService.buscarTodos();

        model.addAttribute("producto",producto_a_editar);
        model.addAttribute("intereses",intereses);

        return "editarVenta";

    }

    @PostMapping("/confirmarActualizarVenta")
    public String confirmarActualizarVenta (@ModelAttribute("producto") ProductoDTO productoDTO) {

        this.productoService.actualizarProducto(productoDTO);

        return "inicio";
    }



    @GetMapping("/nuevaVenta")
    public String irNuevaVenta(Model model){

        ProductoDTO producto = new ProductoDTO();

        List<EstadoEntity> estados = this.estadoService.buscarTodos();
        List<InteresEntity> intereses = this.interesService.buscarTodos();

        model.addAttribute("producto",producto);
        model.addAttribute("estados",estados);
        model.addAttribute("intereses",intereses);


        return "nuevaVenta";

    }

    @GetMapping("/crearVenta")
    public String crearVenta(@ModelAttribute("producto") ProductoDTO producto, HttpSession session){


        PersonaDTO persona = (PersonaDTO) session.getAttribute("persona");

        PersonaEntity persona_transaccion = new PersonaEntity(persona);


        ProductoEntity producto_con_Id = crearProductoNuevo(producto);



        //Hacemos la lista InteresEntity

        List<InteresEntity> intereses_producto = this.interesService.interesesDTOAEntity(producto);

        //Tenemos que crear la relacion producto interes

        List<ProductoInteresEntity> intereses = this.pis.crearIntereses(producto_con_Id.getId(),producto.getProductoInteresByProductoId());

        producto_con_Id.setProductoInteresById(intereses);


        //EstadoEntity estado_producto = this.estadoService.buscarPorId(producto.getEstadoByEstadoId().getId());







        List<TransaccionEntity> nueva_transaccion = crearTransaccion(producto_con_Id,persona_transaccion);

        producto_con_Id.setTransaccionsById(nueva_transaccion);

        return "inicio";

    }

    public ProductoEntity crearProductoNuevo(ProductoDTO producto){

        ProductoEntity producto1 = new ProductoEntity(producto);

        Date fecha_venta = new Date(System.currentTimeMillis());

        producto1.setFechaVenta(fecha_venta);


        EstadoEntity estado_producto = this.estadoService.buscarPorId(producto.getEstadoByEstadoId());

        producto1.setEstadoByEstadoId(estado_producto);

        System.out.println("Se ha cargado el estado en el producto.");
        System.out.println("El estado del producto es : "+producto1.getEstadoByEstadoId().getNombre());

        //No deja crear el producto por que estadoByEstadoId no tiene valor por defecto.

        ProductoEntity producto_con_Id = this.productoService.guardarProducto(producto1);

        return producto_con_Id;

    }

    public List<TransaccionEntity> crearTransaccion(ProductoEntity producto,PersonaEntity persona){

        Date fecha = new Date(System.currentTimeMillis());

        List<TransaccionEntity> listaTransacciones = new ArrayList<TransaccionEntity>();

        TransaccionEntity nueva_transaccion = new TransaccionEntity();

        TransaccionEntityPK nueva_transaccionPK = new TransaccionEntityPK();

        nueva_transaccionPK.setPersonaId(persona.getId());
        nueva_transaccionPK.setProductoId(producto.getId());

        nueva_transaccion.setTransaccionEntityPK(nueva_transaccionPK);
        nueva_transaccion.setFechaTransaccion(fecha);
        nueva_transaccion.setPrecioCompra(null);
        nueva_transaccion.setPersonaByPersonaId(persona);
        nueva_transaccion.setProductoByProductoId(producto);
        nueva_transaccion.setTipo("puja");

        listaTransacciones.add(nueva_transaccion);

        return listaTransacciones;


    }


}
