package pe.gob.osce.escaparate.libutilirario.util;

public class Constante {
	private Constante() {
	}
	public static final String VACIO = "";
	public static final String ESPACIO = " ";
	public static final String COMA = ",";
	public static final String TAB = "\t";
	public static final String BARRA = "/";
	public static final Integer CERO = 0;
	public static final Integer UNO = 1;
	public static final String OK = "OK";
	public static class Auditoria {
		private Auditoria() {
		}
		public static class Parametro {
			private Parametro() {
			}
			public static final String ID_RASTREO = "idRastreo";
			public static final String CANAL = "canal";
			public static final String USUARIO = "usuario";
			public static final String FECHA = "fecha";
		}
	}
	public static class Paginable {
		private Paginable() {
		}
		public static class Parametro {
			private Parametro() {
			}
			public static final String PAGE = "page";
			public static final String PAGE_SIZE = "pageSize";
		}
	}
	
	public static class Tabla {
		private Tabla() {
		}
		public static class Parametro {
			private Parametro() {
			}
			public static class Type {
				private Type() {
				}
				public static final String LISTA_CARGO = "LISTA_CARGO";
				public static final String LISTA_DISPONIBLE = "LISTA_DISPONIBLE";
				public static final String LISTA_TIEMPO_EN_RUBRO = "LISTA_TIEMPO_EN_RUBRO";
			}
		}
	}
}
