package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransaccionEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "persona_id", nullable = false)
    private Integer personaId;
    @Basic(optional = false)
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionEntityPK that = (TransaccionEntityPK) o;
        return Objects.equals(personaId, that.personaId) && Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, productoId);
    }
}
