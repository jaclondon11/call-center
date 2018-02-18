package jhoel.callCenterService.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.exception.CustomException;

/**
 * Clase que representa la cola de empleados
 * 
 * @author Jhoel
 *
 */
public class ColaEmpleados {

	private int prioridadLlamada;
	private Queue<Empleado> colaEmpleados = new LinkedList<Empleado>();

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
		
		List<ColaEmpleados> colaEmpleadosPorCargo = new ArrayList<ColaEmpleados>(3);
		ColaEmpleados colaEmpleados = new ColaEmpleados(0);
		colaEmpleadosPorCargo.add(colaEmpleados);
		colaEmpleados = new ColaEmpleados(1);
		colaEmpleadosPorCargo.add(colaEmpleados);
		colaEmpleados = new ColaEmpleados(2);
		colaEmpleadosPorCargo.add(colaEmpleados);
		
		
		validarListadoEmpleados(listaEmpleados);
		for (Empleado empleado : listaEmpleados) {
			agregarEmpleadoDisponible(empleado, colaEmpleadosPorCargo);
		}
		return colaEmpleadosPorCargo;
	}
	
	public static void agregarEmpleadoDisponible(Empleado empleado, List<ColaEmpleados> colaEmpleadosPorCargo){
		int prioridadEmpleado = empleado.getPrioridadLlamda();
//		ColaEmpleados colaEmpleados = obtenerColaEmpleadosPorPrioridad(colaEmpleadosPorCargo, prioridadEmpleado);
		ColaEmpleados colaEmpleados = colaEmpleadosPorCargo.get(prioridadEmpleado);
		colaEmpleados.getColaEmpleados().add(empleado);
	}

	/**
	 * 
	 * @param colaEmpleadosPorCargo
	 * @param prioridadEmpleado
	 * @return
	 */
	private static ColaEmpleados obtenerColaEmpleadosPorPrioridad(List<ColaEmpleados> colaEmpleadosPorCargo,
			int prioridadEmpleado) {
		ColaEmpleados colaEmpleados;
		try {
			colaEmpleados = colaEmpleadosPorCargo.get(prioridadEmpleado);
		} catch (IndexOutOfBoundsException e) {
			colaEmpleados = new ColaEmpleados(prioridadEmpleado);
		}
		colaEmpleadosPorCargo.add(prioridadEmpleado, colaEmpleados);
		return colaEmpleados;
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
