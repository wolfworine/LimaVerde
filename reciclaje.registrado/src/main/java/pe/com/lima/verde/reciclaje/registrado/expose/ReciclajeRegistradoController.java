package pe.com.lima.verde.reciclaje.registrado.expose;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.lima.verde.reciclaje.registrado.business.ReciclajeRegistradoService;
import pe.com.lima.verde.reciclaje.registrado.expose.dto.GetReciclajeRegistradoDto;

@RestController
@RequestMapping("/ReciclajeRegistrado/v1")
public class ReciclajeRegistradoController {
	@Autowired
	private ReciclajeRegistradoService reciclajeRegistradoService;
	
	@GetMapping(value = "/reciclaje/registrado/{idUsuario}", produces = { "application/json" })
	public ResponseEntity<GetReciclajeRegistradoDto> getHistorialReciclaje(
			@PathVariable(name = "idUsuario", required = true) Long idUsuario, @RequestParam Map<String, String> map) {
		return ResponseEntity.ok().body(this.reciclajeRegistradoService.getHistorialReciclaje(idUsuario, map));
	}
}
