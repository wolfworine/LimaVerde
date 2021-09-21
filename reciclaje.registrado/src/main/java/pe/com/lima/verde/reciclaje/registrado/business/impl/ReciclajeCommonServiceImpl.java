package pe.com.lima.verde.reciclaje.registrado.business.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;


import pe.com.lima.verde.reciclaje.registrado.business.ReciclajeCommonService;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto.ReciclajeHistorialDto;


@Service
public class ReciclajeCommonServiceImpl implements ReciclajeCommonService {

	public ReciclajeHistorialDto getObjectToHistorialReciclajeDto(Object[] source) {
		return ReciclajeHistorialDto.builder().idPuntoReciclaje(Long.parseLong(source[0].toString()))
				.fechaRegistro(LocalDateTime.parse(source[1].toString()))
				.puntosAcumulados(Double.parseDouble(source[2].toString()))
				.nombre(source[3].toString())
				.build();
	}

	
}
