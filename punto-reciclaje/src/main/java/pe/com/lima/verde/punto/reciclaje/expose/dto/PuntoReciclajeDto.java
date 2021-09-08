package pe.com.lima.verde.punto.reciclaje.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuntoReciclajeDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private Long idPuntoReciclaje;
	private Long longitud;
	private Long latitud;
	private String nombre;
	private String direccion;
	private String portada;
}
