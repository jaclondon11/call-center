package jhoel.callCenterService.core;

import java.util.ArrayList;
import java.util.List;

import jhoel.callCenterService.empleado.Director;
import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.empleado.Operador;
import jhoel.callCenterService.empleado.Supervisor;
import jhoel.callCenterService.exception.CustomException;

/**
 * 
 * @author Jhoel
 *
 */
public class Dispatcher {
	
	private List<ColaEmpleados> ListaDecolaEmpleadosPorCargo;

	/**
	 * 
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
	public synchronized void dispatchCall(Llamada llamada){
		Empleado empleado = null;
		for (ColaEmpleados colaEmpleados : ListaDecolaEmpleadosPorCargo) {
			empleado = colaEmpleados.getColaEmpleados().poll();
			if (empleado != null) {
				break;
			}
		}
		if(empleado == null){
			System.out.println("!------------- NO HAY EMPLEADOS DISPONIBLES ---------------!");
		}else{
//			TODO DORMIR HASTA TENER EMPLEDOS DISPONIBLES
		}
		empleado.setLlamadaActual(llamada);
		llamada.setReceptor(empleado);
		Thread t = new Thread(llamada);
	    t.start();
	}
	
	public void desocuparEmpleado(Empleado empleado){
		ColaEmpleados.agregarEmpleadoDisponible(empleado, ListaDecolaEmpleadosPorCargo);
	}
	
	
	public static void main(String[] args) throws CustomException {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados.add(new Supervisor("2"));
		listaEmpleados.add(new Director("3"));
		listaEmpleados.add(new Operador("1"));
		listaEmpleados.add(new Operador("1"));
		listaEmpleados.add(new Operador("1"));
		Dispatcher dispatcher = new Dispatcher(listaEmpleados);
		for (int i = 0; i < 30; i++) {
			dispatcher.dispatchCall(new Llamada(Integer.toString(i)));
		}
	}


}
