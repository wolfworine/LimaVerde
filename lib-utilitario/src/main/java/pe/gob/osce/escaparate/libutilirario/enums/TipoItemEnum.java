package pe.gob.osce.escaparate.libutilirario.enums;

import lombok.Getter;

public enum TipoItemEnum {
	BIEN(1, "BIENES"),
	SERVICIO(2, "SERVICIOS"),
	EJECUTOR_OBRA(3, "EJECUTOR DE OBRA"),
	CONSULTOR_OBRA(4, "CONSULTOR DE OBRA");
	
	@Getter
	private Integer code;
	@Getter
	private String desc;
	
	TipoItemEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
}
