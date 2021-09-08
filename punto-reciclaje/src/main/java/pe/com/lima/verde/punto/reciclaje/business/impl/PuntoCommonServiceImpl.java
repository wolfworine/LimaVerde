package pe.com.lima.verde.punto.reciclaje.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.lima.verde.punto.reciclaje.business.PuntoCommonService;
import pe.com.lima.verde.punto.reciclaje.dao.entity.PuntoReciclajeEntity;
import pe.com.lima.verde.punto.reciclaje.expose.dto.PuntoReciclajeDto;

@Service
public class PuntoCommonServiceImpl implements PuntoCommonService {

	public PuntoReciclajeDto getEntityToDto(PuntoReciclajeEntity source) {
		PuntoReciclajeDto target = new PuntoReciclajeDto();
		BeanUtils.copyProperties(source, target);
		return target;
	}
	
	public List<PuntoReciclajeDto> getListArrayObjectToListPuntoDto(List<PuntoReciclajeEntity> listaSource) {
		    return listaSource.stream().parallel().map(r -> getEntityToDto(r))
		      .collect(Collectors.toList());
	}
	
}
