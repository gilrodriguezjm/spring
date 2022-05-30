package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.ProductoEntity;
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

    public List<ProductoDTO> getVentas(int personaId){


        List<ProductoDTO> ventasDTO = new ArrayList<ProductoDTO>();

        List<ProductoEntity> ventas = this.productorepository.findVentas(personaId);


        if(ventas == null){
            System.out.println("No hay nada");
            return null;
        }else{

            for(int i=0;i<ventas.size();i++){
                ventasDTO.add(ventas.get(i).toDTO());
            }

        }

        return ventasDTO;

    }

}
