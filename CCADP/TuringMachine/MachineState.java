package TuringMachine;
import java.util.ArrayList;

public class MachineState {

	String estado;
	int posHead;
	ArrayList<String> cinta;
	
	public MachineState(String estado, int pos, ArrayList<String> cinta) {
		this.estado = new String(estado);
		this.posHead = pos;
		this.cinta = new ArrayList<String>(cinta);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPosHead() {
		return posHead;
	}

	public void setPosHead(int posHead) {
		this.posHead = posHead;
	}

	public ArrayList<String> getCinta() {
		return cinta;
	}

	public void setCinta(ArrayList<String> cinta) {
		this.cinta = cinta;
	}
	
	public String toString() {
		return "Estado: " + this.estado + " Posicion cabeza : " + this.posHead + " Cinta: " + this.cinta;
	}
	
}
