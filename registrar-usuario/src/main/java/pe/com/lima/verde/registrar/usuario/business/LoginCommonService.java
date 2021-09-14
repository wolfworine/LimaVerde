package pe.com.lima.verde.registrar.usuario.business;

import pe.com.lima.verde.registrar.usuario.dao.entity.UsuarioEntity;
import pe.com.lima.verde.registrar.usuario.expose.dto.UsuarioDto;

public interface LoginCommonService {

	public UsuarioDto getEntityToDto(UsuarioEntity source);
}
