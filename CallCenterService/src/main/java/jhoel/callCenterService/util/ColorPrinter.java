package jhoel.callCenterService.util;

/**
 * Clase que tiene las utilidades para imprimir en consola con diferentes
 * colores de fuente
 * 
 * @author Jhoel
 *
 */
public class ColorPrinter {

	/**
	 * Este metodo permite imprimir un mensaje con un color seg&uacuten un n&uacutemero de hilo
	 * @param mensaje
	 * @param numeroHilo
	 */
	public static void printLnWithColor(String mensaje, int numeroHilo) {
		System.out.println((char) 27 + "[" + calcularCodigoColor(numeroHilo) + "m" + mensaje);
	}

	/**
	 * Este metodo calcula el codigo de color de 8 bits para la fuente segun un número,
	 * 
	 * @param numeroHilo
	 * @return
	 */
	private static String calcularCodigoColor(int numeroHilo) {
		int codigoColor = (numeroHilo - 1) % 8;
		return "3" + codigoColor;
	}

}
