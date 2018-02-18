package Jhoel.CallCenterService.core.util;

import java.util.List;

import jhoel.callCenterService.core.Llamada;
import jhoel.callCenterService.util.Constantes;

public class Monitor implements Runnable {
	private List<Llamada> llamadas;

	public Monitor(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public void run() {
		while (finalizaronTodasLasLlamadas(llamadas)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
