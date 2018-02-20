package jhoel.callCenterService.core;

import java.util.List;

import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.exception.CustomException;
import jhoel.callCenterService.util.Constantes;

/**
 * Esta clase se encarga de gestionar las llamadas que entr&aacute;n al sistema
 * @author Jhoel
 *
 */
public class Dispatcher {
	
	private List<ColaEmpleados> ListaDecolaEmpleadosPorCargo;
	private int contadorLlamadas = 1;

	/**
	 * Constructor minimo que recibe la lista de empleados que recibir&aacute;n las llamadas
	 * @param listaEmpleados
	 * @throws CustomException 
	 */
	public Dispatcher(List<Empleado> listaEmpleados) throws CustomException {
		try {
			ListaDecolaEmpleadosPorCargo = ColaEmpleados.constuirListadeColaEmpleadosPorCargo(listaEmpleados);
		} catch (CustomException e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Metodo que despacha la llamada
	 * @param llamada
	 */
	public void dispatchCall(Llamada llamada){
		Empleado empleado = obtenerEmpleadoConMenorCargoDisponible();
		if(empleado == null){
			System.out.println("!------------- NO HAY EMPLEADOS DISPONIBLES, ESPERANDO HASTA QUE ALGUN EMPLEADO ESTE LIBRE ---------------!");
			empleado = esperarEmpleadoDisponible();
		}
		llamada.asignarLlamada(empleado, this, contadorLlamadas++);
	}

	/**
	 * Este metodo obtiene de la cola de empleados por cargo el empleado con el cargo mas bajo disponible
	 * @return
	 */
	private Empleado obtenerEmpleadoConMenorCargoDisponible() {
		Empleado empleado = null;
			for (ColaEmpleados colaEmpleados : ListaDecolaEmpleadosPorCargo) {
				empleado = colaEmpleados.getColaEmpleados().poll();
				if (empleado != null) {
					break;
				}
			}
		return empleado;
	}
	
	/**
	 * Este metodo duerme el hilo principal de ejecuci&oacute;n hasta que un empleado se desocupe de una llamada
	 * @return
	 */
	private Empleado esperarEmpleadoDisponible() {
		Empleado empleado = null;
		while (empleado == null){
			try {
				Thread.sleep(Constantes.SEGUNDO_EN_MILISEGUNDOS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			empleado = obtenerEmpleadoConMenorCargoDisponible();
		}
		return empleado;
	}
	
	/**
	 * Este metodo descupa un empleado cuando este termina una llamada
	 * @param empleado
	 */
	public void desocuparEmpleado(Empleado empleado){
		empleado.setLlamadaActual(null);
		ColaEmpleados.agregarEmpleadoDisponible(empleado, ListaDecolaEmpleadosPorCargo);
	}
	
}
