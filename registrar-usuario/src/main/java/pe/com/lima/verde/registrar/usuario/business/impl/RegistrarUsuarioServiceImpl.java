package pe.com.lima.verde.registrar.usuario.business.impl;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.registrar.usuario.business.RegistrarUsuarioService;
import pe.com.lima.verde.registrar.usuario.dao.entity.UsuarioEntity;
import pe.com.lima.verde.registrar.usuario.dao.repository.UsuarioRepository;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioResponseDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.UsuarioDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class RegistrarUsuarioServiceImpl implements RegistrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	public PostRegistrarUsuarioResponseDto addUser(PostRegistrarUsuarioRequestDto postRegistrarUsuarioRequestDto) {
		AuditoriaRequestDto auditoriaRequestDto = Util.setParamAuditoriaRequestDtoInDto(postRegistrarUsuarioRequestDto.getAuditoria());
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		postRegistrarUsuarioRequestDto.setAuditoria(auditoriaRequestDto);
		log.info("Request:\n" + Util.toJson(postRegistrarUsuarioRequestDto));
		Util.validateParamAuditoriaRequestDtoOfDto(auditoriaRequestDto);
		
		UsuarioDto usuarioDto = postRegistrarUsuarioRequestDto.getRequest().getUsuario();
	    UsuarioEntity usuarioEntity= UsuarioEntity.builder().username(usuarioDto.getUsername())
	    		.password(this.passwordEncoder.encode(usuarioDto.getPassword()))
	    		.nombres(usuarioDto.getNombres()).apellidos(usuarioDto.getApellidos()).documento(usuarioDto.getDocumento()).email(usuarioDto.getEmail())
	    		.celular(usuarioDto.getCelular()).genero(usuarioDto.getGenero()).build();
	    Long idUsuario= (this.usuarioRepository.save(usuarioEntity)).getId();
	    PostRegistrarUsuarioResponseDto.Response response = new PostRegistrarUsuarioResponseDto.Response();
		response.setIdUsuario(idUsuario);
		
		log.info("idUsuario: " + idUsuario);
		return PostRegistrarUsuarioResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.CREATED.value(),
						HttpStatus.CREATED.name()))
				.response(response).build();
	}

}
