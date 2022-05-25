package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "interes", schema = "salidosSpring", catalog = "")
public class InteresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "interesByInteresId")
    private List<PersonaInteresEntity> personaInteresById;
    @OneToMany(mappedBy = "interesByInteresId")
    private List<ProductoInteresEntity> productoInteresById;

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
        InteresEntity that = (InteresEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public List<PersonaInteresEntity> getPersonaInteresById() {
        return personaInteresById;
    }

    public void setPersonaInteresById(List<PersonaInteresEntity> personaInteresById) {
        this.personaInteresById = personaInteresById;
    }

    public List<ProductoInteresEntity> getProductoInteresById() {
        return productoInteresById;
    }

    public void setProductoInteresById(List<ProductoInteresEntity> productoInteresById) {
        this.productoInteresById = productoInteresById;
    }
}
