package pe.com.lima.verde.validar.voucher.dao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_VOUCHER", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "N_ID_VOUCHER")
  private Long idVoucher;

  @Column(name = "C_CODIGO", nullable = true)
  private Long codigo;

  @Column(name = "N_BOT_REG", nullable = true)
  private Long nroBotReg;

  @Column(name = "C_ID_PUNTO_RECICLAJE", nullable = true)
  private Long idPuntoReciclaje;

  @Column(name = "D_FECHA_HORA_REG", nullable = true)
  private LocalDateTime fechaHoraReg;

  @Column(name = "C_ESTADO", nullable = true)
  private String estado;
  
  @Column(name = "N_ID_USUARIO")
  private Long idUsuario;
}
