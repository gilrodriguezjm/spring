package es.taw.springsalidos.dto;

import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.ProductoEntity;

import java.sql.Date;

public class TransaccionDTO {

    private Date fechaTransaccion;
    private String tipo;
    private Double precio;
    private PersonaEntity persona;
    private ProductoEntity producto;

    public TransaccionDTO() {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
