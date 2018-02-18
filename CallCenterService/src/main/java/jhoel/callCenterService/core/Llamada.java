package jhoel.callCenterService.core;

import java.util.Random;

import jhoel.callCenterService.empleado.Empleado;
import jhoel.callCenterService.util.ColorPrinter;
import jhoel.callCenterService.util.Constantes;

/**
 * 
 * @author Jhoel
 *
 */
public class Llamada implements Runnable{
	
	private static int idContador = 1;
	
	private int id;
	private double duracion;
	private String nombre;
	private Empleado receptor;
	private int estado;
	

	/**
	 * Constructor minimo
	 * @param nombre
	 */
	public Llamada(String nombre) {
		super();
		this.nombre = nombre;
		this.duracion = estimarDuracion();
		this.estado = Constantes.ESTADOS_LLAMADA.EN_ESPERA.getEstado();
		this.id = idContador++;
	}
	
	//TODO ARREGLAR NOMBRE EMPLEADO POR CARGO
	public void run() {
		ColorPrinter.printLnWithColor("INICIO LLAMADA: " + nombre + " con duracion: " + duracion + "segundo(s), Por empleado " + receptor.getNombre(), id);
		this.estado = Constantes.ESTADOS_LLAMADA.ACTIVA.getEstado();
		for (int i = 1; i <= duracion; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				ColorPrinter.printLnWithColor("LLAMADA " + nombre + " INTERRUMPIDA.", id);
			}
			ColorPrinter.printLnWithColor("LLAMADA " + nombre + " Tiempo transcurrido " + i + " segundo(s)", id);
		}
		ColorPrinter.printLnWithColor("LLAMADA " + nombre + " TERMINADA.", id);
		finalizarTarea();
	}
	
	private void finalizarTarea(){
		receptor.setLlamadaActual(null);
		this.estado = Constantes.ESTADOS_LLAMADA.FINALIZADA.getEstado();
	}

	/**
	 * Metodo que calcula aleatoriamente la duracion
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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