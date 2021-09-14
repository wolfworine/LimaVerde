package pe.com.lima.verde.registrar.usuario.business.impl;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.registrar.usuario.business.LoginCommonService;
import pe.com.lima.verde.registrar.usuario.business.RegistrarUsuarioService;
import pe.com.lima.verde.registrar.usuario.dao.entity.UsuarioEntity;
import pe.com.lima.verde.registrar.usuario.dao.repository.UsuarioRepository;
import pe.com.lima.verde.registrar.usuario.expose.dto.GetLoginUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.GetLoginUsuarioResponseDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioRequestDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.PostRegistrarUsuarioResponseDto;
import pe.com.lima.verde.registrar.usuario.expose.dto.UsuarioDto;
import pe.gob.osce.escaparate.libutilirario.exception.NotFoundException;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class RegistrarUsuarioServiceImpl implements RegistrarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    
	@Autowired
	private LoginCommonService loginCommonService;

    public PostRegistrarUsuarioResponseDto addUser(PostRegistrarUsuarioRequestDto postRegistrarUsuarioRequestDto) {
		AuditoriaRequestDto auditoriaRequestDto = Util.setParamAuditoriaRequestDtoInDto(postRegistrarUsuarioRequestDto.getAuditoria());
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		postRegistrarUsuarioRequestDto.setAuditoria(auditoriaRequestDto);
		log.info("Request:\n" + Util.toJson(postRegistrarUsuarioRequestDto));
		Util.validateParamAuditoriaRequestDtoOfDto(auditoriaRequestDto);
		
		UsuarioDto usuarioDto = postRegistrarUsuarioRequestDto.getRequest().getUsuario();
		 boolean userExists = usuarioRepository
	                .findByUsername(usuarioDto.getUsername())
	                .isPresent();
		
	    if (userExists) {
	            throw new IllegalStateException("username already taken");
	    }

	    UsuarioEntity usuarioEntity= UsuarioEntity.builder().username(usuarioDto.getUsername())
	    		.password(bCryptPasswordEncoder.encode(usuarioDto.getPassword()))
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
	
    public GetLoginUsuarioResponseDto getUser(GetLoginUsuarioRequestDto getLoginUsuarioRequestDto) {
		AuditoriaRequestDto auditoriaRequestDto = Util.setParamAuditoriaRequestDtoInDto(getLoginUsuarioRequestDto.getAuditoria());
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		getLoginUsuarioRequestDto.setAuditoria(auditoriaRequestDto);
		log.info("Request:\n" + Util.toJson(getLoginUsuarioRequestDto));
		Util.validateParamAuditoriaRequestDtoOfDto(auditoriaRequestDto);
		
		UsuarioDto usuarioDto = getLoginUsuarioRequestDto.getRequest().getUsuario();
		String password=usuarioDto.getPassword();

		
		UsuarioEntity usuarioEntity= this.usuarioRepository.findByUsername(usuarioDto.getUsername())
				.filter(p -> bCryptPasswordEncoder.matches(password, p.getPassword()))
	    		.orElseThrow(() -> new NotFoundException(auditoriaRequestDto.getIdRastreo()));
	    
	    usuarioDto= this.loginCommonService.getEntityToDto(usuarioEntity);
	    GetLoginUsuarioResponseDto.Response response = new GetLoginUsuarioResponseDto.Response();
		response.setUsuario(usuarioDto);
		
		log.info("Response:\n" + Util.toJson(usuarioDto));
		return GetLoginUsuarioResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();
	}

}
