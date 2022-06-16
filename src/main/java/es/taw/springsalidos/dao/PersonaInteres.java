package es.taw.springsalidos.dao;
import es.taw.springsalidos.entity.PersonaInteresEntity;
import es.taw.springsalidos.entity.PersonaInteresEntityPK;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaInteres extends JpaRepository<PersonaInteresEntity, PersonaInteresEntityPK> {

}



