package pe.com.lima.verde.detallepuntos.business;

import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto.DetallePuntosDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto.ReciclajeDto;

public interface DetalleCommonService {

	DetallePuntosDto getObjectToDetallePuntosDto(Object[] object);

	ReciclajeDto getObjectToReciclajeDto(Object[] object);

}
