package pe.com.lima.verde.canjear.puntos.business.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.lima.verde.canjear.puntos.business.CanjeCommonService;
import pe.com.lima.verde.canjear.puntos.dao.entity.HistoCanjeEntity;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosRequestDto.HistoCanjeDto;

@Service
public class CanjeCommonServiceImpl implements CanjeCommonService {

	public HistoCanjeEntity getEntityToDto(HistoCanjeDto source) {
		HistoCanjeEntity target = new HistoCanjeEntity();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}
