package es.taw.springsalidos.dto;

import java.sql.Date;

public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private Date fecha_puesta_venta;
    private Double precio_salida;
    private Double precio_compra;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_puesta_venta() {
        return fecha_puesta_venta;
    }

    public void setFecha_puesta_venta(Date fecha_puesta_venta) {
        this.fecha_puesta_venta = fecha_puesta_venta;
    }

    public Double getPrecio_salida() {
        return precio_salida;
    }

    public void setPrecio_salida(Double precio_salida) {
        this.precio_salida = precio_salida;
    }

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }
}
