package pe.com.lima.verde.punto.reciclaje.business;

import java.util.List;

import pe.com.lima.verde.punto.reciclaje.dao.entity.PuntoReciclajeEntity;
import pe.com.lima.verde.punto.reciclaje.expose.dto.PuntoReciclajeDto;

public interface PuntoCommonService {

	PuntoReciclajeDto getEntityToDto(PuntoReciclajeEntity source);

	List<PuntoReciclajeDto> getListArrayObjectToListPuntoDto(List<PuntoReciclajeEntity> puntoReciclajesEntity);
}
