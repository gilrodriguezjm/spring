package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.TransaccionEntity;
import es.taw.springsalidos.entity.TransaccionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<TransaccionEntity, TransaccionEntityPK> {

    @Query("SELECT t FROM TransaccionEntity t WHERE t.tipo = :tipo and t.fechaTransaccion >= :fIni and t.fechaTransaccion <= :fFin ORDER BY t.precioCompra ASC")
    public List<TransaccionEntity> buscarTransaccionesPorTipoEnOrdenAscEntreFechas(@Param("tipo") String tipo ,
                                                                                   @Param("fIni") Date fIni,
                                                                                   @Param("fFin") Date fFin);

    @Query("SELECT t FROM TransaccionEntity t WHERE t.tipo = :tipo and t.fechaTransaccion >= :fIni and t.fechaTransaccion <= :fFin ORDER BY t.precioCompra DESC")
    public List<TransaccionEntity> buscarTransaccionesPorTipoEnOrdenDescEntreFechas(@Param("tipo") String tipo ,
                                                                                   @Param("fIni") Date fIni,
                                                                                   @Param("fFin") Date fFin);

    @Query("Select t from TransaccionEntity t where t.productoByProductoId.id = :id")
    public List<TransaccionEntity> findByProductoId(@Param("id") int id);
}
