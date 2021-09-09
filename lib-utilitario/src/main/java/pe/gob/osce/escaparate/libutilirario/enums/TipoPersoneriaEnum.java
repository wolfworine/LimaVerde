package pe.gob.osce.escaparate.libutilirario.enums;

import lombok.Getter;

public enum TipoPersoneriaEnum {
	PERSONAL(1, "Personal"),
	JURIDICA(2, "Juridica");
	
	@Getter
	private Integer code;
	@Getter
	private String desc;
	
	TipoPersoneriaEnum(Integer code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDescripcion(Integer code) {
		String value = null;
		if (code.equals(TipoPersoneriaEnum.PERSONAL.code))
			value = TipoPersoneriaEnum.PERSONAL.desc;
		else if (code.equals(TipoPersoneriaEnum.JURIDICA.code))
			value = TipoPersoneriaEnum.JURIDICA.desc;
		
		return value;
	}
}
