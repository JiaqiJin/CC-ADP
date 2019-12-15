package TuringMachine;
import java.util.*;

public class Transition {

	String estadoActual;
	String estadoSiguiente;
	String escrituraCinta;
	String movimiento;
	String lecturaCinta;
	
	public Transition(String fileLine,ArrayList<String> alfabetoCinta,ArrayList<String> estados) {
		String [] cadenas = fileLine.split("\\s+");
		if(estados.contains(new String(cadenas[0]))) {
			this.estadoActual = new String(cadenas[0]);
		}
		if(alfabetoCinta.contains(new String(cadenas[1]))) {
			this.lecturaCinta = new String(cadenas[1]);
		} 
		if(estados.contains(new String(cadenas[2]))) {
			this.estadoSiguiente = new String(cadenas[2]);
		} 
		if(alfabetoCinta.contains(new String(cadenas[3]))) {
			this.escrituraCinta = new String(cadenas[3]);
		} 
		if(cadenas[4].equals(new String("L")) || cadenas[4].equals(new String("R")) || cadenas[4].equals(new String("S"))) {
			this.movimiento = new String(cadenas[4]);
		} 	
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getEstadoSiguiente() {
		return estadoSiguiente;
	}

	public void setEstadoSiguiente(String estadoSiguiente) {
		this.estadoSiguiente = estadoSiguiente;
	}

	public String getEscrituraCinta() {
		return escrituraCinta;
	}

	public void setEscrituraCinta(String escrituraCinta) {
		this.escrituraCinta = escrituraCinta;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public String getLecturaCinta() {
		return lecturaCinta;
	}

	public void setLecturaCinta(String lecturaCinta) {
		this.lecturaCinta = lecturaCinta;
	}
	
	
	public String toString() {
		return ("Estado " + this.estadoActual + " con lectura cinta " + this.lecturaCinta + " transita al estado " + 
			this.estadoSiguiente + " escribe en la cinta " + this.escrituraCinta + " y se mueve el cabezal a " + this.movimiento);
		
	}
	
	
	/*public boolean checkState(String input , String cadena,ArrayList<String> estados) {
		if(estados.contains(cadena)) {
			return true;
		}
		
		return false;
	}*/
}
