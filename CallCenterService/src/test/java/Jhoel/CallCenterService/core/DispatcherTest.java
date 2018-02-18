package Jhoel.CallCenterService.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import Jhoel.CallCenterService.core.util.Monitor;
import jhoel.callCenterService.core.Dispatcher;
import jhoel.callCenterService.core.Llamada;
import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.empleado.Operador;
import jhoel.callCenterService.exception.CustomException;
import jhoel.callCenterService.util.ColorPrinter;
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
			listaEmpleados.add(new Operador());
			dispatcher = new Dispatcher(listaEmpleados);
			for (int i = 0; i < 10; i++) {
				Llamada llamada = new Llamada(Integer.toString(i));
				llamadas.add(llamada);
				dispatcher.dispatchCall(llamada);
			}
			final Waiter waiter = new Waiter();
			waiter.await(Constantes.DURACION_MAXIMA * 1000);
//			waiter.resume();
//			waiter.assertTrue(Monitor.finalizaronTodasLasLlamadas(llamadas));
		} catch (TimeoutException e) {
			assertTrue(Monitor.finalizaronTodasLasLlamadas(llamadas));
		}
	}
	
	public void monitorearLlamadas(List<Llamada> llamadas) {
		Thread t = new Thread(new Monitor(llamadas));
	    t.start();
	}
	
	
}
