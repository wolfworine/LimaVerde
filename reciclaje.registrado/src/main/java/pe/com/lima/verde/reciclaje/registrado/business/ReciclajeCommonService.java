package pe.com.lima.verde.reciclaje.registrado.business;


import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto.ReciclajeHistorialDto;

public interface ReciclajeCommonService {
 
  ReciclajeHistorialDto getObjectToHistorialReciclajeDto(Object[] object);
  
}
