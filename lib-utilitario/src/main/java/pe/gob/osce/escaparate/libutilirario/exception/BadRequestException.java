package pe.gob.osce.escaparate.libutilirario.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class BadRequestException  extends RuntimeException{
	
	@Getter
	private final String idRastreo;
	
	public BadRequestException(String idRastreo) {
		super("Internal Server Error");
		this.idRastreo = idRastreo;
	}
	
	public BadRequestException(String message, String idRastreo) {
		super(message);
		this.idRastreo = idRastreo;
	}
}
