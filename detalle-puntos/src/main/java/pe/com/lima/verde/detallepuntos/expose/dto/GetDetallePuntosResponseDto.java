package pe.com.lima.verde.detallepuntos.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaResponseDto;

@Data
@Builder
public class GetDetallePuntosResponseDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private AuditoriaResponseDto auditoria;
	private Response response;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Response implements Serializable {
		private static final long serialVersionUID = -1305074895774914370L;
		private DetallePuntosDto DetallePuntos;
		
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DetallePuntosDto implements Serializable {
		private static final long serialVersionUID = -1305074895774914370L;
		private Double totalPuntosAcumulados;
		private Double totalPuntosCanjeados;
		private Double totalPuntosXCanjear;
	}
}
