package es.taw.springsalidos.service;


import es.taw.springsalidos.dao.EstadoRepository;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.entity.EstadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {


    protected EstadoRepository estadoRepository;

    public EstadoRepository getEstadoRepository() {
        return estadoRepository;
    }

    @Autowired
    public void setEstadoRepositroy(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<EstadoEntity> buscarTodos(){

        List<EstadoEntity> estados = this.estadoRepository.findAll();

        return estados;

    }

    public EstadoEntity buscarPorId(int id){

        EstadoEntity estado = this.estadoRepository.findById(id).orElse(null);

        return estado;

    }

}
