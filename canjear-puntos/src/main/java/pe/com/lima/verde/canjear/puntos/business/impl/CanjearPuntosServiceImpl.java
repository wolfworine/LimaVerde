package pe.com.lima.verde.canjear.puntos.business.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.canjear.puntos.business.CanjeCommonService;
import pe.com.lima.verde.canjear.puntos.business.CanjearPuntosService;
import pe.com.lima.verde.canjear.puntos.dao.entity.HistoCanjeEntity;
import pe.com.lima.verde.canjear.puntos.dao.repository.HistoCanjeRepository;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosRequestDto;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosResponseDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class CanjearPuntosServiceImpl implements CanjearPuntosService {
	
    @Value("${lima.verde.pagination.size}")
	private Integer pageSizeDefault;

	@Autowired
	private HistoCanjeRepository histoCanjeRepository;
	
	@Autowired
	private CanjeCommonService canjeCommonService;
	
	@Override
	public PostCanjearPuntosResponseDto canjearPuntos(PostCanjearPuntosRequestDto postCanjearPuntosRequestDto) {
		AuditoriaRequestDto auditoriaRequestDto = Util
				.setParamAuditoriaRequestDtoInDto(postCanjearPuntosRequestDto.getAuditoria());
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		postCanjearPuntosRequestDto.setAuditoria(auditoriaRequestDto);
		log.info("Request:\n" + Util.toJson(postCanjearPuntosRequestDto));
		Util.validateParamAuditoriaRequestDtoOfDto(auditoriaRequestDto);

    	SimpleDateFormat sdf = new SimpleDateFormat(Util.DATE_FORMAT_TYPE_ONE);
		LocalDateTime localDateTime = Util.getLocalDateTime(sdf.format(new Date()), Util.DATE_FORMAT_TYPE_ONE);
    	
		HistoCanjeEntity histoCanjeEntity=this.canjeCommonService.getEntityToDto(postCanjearPuntosRequestDto.getRequest().getHistorialCanje());
		histoCanjeEntity.setFechaHoraRegistro(localDateTime);
		
		Long idHistCanje= this.histoCanjeRepository.save(histoCanjeEntity).getIdHistCanje();
		
		PostCanjearPuntosResponseDto.Response response = new PostCanjearPuntosResponseDto.Response();
		response.setIdHistCanje(idHistCanje);
		response.setMensaje("Puntos Canjeados");
		
		PostCanjearPuntosResponseDto postCanjearPuntosResponseDto = PostCanjearPuntosResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();

		log.info("Response: Puntos Canjeados \n" + Util.toJson(postCanjearPuntosResponseDto));
		return postCanjearPuntosResponseDto;
	}

}
