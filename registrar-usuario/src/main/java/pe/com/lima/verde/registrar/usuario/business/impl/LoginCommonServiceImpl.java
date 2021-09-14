package pe.com.lima.verde.registrar.usuario.business.impl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pe.com.lima.verde.registrar.usuario.business.LoginCommonService;
import pe.com.lima.verde.registrar.usuario.dao.entity.UsuarioEntity;
import pe.com.lima.verde.registrar.usuario.expose.dto.UsuarioDto;

@Service
public class LoginCommonServiceImpl implements LoginCommonService{

	@Override
	public UsuarioDto getEntityToDto(UsuarioEntity source) {
		UsuarioDto target = new UsuarioDto();
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
