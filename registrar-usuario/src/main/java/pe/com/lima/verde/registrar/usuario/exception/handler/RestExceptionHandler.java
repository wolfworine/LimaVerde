package pe.com.lima.verde.registrar.usuario.exception.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osce.escaparate.libutilirario.exception.ApiError;
import pe.gob.osce.escaparate.libutilirario.exception.InternalServerErrorException;
import pe.gob.osce.escaparate.libutilirario.exception.NotFoundException;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Value("${lima.verde.exception.default.message}")
	private String defaultMessage;

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<ApiError> runtimeException(Exception ex, WebRequest request) {
		log.error("ERROR", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.getApiError(null,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name()));
	}

	@ExceptionHandler({ InternalServerErrorException.class })
	public ResponseEntity<ApiError> internalServerError(InternalServerErrorException ex, WebRequest request) {
		log.error("ERROR", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.getApiError(ex.getIdRastreo(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name()));
	}

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<ApiError> notFound(NotFoundException ex, WebRequest request) {
		log.error("ERROR", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Util.getApiError(ex.getIdRastreo(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name()));
	}
}
