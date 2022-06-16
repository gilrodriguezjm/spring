package es.taw.springsalidos.dto;

import es.taw.springsalidos.entity.EstadoEntity;
import es.taw.springsalidos.entity.InteresEntity;
import es.taw.springsalidos.entity.ProductoInteresEntity;

import java.sql.Date;
import java.util.List;

public class ProductoDTO {

    private Integer id;
    private String nombre;
    private Date fechaVenta;
    private Double precioSalida;
    private Double precioCompra;
    private Integer estadoByEstadoId;
    private List<Integer> productoInteresByProductoId;

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

    public Integer getEstadoByEstadoId() {
        return estadoByEstadoId;
    }

    public void setEstadoByEstadoId(Integer estadoByEstadoId) {
        this.estadoByEstadoId = estadoByEstadoId;
    }

    public List<Integer> getProductoInteresByProductoId() {
        return productoInteresByProductoId;
    }

    public void setProductoInteresByProductoId(List<Integer> productoInteresByProductoId) {
        this.productoInteresByProductoId = productoInteresByProductoId;
    }
}
