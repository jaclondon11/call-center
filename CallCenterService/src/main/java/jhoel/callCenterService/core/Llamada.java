package jhoel.callCenterService.core;

import java.util.Random;

import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.util.ColorPrinter;
import jhoel.callCenterService.util.Constantes;

/**
 * Esta clase representa los objetos llamada los cuales implementan la interfaz
 * Runnable para poder iniciar de forma concurrente
 * 
 * @author Jhoel
 *
 */
public class Llamada implements Runnable{
	
	private int id;
	private double duracion;
	private Empleado receptor;
	private int estado;
	private Dispatcher dispatcher;
	

	/**
	 * Constructor minimo
	 */
	public Llamada() {
		super();
		this.duracion = estimarDuracion();
		this.estado = Constantes.ESTADOS_LLAMADA.EN_ESPERA.getEstado();
	}
	
	/**
	 * Este metodo asigna la llamada a un empleado y recibe a un dispatcher y un
	 * id de llamada. Tambien inicia el hilo de ejecuci&oacuten de la llamada
	 * 
	 * @param empleado
	 * @param dispatcher
	 * @param id
	 */
	public void asignarLlamada(Empleado empleado, Dispatcher dispatcher, int id) {
		empleado.setLlamadaActual(this);
		this.receptor = empleado;
		this.dispatcher = dispatcher;
		this.id = id;
		Thread t = new Thread(this);
	    t.start();
    }
	
	/**
	 * Metodo necesario de la implementaci&oacuten Runnable para la
	 * ejecuci&oacuten del hilo
	 */
	public void run() {
		iniciarLlamada();
	}
	
	/**
	 * Este metodo se encarga de iniciar la llamada y dormir el hilo de
	 * ejecuci&oacuten de la llamada cada segundo hasta terminar la
	 * duraici&oacuten asignada de forma aleatoria
	 */
	private  void iniciarLlamada() {
		ColorPrinter.printLnWithColor("INICIO LLAMADA: " + id + "; con duracion: " + duracion + " segundo(s),"
				+ " Por empleado " + receptor.getNombre() + " " + receptor.getCargo(), id);
		this.estado = Constantes.ESTADOS_LLAMADA.ACTIVA.getEstado();
		for (int i = 1; i <= duracion; i++) {
			try {
				Thread.sleep(Constantes.SEGUNDO_EN_MILISEGUNDOS);
			} catch (InterruptedException e) {
				ColorPrinter.printLnWithColor("LLAMADA " + id + " INTERRUMPIDA.", id);
			}
			ColorPrinter.printLnWithColor("LLAMADA " + id + " Tiempo transcurrido " + i + " segundo(s)", id);
		}
		ColorPrinter.printLnWithColor("LLAMADA " + id + " TERMINADA.", id);
		finalizarLlamada();
	}

	/**
	 * Este metodo se encarga de finalizar la llamada y de desocupar al empleado
	 * responsable de la misma
	 */
	private void finalizarLlamada() {
		this.estado = Constantes.ESTADOS_LLAMADA.FINALIZADA.getEstado();
		dispatcher.desocuparEmpleado(receptor);
	}

	/**
	 * Metodo que calcula aleatoriamente la duracion de la llamada
	 * 
	 * @return
	 */
	private static double estimarDuracion() {
		Random r = new Random();
		return r.nextInt(Constantes.DURACION_MAXIMA + 1 - Constantes.DURACION_MINIMA) + Constantes.DURACION_MINIMA;
	}
	
	//Getters and Setters

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Empleado getReceptor() {
		return receptor;
	}

	public void setReceptor(Empleado receptor) {
		this.receptor = receptor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}