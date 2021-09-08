package pe.com.lima.verde.punto.reciclaje.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.punto.reciclaje.dao.entity.PuntoReciclajeEntity;

@Repository
public interface PuntoReciclajeRepository extends JpaRepository<PuntoReciclajeEntity, Long> {

}
