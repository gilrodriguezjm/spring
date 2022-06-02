package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dao.ProductoInteresRepository;
import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.dao.TransaccionRepository;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.ProductoEntity;
import es.taw.springsalidos.entity.ProductoInteresEntity;
import es.taw.springsalidos.entity.TransaccionEntity;
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



    public List<ProductoDTO> getVentas(int personaId){


        PersonaEntity persona = this.getPersonarepository().findPersonaEntityById(personaId);

        System.out.println("Encontrado a " + persona.getNombre());

        List<ProductoDTO> ventasDTO = new ArrayList<ProductoDTO>();

        int pId = persona.getId();

        List<ProductoEntity> ventas = (List<ProductoEntity>) this.productorepository.findVentas(pId);


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

        System.out.println("Cargando transacciones");
        List<TransaccionEntity> transacciones = this.transaccionRepository.findByProductoId(id);
        System.out.println("Transacciones cargadas");
        System.out.println("Cargando intereses del producto");
        List<ProductoInteresEntity> intereses_del_producto = this.productoInteresRepository.findByProductoId(id);
        System.out.println("Intereses-producto cargados");

        if(!transacciones.isEmpty()){
            this.transaccionRepository.deleteAll(transacciones);

        }

        if(!intereses_del_producto.isEmpty()){
            this.productoInteresRepository.deleteAll(intereses_del_producto);
        }


        this.productorepository.deleteById(id);


    }

    public ProductoDTO findProductoById(int id){



        ProductoEntity producto = this.productorepository.findById(id).orElse(null);

        return producto.toDTO();

    }

    public void actualizarProducto(ProductoDTO productoDTO){

        ProductoEntity producto = new ProductoEntity(productoDTO);

        this.productorepository.save(producto);
    }


}
