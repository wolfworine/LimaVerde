package pe.gob.osce.escaparate.libutilirario.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class InternalServerErrorException extends RuntimeException{

	@Getter
	private final String idRastreo;
	
	public InternalServerErrorException(String idRastreo) {
		super("Internal Server Error");
		this.idRastreo = idRastreo;
	}
	
	public InternalServerErrorException(String message,String idRastreo) {
		super(message);
		this.idRastreo = idRastreo;
	}
}
