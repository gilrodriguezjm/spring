package es.taw.springsalidos.entity;

import es.taw.springsalidos.dto.TransaccionDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "transaccion", schema = "salidosSpring", catalog = "")
public class TransaccionEntity {

    @EmbeddedId
    private TransaccionEntityPK transaccionEntityPK;
    @Basic
    @Column(name = "fecha_transaccion")
    private Date fechaTransaccion;
    @Basic
    @Column(name = "tipo")
    private String tipo;
    @Basic
    @Column(name = "precio_compra")
    private Double precioCompra;
    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private PersonaEntity personaByPersonaId;
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private ProductoEntity productoByProductoId;

    public TransaccionEntityPK getTransaccionEntityPK() {
        return transaccionEntityPK;
    }

    public void setTransaccionEntityPK(TransaccionEntityPK transaccionEntityPK) {
        this.transaccionEntityPK = transaccionEntityPK;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionEntity that = (TransaccionEntity) o;
        return this.getTransaccionEntityPK().equals(((TransaccionEntity) o).getTransaccionEntityPK()) &&
                Objects.equals(fechaTransaccion, that.fechaTransaccion) && Objects.equals(tipo, that.tipo) && Objects.equals(precioCompra, that.precioCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.transaccionEntityPK.getPersonaId(),
                this.transaccionEntityPK.getProductoId(), fechaTransaccion, tipo, precioCompra);
    }

    public PersonaEntity getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(PersonaEntity personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

    public ProductoEntity getProductoByProductoId() {
        return productoByProductoId;
    }

    public void setProductoByProductoId(ProductoEntity productoByProductoId) {
        this.productoByProductoId = productoByProductoId;
    }

    public TransaccionDTO toDTO(){
        TransaccionDTO dto = new TransaccionDTO();

        dto.setPersona(personaByPersonaId);
        dto.setFechaTransaccion(fechaTransaccion);
        dto.setTipo(tipo);
        dto.setProducto(productoByProductoId);
        dto.setPrecio(precioCompra);

        return dto;
    }
}
