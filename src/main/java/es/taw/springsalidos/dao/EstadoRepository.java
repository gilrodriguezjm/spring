package es.taw.springsalidos.dao;

import es.taw.springsalidos.entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity,Integer> {


    @Query("Select e from EstadoEntity e where e.id = :id")
    public EstadoEntity encontrarPorId(int id);

}
