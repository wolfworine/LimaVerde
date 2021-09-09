package pe.gob.osce.escaparate.libutilirario.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable{
	private static final long serialVersionUID = 8369713105982059180L;
	private AuditoriaResponseDto auditoria;
}
