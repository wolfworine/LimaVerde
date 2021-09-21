package pe.com.lima.verde.reciclaje.registrado.business;


import java.util.List;

import pe.com.lima.verde.reciclaje.registrado.dao.entity.ReciclajeRegistradoEntity;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto.ReciclajeHistorialDto;

public interface ReciclajeCommonService {
 
	List<ReciclajeHistorialDto>  getObjectToHistorialReciclajeDto(List<ReciclajeRegistradoEntity> listaSource);
  
}
