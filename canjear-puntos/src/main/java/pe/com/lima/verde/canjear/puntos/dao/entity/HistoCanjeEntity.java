package pe.com.lima.verde.canjear.puntos.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Entity
@Table(name = "TB_HIST_CANJE", schema = "DB_A69D44_LVERDE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoCanjeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073811806074837825L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "N_ID_HIST_CANJE")
	private Long idHistCanje;
	@Column(name = "C_CODIGO_CANJE", nullable = true)
	private Long codigoCanje;
	@Column(name = "C_ID_USUARIO", nullable = true)
	private Long idUsuario;
	@Column(name = "C_ID_PUNTO_RECICLAJE", nullable = true)
	private Long idPuntoReciclaje;
	@Column(name = "D_FECHA_HORA_REGISTRO", nullable = true)
	private LocalDateTime fechaHoraRegistro;
	@Column(name = "N_ID_CUPON", nullable = true)
	private Long idCupon;
	@Column(name = "N_PUNTOS_CANJEADOS", nullable = true)
	private Long puntosCanjeados;
	@Column(name = "N_TOT_PASAJE", nullable = true)
	private Long totalPasaje;
	
}
