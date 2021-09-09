package pe.gob.osce.escaparate.libutilirario.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
	
	@Getter
	private final String idRastreo;
	
	public NotFoundException(String idRastreo) {
		super("Not Found");
		this.idRastreo = idRastreo;
	}
	
	public NotFoundException(String message, String idRastreo) {
		super(message);
		this.idRastreo = idRastreo;
	}

}
