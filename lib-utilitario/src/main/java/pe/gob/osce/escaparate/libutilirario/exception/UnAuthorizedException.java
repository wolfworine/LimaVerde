package pe.gob.osce.escaparate.libutilirario.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class UnAuthorizedException extends RuntimeException{
	
	@Getter
	private final String idRastreo;
	
	public UnAuthorizedException(String idRastreo) {
		super("Not Found");
		this.idRastreo = idRastreo;
	}
	
	public UnAuthorizedException(String message, String idRastreo) {
		super(message);
		this.idRastreo = idRastreo;
	}
}
