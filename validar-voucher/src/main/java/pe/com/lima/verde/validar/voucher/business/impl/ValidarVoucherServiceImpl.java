package pe.com.lima.verde.validar.voucher.business.impl;

import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.lima.verde.validar.voucher.business.ValidarVoucherService;
import pe.com.lima.verde.validar.voucher.dao.entity.VoucherEntity;
import pe.com.lima.verde.validar.voucher.dao.repository.VoucherRepository;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherRequestDto;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherRequestDto.VoucherDto;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherResponseDto;
import pe.gob.osce.escaparate.libutilirario.expose.dto.AuditoriaRequestDto;
import pe.gob.osce.escaparate.libutilirario.util.Constante;
import pe.gob.osce.escaparate.libutilirario.util.Util;

@Slf4j
@Service
public class ValidarVoucherServiceImpl implements ValidarVoucherService {
	

    @Value("${lima.verde.pagination.size}")
	private Integer pageSizeDefault;
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Override
	public PostValidarVoucherResponseDto validarVoucher(PostValidarVoucherRequestDto postValidarVoucherRequestDto) {
		AuditoriaRequestDto auditoriaRequestDto = Util
				.setParamAuditoriaRequestDtoInDto(postValidarVoucherRequestDto.getAuditoria());
		MDC.put(Constante.Auditoria.Parametro.ID_RASTREO, auditoriaRequestDto.getIdRastreo());
		postValidarVoucherRequestDto.setAuditoria(auditoriaRequestDto);
		log.info("Request:\n" + Util.toJson(postValidarVoucherRequestDto));
		Util.validateParamAuditoriaRequestDtoOfDto(auditoriaRequestDto);
		VoucherDto voucherDto=postValidarVoucherRequestDto.getRequest().getVoucher();
		String mensaje="";
		Optional<VoucherEntity> voucherEntity = this.voucherRepository.findByIdUsuario(voucherDto.getIdUsuario()).stream()
				//.map(p -> p.get)
				.filter(p -> p.getCodigo().equals(voucherDto.getCodigo())).findFirst();
				//
		
	    if (voucherEntity.get().getEstado().equals("1")) {
	    	mensaje="Voucher Usado";
        }else {
        	voucherEntity.get().setEstado("1");
        	this.voucherRepository.flush();
        	mensaje="Voucher Valido";
        }
		
		PostValidarVoucherResponseDto.Response response = new PostValidarVoucherResponseDto.Response();
		response.setMensaje("Voucher Valido");
		PostValidarVoucherResponseDto postValidarVoucherResponseDto = PostValidarVoucherResponseDto.builder()
				.auditoria(Util.getAuditoriaResponseDto(auditoriaRequestDto.getIdRastreo(), HttpStatus.OK.value(),
						HttpStatus.OK.name()))
				.response(response).build();

		log.info("Response: Voucher Validado \n" + Util.toJson(postValidarVoucherResponseDto));
		return postValidarVoucherResponseDto;
	}

}
