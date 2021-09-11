package pe.com.lima.verde.registrar.usuario.business;

import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioResponseDto;

public interface RegistrarUsuarioService {

	PostRegistrarUsuarioResponseDto addUser(PostRegistrarUsuarioRequestDto postRegistrarUsuarioRequestDto);

}