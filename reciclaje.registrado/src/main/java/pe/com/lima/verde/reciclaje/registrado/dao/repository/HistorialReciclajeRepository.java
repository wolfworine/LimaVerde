package pe.com.lima.verde.reciclaje.registrado.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.reciclaje.registrado.dao.entity.PuntosReciclajeEntity;

@Repository
public interface HistorialReciclajeRepository extends JpaRepository<PuntosReciclajeEntity, Long> {

}
