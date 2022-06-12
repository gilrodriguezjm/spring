package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.PersonaInteres;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.dao.TransaccionRepository;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.TransaccionDTO;
import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.TransaccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    //REPOSITORY

    //Persona


    protected PersonaRepository personaRepository;

    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    @Autowired
    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    //Producto


    protected ProductoRepository productoRepository;

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }
    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    //Persona-Interes


    protected PersonaInteres personaInteresRepository ;
    public PersonaInteres getPersonaInteres() {
        return personaInteresRepository ;
    }
    @Autowired
    public void setPersonaInteres(PersonaInteres personaInteres) {
        this.personaInteresRepository  = personaInteres;
    }


    //Transaccion


    protected TransaccionRepository transaccionRepository;

    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }
    @Autowired
    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }
    //-----------------------------------------------------------------------------


    //FUNCTIONS

    public PersonaDTO findPersonaByID(Integer id)
    {
        PersonaEntity personaEntity = this.personaRepository.findPersonaEntityById(id);
        if (personaEntity == null)
            return null;
        else
            return personaEntity.toDTO();
    }

    public PersonaDTO findPersonaByEmail(String email)
    {
        PersonaEntity personaEntity = this.personaRepository.findPersonaEntityByEmail(email);
        if (personaEntity == null)
            return null;
        else
            return personaEntity.toDTO();
    }

    public PersonaDTO comprobarCredenciales(String email, String pass) {
        PersonaEntity persona = this.personaRepository.findPersonaEntityByEmailAndPassword(email, pass);
        if (persona == null)
            return null;
        else
            return persona.toDTO();
    }

    public List<PersonaDTO> listarTodasPersonas() {
       List<PersonaEntity>  list_personas_entity = this.personaRepository.findAll();
        List<PersonaDTO> list_personas = new ArrayList<>();

        for (int i=0; i<list_personas_entity.size(); i++)
       {
           list_personas.add(list_personas_entity.get(i).toDTO());
       }
        if (list_personas == null)
            return null;
        else
            return list_personas;
    }


    public void actualizarPersona(PersonaDTO personaDTO){
        PersonaEntity personaEntity = new PersonaEntity(personaDTO);
        this.personaRepository.save(personaEntity);
    }



    public void borrarPersona(int id){
        this.personaRepository.deleteById(id);

    }

    public List<TransaccionDTO>  getAllTransaccion()
    {
        List<TransaccionEntity> transacciones = this.transaccionRepository.findAll();

        return this.listaTransaccionEntityToDTO(transacciones);
    }

    private List<TransaccionDTO> listaTransaccionEntityToDTO (List<TransaccionEntity> lista) {
        List<TransaccionDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (TransaccionEntity transaccion:lista) {
                listaDTO.add(transaccion.toDTO());
            }
        }
        return listaDTO;
    }


}
