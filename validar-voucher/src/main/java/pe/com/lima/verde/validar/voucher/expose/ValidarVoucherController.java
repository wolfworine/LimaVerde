package pe.com.lima.verde.validar.voucher.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.com.lima.verde.validar.voucher.business.ValidarVoucherService;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherRequestDto;
import pe.com.lima.verde.validar.voucher.expose.dto.PostValidarVoucherResponseDto;

@Tag(name = "ValidarVoucher Controller", description = "Recurso de lista de puntos de Reciclaje")
@RestController
@RequestMapping("/ValidarVoucher/v1")
@CrossOrigin(origins ="${lima.verde.client.url}", methods = { RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE })
public class ValidarVoucherController {
  
	@Autowired
	private ValidarVoucherService validarVoucherService;
	
	@PostMapping(value = "/validar/voucher", produces = { "application/json" })
	public ResponseEntity<PostValidarVoucherResponseDto> validarVoucher(@RequestBody PostValidarVoucherRequestDto postValidarVoucherRequestDto) {
		return ResponseEntity.ok().body(this.validarVoucherService.validarVoucher(postValidarVoucherRequestDto));
	}
}