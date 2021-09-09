package pe.gob.osce.escaparate.libutilirario.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaResponseDto implements Serializable {
	private static final long serialVersionUID = 7086994751342302300L;
	private String idRastreo;
	private Integer codigo;
	private String mensaje;
}
