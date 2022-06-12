package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.PersonaEntity;
import es.taw.springsalidos.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Query("Select p from ProductoEntity p join p.transaccionsById t where p.id = t.productoByProductoId.id and t.personaByPersonaId.id = :id and t.tipo = 'venta'")
    public List<ProductoEntity> findVentas(@Param("id") int id);

    @Query("Select p from ProductoEntity p join p.transaccionsById t where p.id = t.productoByProductoId.id and t.personaByPersonaId.id = :id and t.tipo = 'puja'")
    public List<ProductoEntity> findPujas(@Param("id") int id);

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
