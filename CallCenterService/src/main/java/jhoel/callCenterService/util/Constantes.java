package jhoel.callCenterService.util;

/**
 * Esta clase se encuentran todas las constantes genericas de la aplicaci&oacuten;
 * @author Jhoel
 *
 */
public class Constantes {

	public static final int DURACION_MINIMA = 5;
	public static final int DURACION_MAXIMA = 10;
	
	public static final int SEGUNDO_EN_MILISEGUNDOS = 1000;
	public static final int CANTIDAD_CARGOS = 3;

	/**
	 * 
	 * Enum de estados de la llamada
	 *
	 */
	public static enum ESTADOS_LLAMADA {
		EN_ESPERA(1, "En Espera"),
		ACTIVA(2, "Activa"),
		FINALIZADA(3, "Finalizada");

		private int estado;
		private String descripcion;

		private ESTADOS_LLAMADA(int estado, String descripcion) {
			this.estado = estado;
			this.descripcion = descripcion;
		}

		public int getEstado() {
			return estado;
		}

		public void setEstado(int estado) {
			this.estado = estado;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

	}

}
