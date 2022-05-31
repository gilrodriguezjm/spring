package es.taw.springsalidos.service;

import es.taw.springsalidos.dao.AnalistaRepository;
import es.taw.springsalidos.dao.PersonaRepository;
import es.taw.springsalidos.dao.ProductoRepository;
import es.taw.springsalidos.dao.TransaccionRepository;
import es.taw.springsalidos.dto.AnalisisDTO;
import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import es.taw.springsalidos.dto.TransaccionDTO;
import es.taw.springsalidos.entity.AnalisisEntity;
import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.ProductoEntity;
import es.taw.springsalidos.entity.TransaccionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalisisService {

    private PersonaRepository personaRepository;
    private AnalistaRepository analistaRepository;
    private TransaccionRepository transaccionRepository;
    private ProductoRepository productoRepository;

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

    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    @Autowired
    public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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

    private List<ProductoDTO> listaProductoEntityToDTO (List<ProductoEntity> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (ProductoEntity producto:lista) {
                listaDTO.add(producto.toDTO());
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
            strTabla = "Transacciones";
        else
            strTabla = "Producto";

        strColumna = switch (columna) {
            case 1 -> "Productos comprados";
            case 2 -> "Precio de salida";
            case 3 -> "Precio de compra";
            case 4 -> "Estado";
            case 5 -> "Puja";
            default -> "Productos vendidos";
        };

        if (orden == 0)
            strOrden = "Ascendente";
        else
            strOrden = "Descendente";

        descripcion = "Informe sobre " + strTabla + " por " + strColumna + " en orden " + strOrden;

        return descripcion;
    }

    public void rellenarInforme(AnalisisEntity analisisEntity, String descripcion, int tabla, int columna, int orden, Date fechaInicio, Date fechaFinal, PersonaDTO persona){
        PersonaEntity personaEntity = new PersonaEntity(persona);

        analisisEntity.setDescripcion(descripcion);
        analisisEntity.setTabla(tabla);
        analisisEntity.setColumna(columna);
        analisisEntity.setOrden(orden);
        analisisEntity.setFechaInicio(fechaInicio);
        analisisEntity.setFechaFinal(fechaFinal);
        analisisEntity.setPersonaByPersonaId(personaEntity);
    }

    public void crearInforme(String descripcion, int tabla, int columna, int orden, Date fechaInicio, Date fechaFinal, PersonaDTO persona){
        AnalisisEntity analisisEntity = new AnalisisEntity();
        this.rellenarInforme(analisisEntity, descripcion, tabla, columna, orden, fechaInicio, fechaFinal, persona);

        this.analistaRepository.save(analisisEntity);
    }

    public void borrarAnalisis(int id){
        this.analistaRepository.deleteById(id);
    }

    public AnalisisDTO findAnalisisById(int id){
        AnalisisEntity analisis = this.analistaRepository.findById(id).orElse(null);

        return analisis.toDTO();
    }

    public void actualizarInforme(AnalisisDTO analisis){
        AnalisisEntity analisisEntity = new AnalisisEntity(analisis);

        this.analistaRepository.save(analisisEntity);
    }

    public List<TransaccionDTO> obtenerTransaccionesPorTipoEnOrdenEntreFechas(int columna, int orden, Date fIni, Date fFin){
        List<TransaccionEntity> list;

        String tipo = switch (columna) {
            case 0 -> "venta";
            case 1 -> "compra";
            default -> "puja";
        };

        if (orden == 0)
            list = this.transaccionRepository.buscarTransaccionesPorTipoEnOrdenAscEntreFechas(tipo, fIni, fFin);
        else
            list = this.transaccionRepository.buscarTransaccionesPorTipoEnOrdenDescEntreFechas(tipo, fIni, fFin);

        return this.listaTransaccionEntityToDTO(list);
    }

    public List<ProductoDTO> obtenerProductosPorColumnaEnOrdenEntreFechas(int columna, int orden, Date fIni, Date fFin){
        List<ProductoEntity> list;

        switch (columna) {
            case 2:
                if (orden == 0)
                    list = this.productoRepository.buscarProductosPorPrecioSalidaAscEntreFechas(fIni, fFin);
                else
                    list = this.productoRepository.buscarProductosPorPrecioSalidaDescEntreFechas(fIni, fFin);
                break;
            case 3:
                if (orden == 0)
                    list = this.productoRepository.buscarProductosPorPrecioCompraAscEntreFechas(fIni, fFin);
                else
                    list = this.productoRepository.buscarProductosPorPrecioCompraDescEntreFechas(fIni, fFin);
                break;
            default:
                if (orden == 0)
                    list = this.productoRepository.buscarProductosPorEstadoAscEntreFechas(fIni, fFin);
                else
                    list = this.productoRepository.buscarProductosPorEstadoDescEntreFechas(fIni, fFin);
                break;
        }

        if (list == null)
            return null;
        else
            return this.listaProductoEntityToDTO(list);
    }
}
