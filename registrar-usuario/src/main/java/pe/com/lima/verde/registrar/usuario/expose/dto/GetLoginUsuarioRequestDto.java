package pe.com.lima.verde.registrar.usuario.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;

@Data
@Builder
public class GetLoginUsuarioRequestDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private AuditoriaRequestDto auditoria;
	private Request request;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Request implements Serializable {
		private static final long serialVersionUID = -1305074895774914370L;
		private UsuarioDto usuario;
	}
	
}
