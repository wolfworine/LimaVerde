package pe.com.lima.verde.reciclaje.registrado.business.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.reciclaje.registrado.business.ReciclajeCommonService;
import pe.com.lima.verde.reciclaje.registrado.business.ReciclajeRegistradoService;
import pe.com.lima.verde.reciclaje.registrado.dao.repository.ReciclajeRegistrdoRepository;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto.ReciclajeHistorialDto;
import pe.gob.osce.escaparate.libutilirario.exception.NotFoundException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.RequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class ReciclajeRegistradoServiceImpl implements ReciclajeRegistradoService {
	@Value("${lima.verde.pagination.size}")
	private Integer pageSizeDefault;
	
	@Autowired
	private ReciclajeRegistrdoRepository reciclajeRegistradoRepository;
	@Autowired
	private ReciclajeCommonService reciclajeCommonService;
	@Override
	public GetReciclajeRegistradoDto getHistorialReciclaje(Integer idUsuario, Map<String, String> map) {
		Util.setParamAuditoriaRequestDtoInMap(map);
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, Util.getIdRastreoOfMap(map));
		AuditoriaRequestDto auditoriaRequestDto = Util.getAuditoriaRequestDtoOfMap(map);
		log.info("Request:\n" + Util.toJson(RequestDto.builder().auditoria(auditoriaRequestDto).build()));
		Util.validateParamAuditoriaRequestDtoOfMap(map);
		
		Pageable pageable = this.getPageable(map);
		List<Object[]> listArrayObject = this.reciclajeRegistradoRepository.getHistorialReciclaje(idUsuario);
        if (listArrayObject.isEmpty()) throw new NotFoundException(auditoriaRequestDto.getIdRastreo());
        Object[] arrayObject = listArrayObject.get(0);
        
        ReciclajeHistorialDto historial= this.reciclajeCommonService.getObjectToHistorialReciclajeDto(arrayObject); //		
		
		GetReciclajeRegistradoDto.Response response = new GetReciclajeRegistradoDto.Response();
		response.setHistorialReciclaje(historial);
		
		GetReciclajeRegistradoDto getReciclajeRegistraDto = GetReciclajeRegistradoDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
		
		return getReciclajeRegistraDto;
	}
	
	private Pageable getPageable(Map<String, String> map) {
	    Integer page = Util.getParamPageablePage(map);
	    Integer pageSize = Util.getParamPageablePageSize(map, this.pageSizeDefault);
	    return PageRequest.of(page, pageSize);
	  }

}
