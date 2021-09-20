package pe.com.lima.verde.detallepuntos.business.impl;

import org.springframework.stereotype.Service;

import pe.com.lima.verde.detallepuntos.business.DetalleCommonService;
import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto.DetallePuntosDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto.ReciclajeDto;

@Service
public class DetalleCommonServiceImpl implements DetalleCommonService {

	public DetallePuntosDto getObjectToDetallePuntosDto(Object[] source) {
		return DetallePuntosDto.builder().totalPuntosAcumulados(Double.parseDouble(source[0].toString()))
				.totalPuntosCanjeados(Double.parseDouble(source[1].toString()))
				.build();
	}
	
	public ReciclajeDto getObjectToReciclajeDto(Object[] source) {
		return ReciclajeDto.builder().nVecesReciclado(Integer.parseInt(source[0].toString()))
				.totalPuntosAcumulados(Double.parseDouble(source[1].toString()))
				.build();
	}

}
