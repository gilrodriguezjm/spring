package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    public PersonaEntity findPersonaEntityByEmailAndPassword(String email, String pass);

    public PersonaEntity findPersonaEntityById(int id);

}
