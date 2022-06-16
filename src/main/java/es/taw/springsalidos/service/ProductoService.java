package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.*;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    protected ProductoRepository productorepository;

    public ProductoRepository getProductorepository() {
        return productorepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository pr) {
        this.productorepository = pr;
    }

    protected PersonaRepository personarepository;

    public PersonaRepository getPersonarepository(){return personarepository;}

    @Autowired
    public void setPersonarepository(PersonaRepository personarepository){this.personarepository = personarepository;}

    protected TransaccionRepository transaccionRepository;

    public TransaccionRepository getTransaccionRepository(){return transaccionRepository;}

    @Autowired
    public void setTransaccionRepository(TransaccionRepository transaccionRepository){this.transaccionRepository = transaccionRepository;}

    protected ProductoInteresRepository productoInteresRepository;

    public ProductoInteresRepository getProductoInteresRepository(){return productoInteresRepository;}

    @Autowired
    public void setProductoInteresRepository(ProductoInteresRepository productoInteresRepository){this.productoInteresRepository = productoInteresRepository;}


    private EstadoRepository estadoRepository;

    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }

    @Autowired
    public void setEstadoRepository(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    private InteresRepository interesRepository;

    public InteresRepository getInteresRepository() {
        return interesRepository;
    }


    @Autowired
    public void setInteresRepository(InteresRepository interesRepository) {
        this.interesRepository = interesRepository;
    }

    public List<ProductoDTO> getVentas(int personaId){


        PersonaEntity persona = this.getPersonarepository().findPersonaEntityById(personaId);

        System.out.println("Encontrado a " + persona.getNombre());

        List<ProductoDTO> ventasDTO = new ArrayList<ProductoDTO>();

        int pId = persona.getId();

        List<ProductoEntity> ventas = this.productorepository.findVentas(pId);

        System.out.println("Cargadas ventas de "+persona.getNombre());

        if(ventas == null){
            return null;
        }else{

            for(int i=0;i<ventas.size();i++){
                ventasDTO.add(ventas.get(i).toDTO());
            }

        }

        return ventasDTO;

    }


    public List<ProductoDTO> getPujas(int personaId){

        List<ProductoDTO> pujasDTO = new ArrayList<ProductoDTO>();

        List<ProductoEntity> pujas = this.productorepository.findPujas(personaId);

        System.out.println("Cargadas pujas");

        if(pujas == null){
            return null;
        }else{

            for(int i=0;i<pujas.size();i++){
                pujasDTO.add(pujas.get(i).toDTO());
            }

        }

        return pujasDTO;

    }


    public void borrarVenta(int id){

        ProductoEntity producto = this.productorepository.findById(id).orElse(null);
        System.out.println("Cargando transacciones");
        List<TransaccionEntity> transacciones = this.transaccionRepository.findByProductoId(id);
        System.out.println("Transacciones cargadas");
        System.out.println("Cargando intereses del producto");
       // List<ProductoInteresEntity> intereses_del_producto = this.productoInteresRepository.findByProductoId(producto);ç
        List<ProductoInteresEntity> intereses_del_producto = producto.getProductoInteresById();
        System.out.println("Intereses-producto cargados");

        if(!transacciones.isEmpty()){
            this.transaccionRepository.deleteAll(transacciones);

        }

        /*
        if(!intereses_del_producto.isEmpty()){
            this.productoInteresRepository.deleteAll(intereses_del_producto);
        }
        */

        this.productorepository.deleteById(id);


    }

    public ProductoDTO findProductoById(int id){



        ProductoEntity producto = this.productorepository.findById(id).orElse(null);

        return producto.toDTO();

    }

    public void actualizarProducto(ProductoDTO productoDTO){

        ProductoEntity producto = new ProductoEntity(productoDTO);

        EstadoEntity estado_nuevo = this.estadoRepository.encontrarPorId(productoDTO.getEstadoByEstadoId());

        producto.setEstadoByEstadoId(estado_nuevo);

        List<InteresEntity> intereses_nuevos = encontrar_nuevos_intereses(productoDTO);

        List<ProductoInteresEntity> producto_intereses = actualizar_intereses(producto,intereses_nuevos);

        producto.setProductoInteresById(producto_intereses);

        this.productorepository.save(producto);
    }

    public List<ProductoInteresEntity> actualizar_intereses(ProductoEntity p,List<InteresEntity> intereses){

        List<ProductoInteresEntity> intereses_producto = new ArrayList<ProductoInteresEntity>();


        for(int i=0;i<intereses.size();i++){

            ProductoInteresEntity pie = new ProductoInteresEntity();
            ProductoInteresEntityPK piePK = new ProductoInteresEntityPK();

            piePK.setProductoId(p.getId());
            piePK.setInteresId(intereses.get(i).getId());

            pie.setProductoInteresEntityPK(piePK);
            pie.setProductoByProductoId(p);
            pie.setInteresByInteresId(intereses.get(i));

            intereses_producto.add(pie);

        }

        return intereses_producto;

    }

    public List<InteresEntity> encontrar_nuevos_intereses(ProductoDTO p){

        List<InteresEntity> intereses = new ArrayList<InteresEntity>();

        for(int i=0;i<p.getProductoInteresByProductoId().size();i++){
            intereses.add(this.interesRepository.findById(p.getProductoInteresByProductoId().get(i)).orElse(null));
        }

        return intereses;
    }


    public ProductoEntity guardarProducto(ProductoEntity producto){
        ProductoEntity product = this.productorepository.save(producto);
        return product;
    }

    public List<ProductoDTO> getAllProductos(){
        List <ProductoEntity> productos = this.productorepository.findAll();
        List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();

        if (productos != null) {
            for(int i=0; i<productos.size(); i++){
                productoDTOs.add(productos.get(i).toDTO());
            }
            return productoDTOs;
        } else {
            return null;
        }
    }

}
