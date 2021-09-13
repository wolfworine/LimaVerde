package pe.com.lima.verde.punto.reciclaje.expose;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.com.lima.verde.punto.reciclaje.business.PuntoReciclajeService;
import pe.com.lima.verde.punto.reciclaje.expose.dto.GetListPuntoReciclajeResponseDto;
import pe.com.lima.verde.punto.reciclaje.expose.dto.GetPuntoReciclajeResponseDto;

@Tag(name = "PuntoReciclaje Controller", description = "Recurso de lista de puntos de Reciclaje")
@RestController
@RequestMapping("/PuntoReciclaje/v1")
@CrossOrigin(origins ="${lima.verde.client.url}", methods = { RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE })
public class PuntoReciclajeController {
  
	@Autowired
	private PuntoReciclajeService puntoReciclajeService;
	
	@GetMapping(value = "/puntos/reciclaje", produces = { "application/json" })
	public ResponseEntity<GetListPuntoReciclajeResponseDto> getList( @RequestParam Map<String, String> map) {
		return ResponseEntity.ok().body(this.puntoReciclajeService.getList(map));
	}
	
	@GetMapping(value = "/puntos/reciclaje/{idPunto}", produces = { "application/json" })
	public ResponseEntity<GetPuntoReciclajeResponseDto> getPunto(
			@PathVariable(name = "idPunto", required = true) Long idPunto, @RequestParam Map<String, String> map) {
		return ResponseEntity.ok().body(this.puntoReciclajeService.getPunto(idPunto, map));
	}
}