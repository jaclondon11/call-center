package jhoel.callCenterService.empleado;

/**
 * Clase que representa la entidad de empleado
 * 
 * @author Jhoel
 *
 */
public abstract class Empleado {

	private String nombre;

	/**
	 * Constructor vacio
	 */
	public Empleado() {
		super();
	}

	/**
	 * Constructor completo
	 * @param nombre
	 */
	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
