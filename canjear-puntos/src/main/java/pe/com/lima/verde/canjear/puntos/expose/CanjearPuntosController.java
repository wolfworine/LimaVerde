package pe.com.lima.verde.canjear.puntos.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.com.lima.verde.canjear.puntos.business.CanjearPuntosService;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosRequestDto;
import pe.com.lima.verde.canjear.puntos.expose.dto.PostCanjearPuntosResponseDto;

@Tag(name = "CanjearPuntos Controller", description = "Recurso de lista de puntos de Reciclaje")
@RestController
@RequestMapping("/CanjearPuntos/v1")
@CrossOrigin(origins ="${lima.verde.client.url}", methods = { RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE })
public class CanjearPuntosController {
  
	@Autowired
	private CanjearPuntosService canjearPuntosService;
	
	@PostMapping(value = "/canjear/puntos", produces = { "application/json" })
	public ResponseEntity<PostCanjearPuntosResponseDto> canjearPuntos(@RequestBody PostCanjearPuntosRequestDto postCanjearPuntosRequestDto) {
		return ResponseEntity.ok().body(this.canjearPuntosService.canjearPuntos(postCanjearPuntosRequestDto));
	}
}