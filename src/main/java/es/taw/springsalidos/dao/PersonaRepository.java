package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {


}
