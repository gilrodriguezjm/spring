package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "analisis", schema = "salidosSpring", catalog = "")
public class AnalisisEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "tabla")
    private Integer tabla;
    @Basic
    @Column(name = "columna")
    private Integer columna;
    @Basic
    @Column(name = "orden")
    private Integer orden;
    @Basic
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Basic
    @Column(name = "fecha_final")
    private Date fechaFinal;
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private PersonaEntity personaByPersonaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTabla() {
        return tabla;
    }

    public void setTabla(Integer tabla) {
        this.tabla = tabla;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalisisEntity that = (AnalisisEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion) && Objects.equals(tabla, that.tabla) && Objects.equals(columna, that.columna) && Objects.equals(orden, that.orden) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(fechaFinal, that.fechaFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, tabla, columna, orden, fechaInicio, fechaFinal);
    }

    public PersonaEntity getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(PersonaEntity personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }
}
