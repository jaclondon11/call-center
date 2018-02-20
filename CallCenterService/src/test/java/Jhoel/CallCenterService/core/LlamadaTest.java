package Jhoel.CallCenterService.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jhoel.callCenterService.core.Llamada;
import jhoel.callCenterService.util.Constantes;

/**
 * 
 * @author Jhoel
 *
 */
public class LlamadaTest {

	/**
	 * Este test se realizo para testear la creación de muchas tareas y validar
	 * que todas tengan un tiempo de duraicon de 5 a 10 segundos
	 */
	@Test
	public void validarDuracionAleatoriaLlamadas() {
		boolean valorMinimo = false;
		boolean valorMaximo = false;
		for (int i = 0; i < 1000; i++) {
			Llamada llamada = new Llamada();
			assertTrue(llamada.getDuracion() >= Constantes.DURACION_MINIMA
					&& llamada.getDuracion() <= Constantes.DURACION_MAXIMA);
			valorMinimo = llamada.getDuracion() == Constantes.DURACION_MINIMA ? true : valorMinimo;
			valorMaximo = llamada.getDuracion() == Constantes.DURACION_MAXIMA ? true : valorMaximo;
		}
		assertTrue(valorMaximo);
		assertTrue(valorMinimo);
	}
}
