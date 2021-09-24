package pe.com.lima.verde.validar.voucher.business;

import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherRequestDto;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherResponseDto;

public interface ValidarVoucherService {

	PostValidarVoucherResponseDto validarVoucher(PostValidarVoucherRequestDto postValidarVoucherRequestDto);

}