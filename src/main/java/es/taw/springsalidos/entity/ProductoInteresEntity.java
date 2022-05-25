package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "producto-interes", schema = "salidosSpring", catalog = "")
public class ProductoInteresEntity {

    @EmbeddedId
    private ProductoInteresEntityPK productoInteresEntityPK;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private ProductoEntity productoByProductoId;
    @ManyToOne
    @JoinColumn(name = "interes_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private InteresEntity interesByInteresId;

    public ProductoInteresEntityPK getProductoInteresEntityPK() {
        return productoInteresEntityPK;
    }

    public void setProductoInteresEntityPK(ProductoInteresEntityPK productoInteresEntityPK) {
        this.productoInteresEntityPK = productoInteresEntityPK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoInteresEntity that = (ProductoInteresEntity) o;
        return this.getProductoInteresEntityPK().equals(((ProductoInteresEntity) o).getProductoInteresEntityPK()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getProductoInteresEntityPK().getInteresId(),
                this.getProductoInteresEntityPK().getProductoId());
    }

    public ProductoEntity getProductoByProductoId() {
        return productoByProductoId;
    }

    public void setProductoByProductoId(ProductoEntity productoByProductoId) {
        this.productoByProductoId = productoByProductoId;
    }

    public InteresEntity getInteresByInteresId() {
        return interesByInteresId;
    }

    public void setInteresByInteresId(InteresEntity interesByInteresId) {
        this.interesByInteresId = interesByInteresId;
    }
}
