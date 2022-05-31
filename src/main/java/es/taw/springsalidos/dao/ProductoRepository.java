package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.precioSalida ASC")
    public List<ProductoEntity> buscarProductosPorPrecioSalidaAscEntreFechas(@Param("fIni") Date fIni,
                                                                             @Param("fFin") Date fFin);

    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.precioSalida DESC")
    public List<ProductoEntity> buscarProductosPorPrecioSalidaDescEntreFechas(@Param("fIni") Date fIni,
                                                                              @Param("fFin") Date fFin);

    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.precioCompra ASC")
    public List<ProductoEntity> buscarProductosPorPrecioCompraAscEntreFechas(@Param("fIni") Date fIni,
                                                                             @Param("fFin") Date fFin);

    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.precioCompra DESC")
    public List<ProductoEntity> buscarProductosPorPrecioCompraDescEntreFechas(@Param("fIni") Date fIni,
                                                                             @Param("fFin") Date fFin);
    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.estadoByEstadoId.id ASC")
    public List<ProductoEntity> buscarProductosPorEstadoAscEntreFechas(@Param("fIni") Date fIni,
                                                                       @Param("fFin") Date fFin);

    @Query("SELECT p FROM ProductoEntity p WHERE p.fechaVenta >= :fIni and p.fechaVenta <= :fFin ORDER BY p.estadoByEstadoId.id DESC")
    public List<ProductoEntity> buscarProductosPorEstadoDescEntreFechas(@Param("fIni") Date fIni,
                                                                       @Param("fFin") Date fFin);
}
