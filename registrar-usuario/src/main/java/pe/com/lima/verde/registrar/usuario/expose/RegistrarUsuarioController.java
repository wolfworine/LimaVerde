package pe.com.lima.verde.registrar.usuario.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.com.lima.verde.registrar.usuario.business.RegistrarUsuarioService;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioResponseDto;
import pe.gob.osce.escaparate.libutilirario.exception.ApiError;

@RestController
@RequestMapping("/RegistrarUsuario/v1")
public class RegistrarUsuarioController {
  
	@Autowired
	private RegistrarUsuarioService registrarUsuarioService;
	
	@PostMapping(value = "/registrar/usuario",consumes = { "application/json" }, produces = { "application/json" })
	@Operation(description = "Retorna un json con informacion del id del usuario agregado.", summary = "json con informacion del id del usuario registrado en base de datos.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(schema = @Schema(implementation = PostRegistrarUsuarioResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiError.class), examples = @ExampleObject(name = "Ejemplos de un error 400", value = "{\n    \"auditoria\": {\n        \"idRastreo\": \"1dae7a20-4474-4b15-84f7-0ec79eceb7f6\",\n        \"codigo\": 400,\n        \"mensaje\": \"BAD_REQUEST\"\n    }\n}"))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiError.class), examples = @ExampleObject(name = "Ejemplos de un error 500", value = "{\n    \"auditoria\": {\n        \"idRastreo\": \"1dae7a20-4474-4b15-84f7-0ec79eceb7f6\",\n        \"codigo\": 500,\n        \"mensaje\": \"Internal Server Error\"\n    }\n}"))) })
	@Tag(name = "Usuario", description = "Recurso de Usuario")
	public ResponseEntity<PostRegistrarUsuarioResponseDto> addUser( @RequestBody PostRegistrarUsuarioRequestDto postRegistrarUsuarioRequestDto) {
		return ResponseEntity.ok().body(this.registrarUsuarioService.addUser(postRegistrarUsuarioRequestDto));
	}

}