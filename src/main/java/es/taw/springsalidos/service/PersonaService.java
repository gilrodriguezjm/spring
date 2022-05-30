package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    protected PersonaRepository personaRepository;

    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public PersonaDTO comprobarCredenciales(String email, String pass) {
        PersonaEntity persona = this.personaRepository.findPersonaEntityByEmailAndPassword(email, pass);
        if (persona == null)
            return null;
        else
            return persona.toDTO();
    }

}
