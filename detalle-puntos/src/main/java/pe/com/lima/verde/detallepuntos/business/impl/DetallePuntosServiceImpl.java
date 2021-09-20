package pe.com.lima.verde.detallepuntos.business.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.detallepuntos.business.DetalleCommonService;
import pe.com.lima.verde.detallepuntos.business.DetallePuntosService;
import pe.com.lima.verde.detallepuntos.dao.repository.HistoCanjeRepository;
import pe.com.lima.verde.detallepuntos.dao.repository.HistoReciclajeRepository;
import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto.DetallePuntosDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto.ReciclajeDto;
import pe.gob.osce.escaparate.libutilirario.exception.NotFoundException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.RequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class DetallePuntosServiceImpl implements DetallePuntosService {
	
	@Autowired
	private HistoReciclajeRepository histoReciclajeRepository;
	
	@Autowired
	private HistoCanjeRepository histoCanjeRepository;
	
	@Autowired
	private DetalleCommonService detalleCommonService;
	
	@Override
	public GetDetallePuntosResponseDto getDetallePuntos(Integer idUsuario, Map<String, String> map) {
		Util.setParamAuditoriaRequestDtoInMap(map);
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, Util.getIdRastreoOfMap(map));
		AuditoriaRequestDto auditoriaRequestDto = Util.getAuditoriaRequestDtoOfMap(map);
		log.info("Request:\n" + Util.toJson(RequestDto.builder().auditoria(auditoriaRequestDto).build()));
		Util.validateParamAuditoriaRequestDtoOfMap(map);
		
        List<Object[]> listArrayObject = this.histoCanjeRepository.getDetallePuntos(idUsuario);
        if (listArrayObject.isEmpty()) throw new NotFoundException(auditoriaRequestDto.getIdRastreo());
        Object[] arrayObject = listArrayObject.get(0);
        
		DetallePuntosDto detallePuntos= this.detalleCommonService.getObjectToDetallePuntosDto(arrayObject); //
		//Long resto=Long.valueOf(detallePuntos.getTotalPuntosAcumulados())-Long.valueOf(detallePuntos.getTotalPuntosCargados());
		
		detallePuntos.setTotalPuntosXCargar(detallePuntos.getTotalPuntosAcumulados()-detallePuntos.getTotalPuntosCargados());
		
		GetDetallePuntosResponseDto.Response response = new GetDetallePuntosResponseDto.Response();
		response.setDetallePuntos(detallePuntos);
		
		GetDetallePuntosResponseDto getDetallePuntosResponseDto = GetDetallePuntosResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
		
		return getDetallePuntosResponseDto;
	}
	
	@Override
	public GetMiReciclajeResponseDto getMiReciclaje(Integer idUsuario, Map<String, String> map) {
		Util.setParamAuditoriaRequestDtoInMap(map);
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, Util.getIdRastreoOfMap(map));
		AuditoriaRequestDto auditoriaRequestDto = Util.getAuditoriaRequestDtoOfMap(map);
		log.info("Request:\n" + Util.toJson(RequestDto.builder().auditoria(auditoriaRequestDto).build()));
		Util.validateParamAuditoriaRequestDtoOfMap(map);
		
        List<Object[]> listArrayObject = this.histoReciclajeRepository.getMiReciclaje(idUsuario);
        if (listArrayObject.isEmpty()) throw new NotFoundException(auditoriaRequestDto.getIdRastreo());
        Object[] arrayObject = listArrayObject.get(0);
		ReciclajeDto reciclajeDto= this.detalleCommonService.getObjectToReciclajeDto(arrayObject); 
		
		GetMiReciclajeResponseDto.Response response = new GetMiReciclajeResponseDto.Response();
		response.setMiReciclaje(reciclajeDto);
		
		GetMiReciclajeResponseDto getMiReciclajeResponseDto = GetMiReciclajeResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
		
		return getMiReciclajeResponseDto;
	}

}
