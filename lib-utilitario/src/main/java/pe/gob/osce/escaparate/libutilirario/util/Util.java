package pe.gob.osce.escaparate.libutilirario.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osce.escaparate.libutilirario.exception.ApiError;
import pe.gob.osce.escaparate.libutilirario.exception.BadRequestException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaResponseDto;

@Slf4j
public class Util {
	
	public static final String DATE_FORMAT_TYPE_ONE = "yyyy-MM-dd HH:mm:ss";
	
	private Util() {
	}
	
	public static String toJson(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
	
	public static LocalDate getCurrentLocalDate() {
		return LocalDate.now();
    }
	
	public static LocalDate getLocalDate(String stringDate, String formatPattern) {
		LocalDate localDate = null;
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern(formatPattern);
			localDate = LocalDate.parse(stringDate, format);
		} catch (Exception e) {
			log.info("Error LocalDate format", e);
		}
		return localDate;
    }
	
	public static LocalDateTime getLocalDateTime(String stringDate, String formatPattern) {
		LocalDateTime localDateTime = null;
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern(formatPattern);
			localDateTime = LocalDateTime.parse(stringDate, format);
		} catch (Exception e) {
			log.info("Error LocalDate format", e);
		}
		return localDateTime;
    }

	public static String getDateTimeVig(String stringDate, String formatPattern,String formatPattern2) {
		String fecha="";
		try {
			  SimpleDateFormat dt = new SimpleDateFormat(formatPattern); 
			  Date date = dt.parse(stringDate); 
			  SimpleDateFormat dt1 = new SimpleDateFormat(formatPattern2);
			  fecha=dt1.format(date);
		} catch (Exception e) {
			log.info("Error SimpleDate format", e);
		}
		return fecha;
    }
	
	public static String getLocalDateTimeStringFormat(LocalDateTime localDateTime, String formatPattern) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(formatPattern);
		return format.format(localDateTime);
    }
	
	public static String getCurrentDateTimeStringFormat(String formatPattern) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(formatPattern);
		LocalDateTime localDateTime = LocalDateTime.now();
		return format.format(localDateTime);
    }

	public static void setParamAuditoriaRequestDtoInMap(Map<String, String> map) {
		String idRastreo = map.get(Constante.Auditoria.Parametro.ID_RASTREO);
		if (idRastreo==null || idRastreo.trim().equals(Constante.VACIO)) 
			map.put(Constante.Auditoria.Parametro.ID_RASTREO, UUID.randomUUID().toString());
		String fecha = map.get(Constante.Auditoria.Parametro.FECHA);
		if (fecha==null || fecha.trim().equals(Constante.VACIO)) 
			map.put(Constante.Auditoria.Parametro.FECHA, Util.getCurrentDateTimeStringFormat(Util.DATE_FORMAT_TYPE_ONE));
	}
	
	public static String getIdRastreoOfMap(Map<String, String> map) {
		return map.get(Constante.Auditoria.Parametro.ID_RASTREO);
	}
	
	public static void validateParamAuditoriaRequestDtoOfMap(Map<String, String> map) {
		String idRastreo = map.get(Constante.Auditoria.Parametro.ID_RASTREO);
		String canal = map.get(Constante.Auditoria.Parametro.CANAL);
		String usuario = map.get(Constante.Auditoria.Parametro.USUARIO);
		LocalDateTime fecha = Util.getLocalDateTime(map.get(Constante.Auditoria.Parametro.FECHA), Util.DATE_FORMAT_TYPE_ONE);
		if (canal==null || canal.trim().equals(Constante.VACIO) ||
			usuario==null || usuario.trim().equals(Constante.VACIO)	||
			fecha==null) 
			throw new BadRequestException("Error parametros de auditoria requeridos: canal, usuario y fecha(format)", idRastreo);
	}

	public static AuditoriaRequestDto getAuditoriaRequestDtoOfMap(Map<String, String> map) {
		return AuditoriaRequestDto.builder()
				.idRastreo(map.get(Constante.Auditoria.Parametro.ID_RASTREO))
				.canal(map.get(Constante.Auditoria.Parametro.CANAL))
				.usuario(map.get(Constante.Auditoria.Parametro.USUARIO))
				.fecha(map.get(Constante.Auditoria.Parametro.FECHA))
				.build();
	}
	
	public static AuditoriaRequestDto setParamAuditoriaRequestDtoInDto(AuditoriaRequestDto auditoriaRequestDto) {
		if (auditoriaRequestDto==null) {
			auditoriaRequestDto = new AuditoriaRequestDto();
			auditoriaRequestDto.setIdRastreo(UUID.randomUUID().toString());
			auditoriaRequestDto.setFecha(Util.getCurrentDateTimeStringFormat(Util.DATE_FORMAT_TYPE_ONE));
		} else {
			String idRastreo = auditoriaRequestDto.getIdRastreo();
			if (idRastreo==null || idRastreo.trim().equals(Constante.VACIO)) 
				auditoriaRequestDto.setIdRastreo(UUID.randomUUID().toString());
			String fecha = auditoriaRequestDto.getFecha();
			if (fecha==null || fecha.trim().equals(Constante.VACIO))
				auditoriaRequestDto.setFecha(Util.getCurrentDateTimeStringFormat(Util.DATE_FORMAT_TYPE_ONE));
		}
		return auditoriaRequestDto;
	}
	
	public static void validateParamAuditoriaRequestDtoOfDto(AuditoriaRequestDto auditoriaRequestDto) {
		String idRastreo = auditoriaRequestDto.getIdRastreo();
		String canal = auditoriaRequestDto.getCanal();
		String usuario = auditoriaRequestDto.getUsuario();
		LocalDateTime fecha = Util.getLocalDateTime(auditoriaRequestDto.getFecha(), Util.DATE_FORMAT_TYPE_ONE);
		if (canal==null || canal.trim().equals(Constante.VACIO) ||
			usuario==null || usuario.trim().equals(Constante.VACIO)	||
			fecha==null) 
			throw new BadRequestException("Error parametros de auditoria requeridos: canal, usuario y fecha(format)", idRastreo);
	}
	
	public static AuditoriaResponseDto getAuditoriaResponseDto(String idRastreo, int codigo, String mensaje) {
		return AuditoriaResponseDto.builder()
				.idRastreo(idRastreo)
				.codigo(codigo)
				.mensaje(mensaje)
				.build();
	}
	
	public static Map<String, String> getAuditoriaRequestDtoToMap(AuditoriaRequestDto auditoriaRequestDto) {
		Map<String, String> map = new HashMap<>();
		map.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		map.put(Constante.Auditoria.Parametro.CANAL, auditoriaRequestDto.getCanal());
		map.put(Constante.Auditoria.Parametro.USUARIO, auditoriaRequestDto.getUsuario());
		map.put(Constante.Auditoria.Parametro.FECHA, auditoriaRequestDto.getFecha());
		return map;
	}
	
	public static ApiError getApiError(String idRastreo, int codigo, String mensaje) {
		
		AuditoriaResponseDto auditoria = AuditoriaResponseDto.builder()
											.idRastreo(idRastreo)
											.codigo(codigo)
											.mensaje(mensaje)
											.build();
		ApiError apiError = new ApiError(auditoria);
		log.info("Response: error\n"+Util.toJson(apiError));
		return apiError;
	}
	
	public static Integer getParamPageablePage(Map<String, String> map) {
		Integer page;
		try {
			page = Integer.parseInt(map.get("page"));
		} catch (Exception e) {
			log.info("Error - param page is invalid, default value 0");
			page = Constante.CERO;
		}
		return page;
	}
	
	public static Integer getParamPageablePage(Integer page) {
		if (page==null) {
			log.info("Error - param page is invalid, default value 0");
			page=Constante.CERO;
		}
		return page;
	}
	
	public static Integer getParamPageablePageSize(Map<String, String> map, Integer pageSizeDefault) {
		Integer pageSize;
		try {
			pageSize = Integer.parseInt(map.get("pageSize"));
		} catch (Exception e) {
			log.info("Error - param pageSize is invalid, default value "+pageSizeDefault);
			pageSize = pageSizeDefault;
		}
		return pageSize;
	}
	
	public static Integer getParamPageablePageSize(Integer pageSize, Integer pageSizeDefault) {
		if (pageSize==null) {
			log.info("Error - param pageSize is invalid, default value "+pageSizeDefault);
			pageSize=pageSizeDefault;
		}
		return pageSize;
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
