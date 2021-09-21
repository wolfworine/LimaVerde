package pe.com.lima.verde.reciclaje.registrado.dao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.reciclaje.registrado.dao.entity.ReciclajeRegistradoEntity;

@Repository
public interface ReciclajeRegistrdoRepository extends JpaRepository<ReciclajeRegistradoEntity, Long> {

Optional<Page<ReciclajeRegistradoEntity>> findByIdUsuario(Long idUsuario, Pageable pageable);

}
