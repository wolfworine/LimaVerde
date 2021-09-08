package pe.com.lima.verde.punto.reciclaje.business.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.punto.reciclaje.business.PuntoCommonService;
import pe.com.lima.verde.punto.reciclaje.business.PuntoReciclajeService;
import pe.com.lima.verde.punto.reciclaje.dao.entity.PuntoReciclajeEntity;
import pe.com.lima.verde.punto.reciclaje.dao.repository.PuntoReciclajeRepository;
import pe.com.lima.verde.punto.reciclaje.expose.dto.GetListPuntoReciclajeResponseDto;
import pe.com.lima.verde.punto.reciclaje.expose.dto.GetPuntoReciclajeResponseDto;
import pe.com.lima.verde.punto.reciclaje.expose.dto.PuntoReciclajeDto;
import pe.gob.osce.escaparate.libutilirario.exception.NotFoundException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.RequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class PuntoReciclajeServiceImpl implements PuntoReciclajeService {
	

    @Value("${lima.verde.pagination.size}")
	private Integer pageSizeDefault;
	
	@Autowired
	private PuntoReciclajeRepository puntoReciclajeRepository;
	@Autowired
	private PuntoCommonService puntoCommonService;
	
	@Override
	public GetListPuntoReciclajeResponseDto getList(Map<String, String> map) {
		Util.setParamAuditoriaRequestDtoInMap(map);
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, Util.getIdRastreoOfMap(map));
		AuditoriaRequestDto auditoriaRequestDto = Util.getAuditoriaRequestDtoOfMap(map);
		log.info("Request:\n" + Util.toJson(RequestDto.builder().auditoria(auditoriaRequestDto).build()));
		Util.validateParamAuditoriaRequestDtoOfMap(map);
		
		Pageable pageable = this.getPageable(map);
		Page<PuntoReciclajeEntity> pageEntities =this.puntoReciclajeRepository.findAll(pageable);
	    List<PuntoReciclajeEntity> puntoReciclajesEntity = pageEntities.getContent();
		List<PuntoReciclajeDto> puntoReciclajes= this.puntoCommonService.getListArrayObjectToListPuntoDto(puntoReciclajesEntity); 
				
		GetListPuntoReciclajeResponseDto.Response response = new GetListPuntoReciclajeResponseDto.Response();
		response.setTotalItems(pageEntities.getTotalElements());
		response.setPuntos(puntoReciclajes);
		response.setTotalPages(pageEntities.getTotalPages());
		response.setCurrentPage(pageEntities.getNumber());
		
		return GetListPuntoReciclajeResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
	}

	@Override
	public GetPuntoReciclajeResponseDto getPunto(Long idPunto, Map<String, String> map) {
		Util.setParamAuditoriaRequestDtoInMap(map);
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, Util.getIdRastreoOfMap(map));
		AuditoriaRequestDto auditoriaRequestDto = Util.getAuditoriaRequestDtoOfMap(map);
		log.info("Request:\n" + Util.toJson(RequestDto.builder().auditoria(auditoriaRequestDto).build()));
		Util.validateParamAuditoriaRequestDtoOfMap(map);
		
		PuntoReciclajeEntity puntoReciclajeEntity = this.puntoReciclajeRepository.findById(idPunto)
				.orElseThrow(() -> new NotFoundException(auditoriaRequestDto.getIdRastreo()));
		
		PuntoReciclajeDto proveedorDto = this.puntoCommonService.getEntityToDto(puntoReciclajeEntity);
		
		GetPuntoReciclajeResponseDto.Response response = new GetPuntoReciclajeResponseDto.Response();
		response.setPunto(proveedorDto);
		
		return GetPuntoReciclajeResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
	}
	
	  private Pageable getPageable(Map<String, String> map) {
		    Integer page = Util.getParamPageablePage(map);
		    Integer pageSize = Util.getParamPageablePageSize(map, this.pageSizeDefault);
		    return PageRequest.of(page, pageSize);
		  }

}
