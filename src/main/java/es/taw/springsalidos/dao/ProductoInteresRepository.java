package es.taw.springsalidos.dao;


import es.taw.springsalidos.entity.ProductoInteresEntity;
import es.taw.springsalidos.entity.ProductoInteresEntityPK;
import es.taw.springsalidos.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoInteresRepository extends JpaRepository<ProductoInteresEntity, ProductoInteresEntityPK> {
    @Query("Select p from ProductoInteresEntity p where p.productoByProductoId.id = :id")
    public List<ProductoInteresEntity> findByProductoId(@Param("id") int id);

}
