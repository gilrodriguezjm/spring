package es.taw.springsalidos.service;


import es.taw.springsalidos.dao.InteresRepository;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.entity.InteresEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InteresService {

    private InteresRepository interesRepository;

    public InteresRepository getInteresRepository() {
        return interesRepository;
    }

    @Autowired
    public void setInteresRepository(InteresRepository interesRepository) {
        this.interesRepository = interesRepository;
    }

    public List<InteresEntity> buscarTodos(){

        return this.interesRepository.findAll();

    }

    public List<InteresEntity> interesesDTOAEntity(ProductoDTO producto){

        List<InteresEntity> lista_intereses = new ArrayList<InteresEntity>();

        for(int i=0;i<producto.getProductoInteresByProductoId().size();i++){
            lista_intereses.add(this.interesRepository.findById(producto.getProductoInteresByProductoId().get(i)).orElse(null));
        }

        return lista_intereses;
    }



}
