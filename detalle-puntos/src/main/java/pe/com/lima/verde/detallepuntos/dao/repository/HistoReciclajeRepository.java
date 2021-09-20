package pe.com.lima.verde.detallepuntos.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.detallepuntos.dao.entity.HistoReciclajeEntity;

@Repository
public interface HistoReciclajeRepository extends JpaRepository<HistoReciclajeEntity, Long> {

	@Query(value ="select count(hr.c_puntos_acumu) as num,sum(hr.c_puntos_acumu) as tota_hist_puntos \n" + 
			"from tb_hist_reciclaje hr \n" + 
			"where hr.n_id_usuario = :idUsuario \n" ,
			nativeQuery = true)
	List<Object[]> getMiReciclaje(@Param("idUsuario") Integer idUsuario);
}
