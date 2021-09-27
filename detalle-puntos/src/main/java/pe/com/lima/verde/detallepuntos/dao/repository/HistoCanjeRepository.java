package pe.com.lima.verde.detallepuntos.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.detallepuntos.dao.entity.HistoCanjeEntity;

@Repository
public interface HistoCanjeRepository extends JpaRepository<HistoCanjeEntity, Long> {
	
	@Query(value ="select IFNULL(b.tota_hist_puntos,0),IFNULL(c.tota_puntos_can,0)  from (\n" + 
			"(select  sum(hr.c_puntos_acumu) as tota_hist_puntos \n" + 
			"from tb_hist_reciclaje hr \n" + 
			"where hr.n_id_usuario = :idUsuario)b, \n" + 
			"(select  sum(hc.n_puntos_canjeados) as tota_puntos_can \n" + 
			"from tb_hist_canje hc \n" + 
			"where hc.c_id_usuario = :idUsuario)c) \n",
			nativeQuery = true)
	List<Object[]> getDetallePuntos(@Param("idUsuario") Integer idUsuario);
}
