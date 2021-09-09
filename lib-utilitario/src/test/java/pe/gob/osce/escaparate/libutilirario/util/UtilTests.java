package pe.gob.osce.escaparate.libutilirario.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pe.gob.osce.escaparate.libutilirario.enums.TipoItemEnum;
import pe.gob.osce.escaparate.libutilirario.enums.TipoPersoneriaEnum;
import pe.gob.osce.escaparate.libutilirario.exception.BadRequestException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;

@SpringBootTest
class UtilTests {
	
	
	private Map<String, String> getMapRequest() {
		Map<String, String> map = new HashMap<>();
		map.put(Constante.Auditoria.Parametro.ID_RASTREO,Constante.VACIO);
		map.put(Constante.Auditoria.Parametro.CANAL,Constante.UNO.toString());
		map.put(Constante.Auditoria.Parametro.USUARIO,Constante.UNO.toString());
		map.put(Constante.Auditoria.Parametro.FECHA,Constante.VACIO);
		return map;
	}

	@Test
	void localDate() {
		assertNotNull(Util.toJson(Util.getCurrentLocalDate()));
		assertNotNull(Util.getLocalDate("2020-12-15", "yyyy-MM-dd"));
		assertNull(Util.getLocalDate("", "yyyy-MM-dd"));
		assertNotNull(Util.getLocalDateTime("2020-12-15 15:27:30", Util.DATE_FORMAT_TYPE_ONE));
		assertNull(Util.getLocalDateTime("", Util.DATE_FORMAT_TYPE_ONE));
		assertNotNull(Util.getCurrentDateTimeStringFormat(Util.DATE_FORMAT_TYPE_ONE));
		assertNotNull(Util.getLocalDateTimeStringFormat(LocalDateTime.now(), Util.DATE_FORMAT_TYPE_ONE));
	}
	
	@Test
	void mapParam() {
		Map<String, String> map = new HashMap<>();
		Util.setParamAuditoriaRequestDtoInMap(map);
		assertNotNull(Util.getIdRastreoOfMap(map));
		//
		Map<String, String> map1 = this.getMapRequest();
		Util.setParamAuditoriaRequestDtoInMap(map1);
		assertNotNull(Util.getIdRastreoOfMap(map1));
		//
		Map<String, String> map2 = new HashMap<>();
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfMap(map2);});
		map2.put(Constante.Auditoria.Parametro.CANAL,"app");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfMap(map2);});
		map2.put(Constante.Auditoria.Parametro.USUARIO,"user");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfMap(map2);});
		map2.put(Constante.Auditoria.Parametro.FECHA,"2020-12-15 15:27:30");
		Util.validateParamAuditoriaRequestDtoOfMap(map2);
		assertNotNull(map2);
		//
		assertNotNull(Util.getAuditoriaRequestDtoOfMap(map1));
	}
	
	@Test
	void auditoriaRequest() {
		assertNotNull(Util.setParamAuditoriaRequestDtoInDto(null));
		assertNotNull(Util.setParamAuditoriaRequestDtoInDto(AuditoriaRequestDto.builder().build()));
		assertNotNull(Util.setParamAuditoriaRequestDtoInDto(AuditoriaRequestDto.builder().idRastreo("").fecha("").build()));
		//
		AuditoriaRequestDto audi = AuditoriaRequestDto.builder().build();
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfDto(audi);});
		audi.setCanal("");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfDto(audi);});
		audi.setCanal("app");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfDto(audi);});
		audi.setUsuario("");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfDto(audi);});
		audi.setUsuario("user");
		assertThrows(BadRequestException.class, () ->{Util.validateParamAuditoriaRequestDtoOfDto(audi);});
		audi.setFecha("2020-12-15 15:27:30");
		Util.validateParamAuditoriaRequestDtoOfDto(audi);
		assertNotNull(audi);
		//
		assertNotNull(Util.getAuditoriaResponseDto("sdsf",0,"sfs"));
		assertNotNull(Util.getAuditoriaRequestDtoToMap(AuditoriaRequestDto.builder().build()));
	}
	
	@Test
	void apiError() {
		assertNotNull(Util.getApiError("",Constante.CERO,"sfs"));
		assertNotNull(Util.getApiError("",Constante.UNO,"sfs"));
	}
	
	@Test
	void param() {
		Map<String, String> map = new HashMap<>();
		assertNotNull(Util.getParamPageablePage(map));
		map.put("page","4");
		assertNotNull(Util.getParamPageablePage(map));
		Integer page = null;
		assertNotNull(Util.getParamPageablePage(page));
		assertNotNull(Util.getParamPageablePage(9));
		//
		assertNotNull(Util.getParamPageablePageSize(map,10));
		map.put("pageSize","40");
		assertNotNull(Util.getParamPageablePageSize(map,10));
		Integer pageSize = null;
		assertNotNull(Util.getParamPageablePageSize(pageSize, 30));
		assertNotNull(Util.getParamPageablePageSize(9,10));
	}
	
	@Test
	void enumClass() {
		assertNotNull(TipoItemEnum.BIEN.getCode());
		assertNotNull(TipoItemEnum.BIEN.getDesc());
		assertNotNull(TipoItemEnum.SERVICIO.getCode());
		assertNotNull(TipoItemEnum.SERVICIO.getDesc());
		assertNotNull(TipoItemEnum.CONSULTOR_OBRA.getCode());
		assertNotNull(TipoItemEnum.CONSULTOR_OBRA.getDesc());
		assertNotNull(TipoItemEnum.EJECUTOR_OBRA.getCode());
		assertNotNull(TipoItemEnum.EJECUTOR_OBRA.getDesc());
		//
		assertNotNull(TipoPersoneriaEnum.PERSONAL.getCode());
		assertNotNull(TipoPersoneriaEnum.PERSONAL.getDesc());
		assertNotNull(TipoPersoneriaEnum.JURIDICA.getCode());
		assertNotNull(TipoPersoneriaEnum.JURIDICA.getDesc());
		
		assertNotNull(TipoPersoneriaEnum.getDescripcion(1));
		assertNotNull(TipoPersoneriaEnum.getDescripcion(2));
	}
	
	@Test
	void numero() {
		assertTrue(Util.isNumeric("2"));
		assertFalse(Util.isNumeric("sds"));
	}
}
