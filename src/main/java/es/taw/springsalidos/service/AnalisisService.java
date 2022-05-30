package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalisisService {

    private PersonaRepository personaRepository;
    private AnalistaRepository analistaRepository;

    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public AnalistaRepository getAnalistaRepository() {
        return analistaRepository;
    }

    @Autowired
    public void setAnalistaRepository(AnalistaRepository analistaRepository) {
        this.analistaRepository = analistaRepository;
    }

    private List<AnalisisDTO> listaAnalisisEntityToDTO (List<AnalisisEntity> lista) {
        List<AnalisisDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (AnalisisEntity analisis:lista) {
                listaDTO.add(analisis.toDTO());
            }
        }
        return listaDTO;
    }

    public List<AnalisisDTO> listarInformes(PersonaDTO persona) {
        PersonaEntity personaEntity = new PersonaEntity(persona);

        List<AnalisisEntity> listaAnalisis = this.analistaRepository.findAllByPersonaByPersonaId(personaEntity);

        return this.listaAnalisisEntityToDTO(listaAnalisis);
    }
}
