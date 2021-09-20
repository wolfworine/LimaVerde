package pe.com.lima.verde.detallepuntos.business;

import java.util.Map;

import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto;

public interface DetallePuntosService {

	GetDetallePuntosResponseDto getDetallePuntos(Integer idUsuario, Map<String, String> map);
	
	GetMiReciclajeResponseDto getMiReciclaje(Integer idUsuario, Map<String, String> map);

}