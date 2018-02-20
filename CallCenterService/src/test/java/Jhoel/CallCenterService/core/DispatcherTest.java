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

/**
 * 
 * @author Jhoel
 *
 */
public class DispatcherTest{
	
	private static final int TIMEOUT = 1;
	
	private Dispatcher dispatcher;

	/**
	 * Test de empleados nulos
	 */
	@Test
	public void validarEmpleadosNulos() {
		try {
			dispatcher = new Dispatcher(null);
		} catch (Exception e) {
			assertEquals(CustomException.LISTA_VACIA, e.getMessage());
		}
	}
	
	/**
	 * Test de lista vacia de empleados
	 */
	@Test
	public void validarEmpleadosVacia() {
		try {
			List<Empleado> listaEmpleados = new ArrayList<Empleado>();
			dispatcher = new Dispatcher(listaEmpleados);
		} catch (Exception e) {
			assertEquals(CustomException.LISTA_VACIA, e.getMessage());
		}
	}
	
	/**
	 * Test de 10 llamadas concurrentes: En este Testse utiliza la clase Waiter
	 * para poder testear hilos concurrentes, ya que junit termina la ejecución
	 * del hilo principal al terminar el metodo @Test; Por esta razón con ayuda
	 * de la clase Waiter podemos detener el hilo de Junit durante el tiempo que
	 * queramos para poder comprobar el caso de prueba; Se realiza un assert con
	 * cada llamada para identificar que las llamadas fueron asignadas en el
	 * orden que entraron en el dispatcher por el empleado con el menor cargo
	 * 
	 * @throws Throwable
	 */
	@Test
	public void despacharDiezLlamadas() throws Throwable{//10 llamadas
		System.out.println("****** TEST despacharDiezLlamadas **********");
		List<Llamada> llamadas = new ArrayList<Llamada>();
		try {
			List<Empleado> listaEmpleados = new ArrayList<Empleado>();
			listaEmpleados.add(new Supervisor("a")); 	//1
			listaEmpleados.add(new Director("b")); 		//2 
			listaEmpleados.add(new Operador("c")); 		//3
			listaEmpleados.add(new Operador("d")); 		//4
			listaEmpleados.add(new Operador("e")); 		//5
			listaEmpleados.add(new Supervisor("f"));	//6	
			listaEmpleados.add(new Operador("g"));		//7
			listaEmpleados.add(new Operador("h"));		//8
			listaEmpleados.add(new Operador("i"));		//9
			listaEmpleados.add(new Operador("j"));		//10
			dispatcher = new Dispatcher(listaEmpleados);
			for (int i = 0; i < 10; i++) {
				Llamada llamada = new Llamada();
				llamadas.add(llamada);
				dispatcher.dispatchCall(llamada);
			}
			final Waiter waiter = new Waiter();
			waiter.await((Constantes.DURACION_MAXIMA + TIMEOUT) * Constantes.SEGUNDO_EN_MILISEGUNDOS);
		} catch (TimeoutException e) {
			assertTrue(finalizaronTodasLasLlamadas(llamadas));
			assertTrue(llamadas.get(0).getReceptor().getCargo().equals(Operador.CARGO));	//1
			assertTrue(llamadas.get(1).getReceptor().getCargo().equals(Operador.CARGO));    //2
			assertTrue(llamadas.get(2).getReceptor().getCargo().equals(Operador.CARGO));    //3
			assertTrue(llamadas.get(3).getReceptor().getCargo().equals(Operador.CARGO));  	//4
			assertTrue(llamadas.get(4).getReceptor().getCargo().equals(Operador.CARGO));    //5
			assertTrue(llamadas.get(5).getReceptor().getCargo().equals(Operador.CARGO));    //6
			assertTrue(llamadas.get(6).getReceptor().getCargo().equals(Operador.CARGO));    //7
			assertTrue(llamadas.get(7).getReceptor().getCargo().equals(Supervisor.CARGO));  //8
			assertTrue(llamadas.get(8).getReceptor().getCargo().equals(Supervisor.CARGO));  //9
			assertTrue(llamadas.get(9).getReceptor().getCargo().equals(Director.CARGO));    //10
		}
	}
	
	/**
	 * Metodo que valida que todas las llamadas esten finalizadas
	 * @param llamadas
	 * @return
	 */
	private boolean finalizaronTodasLasLlamadas(List<Llamada> llamadas) {
		for (Llamada llamada : llamadas) {
			if (llamada.getEstado() != Constantes.ESTADOS_LLAMADA.FINALIZADA.getEstado()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Test de mas de 10 llamadas concurrentes, para la solucion de mas de 10 llamadas
	 * @throws Throwable
	 */
	@Test
	public void despacharMasDeDiezLlamadas() throws Throwable { // 10+ llamadas
		System.out.println("****** TEST despacharMasDeDiezLlamadas **********");
		List<Llamada> llamadas = new ArrayList<Llamada>();
		final Waiter waiter = new Waiter();
		try {
			List<Empleado> listaEmpleados = new ArrayList<Empleado>();
			listaEmpleados.add(new Supervisor("a")); 	//1
			listaEmpleados.add(new Director("b")); 		//2 
			listaEmpleados.add(new Operador("c")); 		//3
			listaEmpleados.add(new Operador("d")); 		//4
			listaEmpleados.add(new Operador("e")); 		//5
			listaEmpleados.add(new Supervisor("f"));	//6	
			listaEmpleados.add(new Operador("g"));		//7
			listaEmpleados.add(new Operador("h"));		//8
			listaEmpleados.add(new Operador("i"));		//9
			listaEmpleados.add(new Operador("j"));		//10
			dispatcher = new Dispatcher(listaEmpleados);
			for (int i = 0; i < 20; i++) {
				Llamada llamada = new Llamada();
				llamadas.add(llamada);
				dispatcher.dispatchCall(llamada);
			}
			waiter.await(((Constantes.DURACION_MAXIMA * 2) + TIMEOUT) * Constantes.SEGUNDO_EN_MILISEGUNDOS);
		} catch (TimeoutException e) {
			assertTrue(finalizaronTodasLasLlamadas(llamadas));
			assertTrue(llamadas.get(0).getReceptor().getCargo().equals(Operador.CARGO));	//1
			assertTrue(llamadas.get(1).getReceptor().getCargo().equals(Operador.CARGO));    //2
			assertTrue(llamadas.get(2).getReceptor().getCargo().equals(Operador.CARGO));    //3
			assertTrue(llamadas.get(3).getReceptor().getCargo().equals(Operador.CARGO));  	//4
			assertTrue(llamadas.get(4).getReceptor().getCargo().equals(Operador.CARGO));    //5
			assertTrue(llamadas.get(5).getReceptor().getCargo().equals(Operador.CARGO));    //6
			assertTrue(llamadas.get(6).getReceptor().getCargo().equals(Operador.CARGO));    //7
			assertTrue(llamadas.get(7).getReceptor().getCargo().equals(Supervisor.CARGO));  //8
			assertTrue(llamadas.get(8).getReceptor().getCargo().equals(Supervisor.CARGO));  //9
			assertTrue(llamadas.get(9).getReceptor().getCargo().equals(Director.CARGO));    //10
			waiter.resume();
		}
	}
	
	
}
