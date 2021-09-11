package pe.com.lima.verde.registrar.usuario.expose.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto implements Serializable {
	private static final long serialVersionUID = -7172401280896850668L;
	private Long id;
	private String username;
	private String password;
	private String nombres;
	private String apellidos;
	private String documento;
	private String email;
	private Long celular;
	private String genero;
}
