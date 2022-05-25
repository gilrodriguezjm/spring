package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductoInteresEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;
    @Basic(optional = false)
    @Column(name = "interes_id", nullable = false)
    private Integer interesId;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getInteresId() {
        return interesId;
    }

    public void setInteresId(Integer interesId) {
        this.interesId = interesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoInteresEntityPK that = (ProductoInteresEntityPK) o;
        return Objects.equals(productoId, that.productoId) && Objects.equals(interesId, that.interesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, interesId);
    }
}
