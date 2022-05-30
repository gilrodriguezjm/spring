package es.taw.springsalidos.entity;

import es.taw.springsalidos.dto.PersonaDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persona", schema = "salidosSpring", catalog = "")
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellidos")
    private String apellidos;
    @Basic
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Basic
    @Column(name = "monedero")
    private Double monedero;
    @Basic
    @Column(name = "rol")
    private String rol;
    @Basic
    @Column(name = "domicilio")
    private String domicilio;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(mappedBy = "personaByPersonaId")
    private List<AnalisisEntity> analisesById;
    @OneToMany(mappedBy = "personaByPersonaId")
    private List<PersonaInteresEntity> personaInteresById;
    @OneToMany(mappedBy = "personaByPersonaId")
    private List<TransaccionEntity> transaccionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getMonedero() {
        return monedero;
    }

    public void setMonedero(Double monedero) {
        this.monedero = monedero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(monedero, that.monedero) && Objects.equals(rol, that.rol) && Objects.equals(domicilio, that.domicilio) && Objects.equals(ciudad, that.ciudad) && Objects.equals(sexo, that.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, nombre, apellidos, fechaNacimiento, monedero, rol, domicilio, ciudad, sexo);
    }

    public List<AnalisisEntity> getAnalisesById() {
        return analisesById;
    }

    public void setAnalisesById(List<AnalisisEntity> analisesById) {
        this.analisesById = analisesById;
    }

    public List<PersonaInteresEntity> getPersonaInteresById() {
        return personaInteresById;
    }

    public void setPersonaInteresById(List<PersonaInteresEntity> personaInteresById) {
        this.personaInteresById = personaInteresById;
    }

    public List<TransaccionEntity> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<TransaccionEntity> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public PersonaDTO toDTO() {
        PersonaDTO dto = new PersonaDTO();

        dto.setIdPersona(id);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setNombre(nombre);
        dto.setApellidos(apellidos);
        dto.setRol(rol);
        dto.setFecha_nacimiento(fechaNacimiento);
        dto.setMonedero(monedero);
        dto.setDomicilio(domicilio);
        dto.setCiudad(ciudad);
        dto.setSexo(sexo);

        return dto;
    }

    public PersonaEntity() {}

    public PersonaEntity(PersonaDTO dto){
        this.id = dto.getIdPersona();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nombre = dto.getNombre();
        this.apellidos = dto.getApellidos();
        this.rol = dto.getRol();
        this.fechaNacimiento = dto.getFecha_nacimiento();
        this.monedero = dto.getMonedero();
        this.domicilio = dto.getDomicilio();
        this.ciudad = dto.getCiudad();
        this.sexo = dto.getSexo();
    }
}
