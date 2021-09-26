package pe.com.lima.verde.canjear.puntos.business;

import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosRequestDto;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosResponseDto;

public interface CanjearPuntosService {

	PostCanjearPuntosResponseDto canjearPuntos(PostCanjearPuntosRequestDto postCanjearPuntosRequestDto);

}