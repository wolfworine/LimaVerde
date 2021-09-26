package pe.com.lima.verde.canjear.puntos.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;

@Data
@Builder
public class PostCanjearPuntosRequestDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private AuditoriaRequestDto auditoria;
	private Request request;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Request implements Serializable {
		private static final long serialVersionUID = -1305074895774914370L;
		private HistoCanjeDto HistorialCanje;

	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class HistoCanjeDto implements Serializable {
		private static final long serialVersionUID = -1305074895774914370L;
	    private Long codigoCanje;
	    private Long idUsuario;
	    private Long idPuntoReciclaje;
	    private Long idCupon;
	    private Long puntosCanjeados;
	    private Long totalPasaje;
	}

}
