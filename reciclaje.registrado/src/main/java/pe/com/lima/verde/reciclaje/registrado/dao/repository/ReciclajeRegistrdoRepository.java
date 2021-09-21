package pe.com.lima.verde.reciclaje.registrado.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.reciclaje.registrado.dao.entity.ReciclajeRegistradoEntity;

@Repository
public interface ReciclajeRegistrdoRepository extends JpaRepository<ReciclajeRegistradoEntity, Long> {
	
	@Query(value ="select hr.n_id_hist_reci,pr.c_nombre,hr.d_fecha_hora_registro, hr.c_puntos_acumu \n"
			+ "from db_a69d44_lverde.tb_hist_reciclaje hr \n"
			+ "inner join db_a69d44_lverde.tb_puntos_reciclaje pr \n"
			+ "on pr.n_id_puntos_reciclaje = hr.c_id_punto_reciclaje \n"
			+ "where n_id_usuario = 8; \n",
			nativeQuery = true)
	List<Object[]> getHistorialReciclaje(@Param("idUsuario") Integer idUsuario);
}
