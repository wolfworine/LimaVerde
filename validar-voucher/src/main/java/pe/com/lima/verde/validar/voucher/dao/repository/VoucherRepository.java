package pe.com.lima.verde.validar.voucher.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.lima.verde.validar.voucher.dao.entity.VoucherEntity;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
	
	List<VoucherEntity> findByIdUsuario(Long idUsuario);

}
