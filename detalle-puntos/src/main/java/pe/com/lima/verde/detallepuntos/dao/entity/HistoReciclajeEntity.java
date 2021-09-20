package pe.com.lima.verde.detallepuntos.dao.entity;

import java.io.Serializable;
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
@Table(name = "TB_HIST_RECICLAJE", schema = "DB_A69D44_LVERDE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoReciclajeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "N_ID_HIST_RECI")
	private Long idHistReci;
	@Column(name = "C_CODIGO_REG", nullable = true)
	private Long codRegistro;
	@Column(name = "N_ID_VOUCHER", nullable = true)
	private Long idVoucher;
	@Column(name = "C_ID_PUNTO_RECICLAJE", nullable = true)
	private Long idPuntoReciclaje;
	@Column(name = "D_FECHA_HORA_REGISTRO", nullable = true)
	private LocalDateTime fechaHoraRegistro;
	@Column(name = "C_PUNTOS_ACUMU", nullable = true)
	private Long puntosAcumulado;
	@Column(name = "N_ID_USUARIO", nullable = true)
	private Long idUsuario;
	
}
