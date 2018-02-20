package jhoel.callCenterService.empleado;

import jhoel.callCenterService.core.Llamada;

/**
 * Clase que representa la entidad de empleado
 * 
 * @author Jhoel
 *
 */
public abstract class Empleado {

	private String nombre;
	private int prioridadLlamda;
	private Llamada llamadaActual;

	/**
	 * Constructor minimo
	 * 
	 * @param prioridadLlamda
	 */
	public Empleado(int prioridadLlamda) {
		super();
		this.prioridadLlamda = prioridadLlamda;
	}
	
	public abstract String getCargo();

	/**
	 * Constructor Completo
	 * 
	 * @param nombre
	 * @param prioridadLlamda
	 */
	public Empleado(String nombre, int prioridadLlamda) {
		super();
		this.nombre = nombre;
		this.prioridadLlamda = prioridadLlamda;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getPrioridadLlamda() {
		return prioridadLlamda;
	}

	public Llamada getLlamadaActual() {
		return llamadaActual;
	}

	public void setLlamadaActual(Llamada llamadaActual) {
		this.llamadaActual = llamadaActual;
	}
	
}
