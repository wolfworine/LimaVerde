package pe.com.lima.verde.reciclaje.registrado.business;

import java.util.Map;

import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto;

public interface ReciclajeRegistradoService {
   GetReciclajeRegistradoDto getHistorialReciclaje(Long idUsuario, Map<String, String> map);
}
