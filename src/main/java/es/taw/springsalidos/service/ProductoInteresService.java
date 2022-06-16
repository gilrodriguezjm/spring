package es.taw.springsalidos.service;


import es.taw.springsalidos.dao.InteresRepository;
import es.taw.springsalidos.dao.ProductoInteresRepository;
import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.entity.InteresEntity;
import es.taw.springsalidos.entity.ProductoEntity;
import es.taw.springsalidos.entity.ProductoInteresEntity;
import es.taw.springsalidos.entity.ProductoInteresEntityPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoInteresService {

    private ProductoInteresRepository pir;

    public ProductoInteresRepository getPir() {
        return pir;
    }

    @Autowired
    public void setPir(ProductoInteresRepository pir) {
        this.pir = pir;
    }

    private ProductoRepository pr;

    public ProductoRepository getPr() {
        return pr;
    }

    @Autowired
    public void setPr(ProductoRepository pr) {
        this.pr = pr;
    }

    private InteresRepository ir;

    public InteresRepository getIr() {
        return ir;
    }

    @Autowired
    public void setIr(InteresRepository ir) {
        this.ir = ir;
    }

    public List<ProductoInteresEntity> crearIntereses(int id_producto, List<Integer> id_intereses){

        ProductoEntity producto = this.pr.findById(id_producto).orElse(null);

        List<InteresEntity> producto_interes = new ArrayList<InteresEntity>();

        List<ProductoInteresEntity> lista = new ArrayList<ProductoInteresEntity>();

        for(int i=0;i<id_intereses.size();i++){

            InteresEntity interes = this.ir.encontrarPorId(id_intereses.get(i));

            producto_interes.add(interes);

        }

        for(int i=0;i<producto_interes.size();i++){

            ProductoInteresEntity pi = new ProductoInteresEntity();
            ProductoInteresEntityPK piPK = new ProductoInteresEntityPK();

            //Creamos la PK
            piPK.setInteresId(producto_interes.get(i).getId());
            piPK.setProductoId(producto.getId());
            //Creamos la entidad producto_interes
            pi.setProductoByProductoId(producto);
            pi.setInteresByInteresId(producto_interes.get(i));
            pi.setProductoInteresEntityPK(piPK);
            //Añadimos a la lista
            lista.add(pi);

        }

        return lista;
    }


}
