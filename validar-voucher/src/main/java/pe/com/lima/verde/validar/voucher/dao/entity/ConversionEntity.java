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
@Table(name = "TB_CONVERSION", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "N_ID_CONVERSION")
  private Long idConversion;
  
  @Column(name = "N_CXPUNTO", nullable = true)
  private Long punto;

  @Column(name = "C_ESTADO", nullable = true)
  private Long estado;

  @Column(name = "D_FECHA_INICIO", nullable = true)
  private LocalDateTime fechaInicio;

  @Column(name = "D_FECHA_FIN", nullable = true)
  private LocalDateTime fechaFin;

  @Column(name = "C_DESCRIPCION", nullable = true)
  private String descripcion;
}
