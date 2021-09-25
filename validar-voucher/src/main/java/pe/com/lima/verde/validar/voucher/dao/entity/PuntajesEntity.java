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
@Table(name = "TB_PUNTAJES", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuntajesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "N_ID_PUNTAJES")
  private Long idPuntajes;

  @Column(name = "N_ID_USU", nullable = true)
  private Long idUsuario;

  @Column(name = "C_TIPO_OPERA", nullable = true)
  private Long tipoOperacion;

  @Column(name = "N_PUNTAJE", nullable = true)
  private Long puntaje;

  @Column(name = "C_USU_CRE", nullable = true)
  private String usuarioCreacion;

  @Column(name = "D_FECHACRE_REG", nullable = true)
  private LocalDateTime fechaRegistro;

}
