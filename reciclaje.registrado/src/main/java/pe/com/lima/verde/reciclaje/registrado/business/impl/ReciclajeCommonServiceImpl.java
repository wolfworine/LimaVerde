package pe.com.lima.verde.reciclaje.registrado.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.lima.verde.reciclaje.registrado.business.ReciclajeCommonService;
import pe.com.lima.verde.reciclaje.registrado.dao.entity.PuntosReciclajeEntity;
import pe.com.lima.verde.reciclaje.registrado.dao.entity.ReciclajeRegistradoEntity;
import pe.com.lima.verde.reciclaje.registrado.dao.repository.HistorialReciclajeRepository;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto.ReciclajeHistorialDto;


@Service
public class ReciclajeCommonServiceImpl implements ReciclajeCommonService {

	@Autowired
	private HistorialReciclajeRepository historialReciclajeRepository;
	
	public List<ReciclajeHistorialDto>  getObjectToHistorialReciclajeDto(List<ReciclajeRegistradoEntity> listaSource) {
	    return listaSource.stream().parallel().map(r -> getEntityToDto(r))
			      .collect(Collectors.toList());
	}

	public ReciclajeHistorialDto getEntityToDto(ReciclajeRegistradoEntity source) {
		ReciclajeHistorialDto target = new ReciclajeHistorialDto();
		target.setFechaRegistro(source.getFechaRegistro());
		target.setPuntosAcumulados(source.getPuntosAcumulados());
		if(source.getIdPuntoReciclaje()!=null) {
			PuntosReciclajeEntity puntosReciclajeEntity=this.historialReciclajeRepository.findById(source.getIdPuntoReciclaje())
					.orElse(new PuntosReciclajeEntity());
			target.setIdPuntoReciclaje(puntosReciclajeEntity.getIdPuntoReciclaje());
			target.setNombre(puntosReciclajeEntity.getNombre());
		}
		
		return target;
	}
	
}
