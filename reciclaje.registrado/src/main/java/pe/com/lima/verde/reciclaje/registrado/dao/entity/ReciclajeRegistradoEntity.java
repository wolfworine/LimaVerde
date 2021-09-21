package pe.com.lima.verde.reciclaje.registrado.dao.entity;


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
@Table(name = "TB_HIST_RECICLAJE", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReciclajeRegistradoEntity {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "N_ID_HIST_RECI")
	  private Long idHistorialReciclaje;

	  @Column(name = "C_CODIGO_REG", nullable = true)
	  private Long CodigoRegistrado;

	  @Column(name = "N_ID_VOUCHER", nullable = true)
	  private Long idVoucher;

	  @Column(name = "C_ID_PUNTO_RECICLAJE", nullable = true)
	  private Long idPuntoReciclaje;

	  @Column(name = "D_FECHA_HORA_REGISTRO", nullable = true)
	  private LocalDateTime fechaRegistro;

	  @Column(name = "C_PUNTOS_ACUMU", nullable = true)
	  private Double puntosAcumulados;
	  
	  @Column(name="N_ID_USUARIO",nullable = true)
	  private Long idUsuario;
	
}
