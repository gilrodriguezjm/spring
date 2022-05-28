package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalistaRepository extends JpaRepository<AnalisisEntity, Integer> {

    public List<AnalisisEntity> findAllByPersonaByPersonaId(PersonaEntity persona);

}
