package pe.com.lima.verde.canjear.puntos.business;

import pe.com.lima.verde.canjear.puntos.dao.entity.HistoCanjeEntity;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosRequestDto.HistoCanjeDto;

public interface CanjeCommonService {

	HistoCanjeEntity getEntityToDto(HistoCanjeDto source);

}
