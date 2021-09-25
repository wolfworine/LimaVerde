package pe.com.lima.verde.validar.voucher.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.validar.voucher.dao.entity.PuntajesEntity;

@Repository
public interface PuntajesRepository extends JpaRepository<PuntajesEntity, Long> {

}
