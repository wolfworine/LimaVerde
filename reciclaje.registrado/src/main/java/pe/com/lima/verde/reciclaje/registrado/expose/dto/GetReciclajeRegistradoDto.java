package pe.com.lima.verde.reciclaje.registrado.expose.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaResponseDto;

@Data
@Builder
public class GetReciclajeRegistradoDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private AuditoriaResponseDto auditoria;
	private Response response;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Response implements Serializable {
		private static final long serialVersionUID = -526261566498569L;
        private Long totalItems;
        private List<ReciclajeHistorialDto> ListaHistoReciclaje;
        private Integer totalPages;
        private Integer currentPage;
	}
	

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReciclajeHistorialDto implements Serializable{
	   private static final long serialVersionUID = -526261566498569L;
	   private Long idPuntoReciclaje;
	   private LocalDateTime fechaRegistro;
	   private Double puntosAcumulados;
	   private String nombre;

}
}
