package jhoel.callCenterService.util;

public class ColorPrinter {

	public static void printLnWithColor(String mensaje, int numeroHilo) {
		System.out.println((char) 27 + "[" + calcularCodigoColor(numeroHilo) + "m"+ mensaje);
	}
	
	private static String calcularCodigoColor(int numeroHilo){
		int codigoColor = (numeroHilo - 1) % 8;
		return "3" + codigoColor;
	}

}
