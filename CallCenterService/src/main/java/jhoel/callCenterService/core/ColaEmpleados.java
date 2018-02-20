package jhoel.callCenterService.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.exception.CustomException;
import jhoel.callCenterService.util.Constantes;

/**
 * Clase que representa la cola de empleados
 * 
 * @author Jhoel
 *
 */
public class ColaEmpleados {

	private int prioridadLlamada; //indica la prioridad de empleados en la cola
	private Queue<Empleado> colaEmpleados = new LinkedList<Empleado>(); //cola de empleados

	/**
	 * Constructor minimo
	 * @param prioridadLlamada
	 */
	public ColaEmpleados(int prioridadLlamada) {
		super();
		this.prioridadLlamada = prioridadLlamada;
	}

	/**
	 * 
	 * @param listaEmpleados
	 * @return
	 * @throws CustomException
	 */
	public static List<ColaEmpleados> constuirListadeColaEmpleadosPorCargo(List<Empleado> listaEmpleados)
			throws CustomException {
		
		List<ColaEmpleados> colaEmpleadosPorCargo = (new ArrayList<ColaEmpleados>(Constantes.CANTIDAD_CARGOS));
		for (int i = 0; i < Constantes.CANTIDAD_CARGOS; i++) {
			ColaEmpleados colaEmpleados = new ColaEmpleados(i);
			colaEmpleadosPorCargo.add(colaEmpleados);
		}
		validarListadoEmpleados(listaEmpleados);
		for (Empleado empleado : listaEmpleados) {
			agregarEmpleadoDisponible(empleado, colaEmpleadosPorCargo);
		}
		return colaEmpleadosPorCargo;
	}
	
	/**
	 * Metodo que agrega empleados disponibles en la cola de empleados
	 * @param empleado
	 * @param colaEmpleadosPorCargo
	 */
	public static void agregarEmpleadoDisponible(Empleado empleado, List<ColaEmpleados> colaEmpleadosPorCargo){
		int prioridadEmpleado = empleado.getPrioridadLlamda();
		ColaEmpleados colaEmpleados = colaEmpleadosPorCargo.get(prioridadEmpleado);
		colaEmpleados.getColaEmpleados().add(empleado);
	}


	/**
	 * 
	 * @param listaEmpleados
	 * @throws CustomException
	 */
	private static void validarListadoEmpleados(List<Empleado> listaEmpleados) throws CustomException {
		if (listaEmpleados == null || listaEmpleados.isEmpty()) {
			throw new CustomException(CustomException.LISTA_VACIA);
		}
	}

	// GETTERS AND SETTERS

	public Queue<Empleado> getColaEmpleados() {
		return colaEmpleados;
	}

	public void setColaEmpleados(Queue<Empleado> colaEmpleados) {
		this.colaEmpleados = colaEmpleados;
	}

	public int getPrioridadLlamada() {
		return prioridadLlamada;
	}

	public void setPrioridadLlamada(int prioridadLlamada) {
		this.prioridadLlamada = prioridadLlamada;
	}

}
