package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public String generarDescripcion(int tabla, int columna, int orden) {
        String strTabla, strColumna, strOrden, descripcion;

        if (tabla == 0)
            strTabla = "Personas";
        else
            strTabla = "Producto";

        strColumna = switch (columna) {
            case 1 -> "Productos comprados";
            case 2 -> "Precio de salida";
            case 3 -> "Precio de compra";
            case 4 -> "Estado";
            default -> "Productos vendidos";
        };

        if (orden == 0)
            strOrden = "Ascendente";
        else
            strOrden = "Descendente";

        descripcion = "Informe sobre " + strTabla + " por " + strColumna + " en orden " + strOrden;

        return descripcion;
    }

    public void crearInforme(String descripcion, int tabla, int columna, int orden, Date fechaInicio, Date fechaFinal, PersonaDTO persona){
        AnalisisEntity analisisEntity = new AnalisisEntity();
        PersonaEntity personaEntity = new PersonaEntity(persona);

        analisisEntity.setDescripcion(descripcion);
        analisisEntity.setTabla(tabla);
        analisisEntity.setColumna(columna);
        analisisEntity.setOrden(orden);
        analisisEntity.setFechaInicio(fechaInicio);
        analisisEntity.setFechaFinal(fechaFinal);
        analisisEntity.setPersonaByPersonaId(personaEntity);

        this.analistaRepository.save(analisisEntity);
    }
}
