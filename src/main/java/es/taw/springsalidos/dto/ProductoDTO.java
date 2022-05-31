package es.taw.springsalidos.dto;

import es.taw.springsalidos.entity.EstadoEntity;

import java.sql.Date;

public class ProductoDTO {

    private Integer id;
    private String nombre;
    private Date fechaVenta;
    private Double precioSalida;
    private Double precioCompra;
    private EstadoEntity estadoByEstadoId;

    public ProductoDTO() {
    }

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

    public EstadoEntity getEstadoByEstadoId() {
        return estadoByEstadoId;
    }

    public void setEstadoByEstadoId(EstadoEntity estadoByEstadoId) {
        this.estadoByEstadoId = estadoByEstadoId;
    }
}
