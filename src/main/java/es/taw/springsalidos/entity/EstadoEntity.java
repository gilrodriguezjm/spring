package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estado", schema = "salidosSpring", catalog = "")
public class EstadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "estadoByEstadoId")
    private List<ProductoEntity> productosById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoEntity that = (EstadoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public List<ProductoEntity> getProductosById() {
        return productosById;
    }

    public void setProductosById(List<ProductoEntity> productosById) {
        this.productosById = productosById;
    }
}
