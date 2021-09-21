package pe.com.lima.verde.reciclaje.registrado.dao.entity;


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
@Table(name = "TB_PUNTOS_RECICLAJE", schema = "db_a69d44_lverde")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuntosReciclajeEntity {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "N_ID_PUNTOS_RECICLAJE")
	  private Long idPuntoReciclaje;

	  @Column(name = "N_LONGITUD", nullable = true)
	  private Long longitud;

	  @Column(name = "N_LATITUD", nullable = true)
	  private Long latitud;

	  @Column(name = "C_NOMBRE", nullable = true)
	  private String nombre;

	  @Column(name = "C_DIRECCION", nullable = true)
	  private String direccion;

	  @Column(name = "C_PORTADA", nullable = true)
	  private String portada;
}
