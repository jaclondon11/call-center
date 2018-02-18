package jhoel.callCenterService.empleado;

/**
 * Clase que representa la entidad Director
 * @author Prueba
 *
 */
public class Director extends Empleado {
	
	private static final int PRIORIDAD_LLAMDA = 2;

	/**
	 * Constructor vacio
	 */
	public Director() {
		super(PRIORIDAD_LLAMDA);
	}

	/**
	 * 
	 * @param nombre
	 */
	public Director(String nombre) {
		super(nombre, PRIORIDAD_LLAMDA);
	}

}
