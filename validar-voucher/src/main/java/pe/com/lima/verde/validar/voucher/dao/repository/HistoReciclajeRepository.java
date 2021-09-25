package pe.com.lima.verde.validar.voucher.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.validar.voucher.dao.entity.HistoReciclajeEntity;

@Repository
public interface HistoReciclajeRepository extends JpaRepository<HistoReciclajeEntity, Long> {

}
