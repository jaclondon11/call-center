package Jhoel.CallCenterService.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import jhoel.callCenterService.core.Dispatcher;
import jhoel.callCenterService.core.Llamada;
import jhoel.callCenterService.empleado.Director;
import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.empleado.Operador;
import jhoel.callCenterService.empleado.Supervisor;
import jhoel.callCenterService.exception.CustomException;
import jhoel.callCenterService.util.Constantes;
import net.jodah.concurrentunit.Waiter;

public class DispatcherTest{

	private Dispatcher dispatcher;

	@Test
	public void validarEmpleadosNulos() {
		try {
			dispatcher = new Dispatcher(null);
		} catch (Exception e) {
			assertEquals(CustomException.LISTA_VACIA, e.getMessage());
		}
	}
	
	@Test
	public void validarEmpleadosVacia() {
		try {
			List<Empleado> listaEmpleados = new ArrayList<Empleado>();
			dispatcher = new Dispatcher(listaEmpleados);
		} catch (Exception e) {
			assertEquals(CustomException.LISTA_VACIA, e.getMessage());
		}
	}
	
	@Test
	public void despacharLlamada() throws Throwable{
		List<Llamada> llamadas = new ArrayList<Llamada>();
		try {
			List<Empleado> listaEmpleados = new ArrayList<Empleado>();
			listaEmpleados.add(new Supervisor("2"));
			listaEmpleados.add(new Director("3"));
			listaEmpleados.add(new Operador("1"));
			listaEmpleados.add(new Operador("1"));
			listaEmpleados.add(new Operador("1"));
			dispatcher = new Dispatcher(listaEmpleados);
			for (int i = 0; i < 5; i++) {
				Llamada llamada = new Llamada(Integer.toString(i));
				llamadas.add(llamada);
				dispatcher.dispatchCall(llamada);
			}
			final Waiter waiter = new Waiter();
			waiter.await((Constantes.DURACION_MAXIMA + 1) * 1000);
		} catch (TimeoutException e) {
			assertTrue(finalizaronTodasLasLlamadas(llamadas));
		}
	}
	
	public static boolean finalizaronTodasLasLlamadas(List<Llamada> llamadas) {
		for (Llamada llamada : llamadas) {
			if (llamada.getEstado() != Constantes.ESTADOS_LLAMADA.FINALIZADA.getEstado()) {
				return false;
			}
		}
		return true;
	}
	
	
}
