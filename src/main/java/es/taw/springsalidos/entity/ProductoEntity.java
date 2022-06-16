package es.taw.springsalidos.entity;

import es.taw.springsalidos.dto.PersonaDTO;
import es.taw.springsalidos.dto.ProductoDTO;
import org.springframework.data.repository.query.Param;

import es.taw.springsalidos.dto.ProductoDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "producto", schema = "salidosSpring", catalog = "")
public class ProductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fecha_venta")
    private Date fechaVenta;
    @Basic
    @Column(name = "precio_salida")
    private Double precioSalida;
    @Basic
    @Column(name = "precio_compra")
    private Double precioCompra;
    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private EstadoEntity estadoByEstadoId;
    @OneToMany(mappedBy = "productoByProductoId", cascade = CascadeType.ALL)
    private List<ProductoInteresEntity> productoInteresById;
    @OneToMany(mappedBy = "productoByProductoId")
    private List<TransaccionEntity> transaccionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(Double precioSalida) {
        this.precioSalida = precioSalida;
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
        ProductoEntity that = (ProductoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaVenta, that.fechaVenta) && Objects.equals(precioSalida, that.precioSalida) && Objects.equals(precioCompra, that.precioCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaVenta, precioSalida, precioCompra);
    }

    public EstadoEntity getEstadoByEstadoId() {
        return estadoByEstadoId;
    }

    public void setEstadoByEstadoId(EstadoEntity estadoByEstadoId) {
        this.estadoByEstadoId = estadoByEstadoId;
    }

    public List<ProductoInteresEntity> getProductoInteresById() {
        return productoInteresById;
    }

    public void setProductoInteresById(List<ProductoInteresEntity> productoInteresById) {
        this.productoInteresById = productoInteresById;
    }

    public List<TransaccionEntity> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<TransaccionEntity> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }




    public ProductoEntity() {}

    public ProductoEntity(ProductoDTO dto){
        this.id = dto.getId();
        this.nombre = dto.getNombre();
        this.fechaVenta = dto.getFechaVenta();
        this.precioSalida = dto.getPrecioSalida();
        this.precioCompra = dto.getPrecioCompra();
        //this.estadoByEstadoId = dto.getEstadoByEstadoId();

    }



    public ProductoDTO toDTO(){
        ProductoDTO dto = new ProductoDTO();

        dto.setId(id);
        dto.setFechaVenta(fechaVenta);
        dto.setNombre(nombre);
        dto.setPrecioCompra(precioCompra);
        dto.setPrecioSalida(precioSalida);
        dto.setEstadoByEstadoId(estadoByEstadoId.getId());


        return dto;
    }
}
