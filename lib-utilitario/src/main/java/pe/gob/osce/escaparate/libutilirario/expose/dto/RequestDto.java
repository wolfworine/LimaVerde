package pe.gob.osce.escaparate.libutilirario.expose.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto implements Serializable{
	private static final long serialVersionUID = 1477072660852513495L;
	private AuditoriaRequestDto auditoria;
	private Map<String, String> map;
}
