package jhoel.callCenterService.empleado;

/**
 * Clase que representa la entidad Supervisor
 * @author Jhoel
 *
 */
public class Supervisor extends Empleado {
	
	private static final int PRIORIDAD_LLAMDA = 1;

	/**
	 * Constructor vacio
	 */
	public Supervisor() {
		super(PRIORIDAD_LLAMDA);
	}

	/**
	 * Constructor completo
	 * 
	 * @param nombre
	 */
	public Supervisor(String nombre) {
		super(nombre, PRIORIDAD_LLAMDA);
	}

}
