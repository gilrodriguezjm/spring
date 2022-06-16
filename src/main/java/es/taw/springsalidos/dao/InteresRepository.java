package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.InteresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresRepository extends JpaRepository<InteresEntity,Integer> {

    @Query("select i from InteresEntity i where i.id = :id ")
    public List<InteresEntity> buscarPorId(@Param("id") int id);

    @Query("select i from InteresEntity i where i.id = :id ")
    public InteresEntity encontrarPorId(@Param("id") int id);

}
