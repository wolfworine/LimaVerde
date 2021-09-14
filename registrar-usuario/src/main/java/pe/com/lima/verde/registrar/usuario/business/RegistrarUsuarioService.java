package pe.com.lima.verde.registrar.usuario.business;

import pe.com.lima.verde.registrar.usuario.expose.dto.GetLoginUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.GetLoginUsuarioResponseDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioResponseDto;

public interface RegistrarUsuarioService {

	PostRegistrarUsuarioResponseDto addUser(PostRegistrarUsuarioRequestDto postRegistrarUsuarioRequestDto);
	GetLoginUsuarioResponseDto getUser(GetLoginUsuarioRequestDto getLoginUsuarioRequestDto) ;
}