package TuringMachine;
import java.util.ArrayList;

/**
 * @author sam
 *
 */
public class MachineState {

	String estado;
	int posHead;
	ArrayList<String> cinta;
	
	/**
	 * @param estado
	 * @param pos
	 * @param cinta
	 */
	public MachineState(String estado, int pos, ArrayList<String> cinta) {
		this.estado = new String(estado);
		this.posHead = pos;
		this.cinta = new ArrayList<String>(cinta);
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return
	 */
	public int getPosHead() {
		return posHead;
	}

	/**
	 * @param posHead
	 */
	public void setPosHead(int posHead) {
		this.posHead = posHead;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getCinta() {
		return cinta;
	}

	/**
	 * @param cinta
	 */
	public void setCinta(ArrayList<String> cinta) {
		this.cinta = cinta;
	}
	
	/**
	 *
	 */
	public String toString() {
		return "Estado: " + this.estado + " Posicion cabeza : " + this.posHead + " Cinta: " + this.cinta;
	}
	
	public MachineState(MachineState other) {
		super();
		this.estado = new String(other.estado);
		this.posHead = other.posHead;
		this.cinta = new ArrayList<String>(other.cinta);
	}
	
}
