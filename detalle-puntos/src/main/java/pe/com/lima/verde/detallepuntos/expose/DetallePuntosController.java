package pe.com.lima.verde.detallepuntos.expose;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.com.lima.verde.detallepuntos.business.DetallePuntosService;
import pe.com.lima.verde.detallepuntos.expose.dto.GetDetallePuntosResponseDto;
import pe.com.lima.verde.detallepuntos.expose.dto.GetMiReciclajeResponseDto;

@Tag(name = "Detalle Puntos Controller", description = "Recurso de Detalle Puntos")
@RestController
@RequestMapping("/DetallePuntos/v1")
public class DetallePuntosController {
  
	@Autowired
	private DetallePuntosService detallePuntosService;
	
	@GetMapping(value = "/detalle/puntos", produces = { "application/json" })
	public ResponseEntity<GetDetallePuntosResponseDto> getDetallePuntos(
			@RequestParam(name = "idUsuario", required = true) Integer idUsuario, @RequestParam Map<String, String> map) {
		return ResponseEntity.ok().body(this.detallePuntosService.getDetallePuntos(idUsuario, map));
	}
	
	@GetMapping(value = "/mi/reciclaje", produces = { "application/json" })
	public ResponseEntity<GetMiReciclajeResponseDto> getMiReciclaje(
			@RequestParam(name = "idUsuario", required = true) Integer idUsuario, @RequestParam Map<String, String> map) {
		return ResponseEntity.ok().body(this.detallePuntosService.getMiReciclaje(idUsuario, map));
	}
	
}