package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Query("Select p from ProductoEntity p join p.transaccionsById t where p.id = t.personaByPersonaId.id and t.personaByPersonaId.id = :id and t.tipo = 'puja' or t.tipo = 'venta'")
    public List<ProductoEntity> findVentas(@Param("id") int id);

}
