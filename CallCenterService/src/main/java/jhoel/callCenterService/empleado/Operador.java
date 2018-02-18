package jhoel.callCenterService.empleado;

/**
 * Clase que representa la entidad Operador
 * @author Jhoel
 *
 */
public class Operador extends Empleado {
	
	private static final int PRIORIDAD_LLAMDA = 0;

	/**
	 * Constructor vacio
	 */
	public Operador() {
		super(PRIORIDAD_LLAMDA);
	}

	/**
	 * Constructor completo
	 * @param nombre
	 */
	public Operador(String nombre) {
		super(nombre, PRIORIDAD_LLAMDA);
	}

}
