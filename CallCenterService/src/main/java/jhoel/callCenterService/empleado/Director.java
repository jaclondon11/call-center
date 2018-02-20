package jhoel.callCenterService.empleado;

/**
 * Clase que representa la entidad Director
 * 
 * @author Jhoel
 *
 */
public class Director extends Empleado {
	
	private static final int PRIORIDAD_LLAMDA = 2;
	public static final String CARGO = "Director";

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

	public String getCargo() {
		return CARGO;
	}

}
