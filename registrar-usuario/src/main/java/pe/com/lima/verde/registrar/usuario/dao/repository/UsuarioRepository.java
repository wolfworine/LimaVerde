package pe.com.lima.verde.registrar.usuario.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.registrar.usuario.dao.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	  Optional<UsuarioEntity> findByEmail(String email);
	  Optional<UsuarioEntity> findByUsername(String username);
	
}
