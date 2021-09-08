package pe.com.lima.verde.punto.reciclaje.business;

import java.util.Map;

import pe.com.lima.verde.punto.reciclaje.expose.dto.GetListPuntoReciclajeResponseDto;
import pe.com.lima.verde.punto.reciclaje.expose.dto.GetPuntoReciclajeResponseDto;

public interface PuntoReciclajeService {

	GetListPuntoReciclajeResponseDto getList(Map<String, String> map);

	GetPuntoReciclajeResponseDto getPunto(Long idPunto, Map<String, String> map);

}