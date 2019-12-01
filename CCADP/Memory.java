package CCADP;
import java.util.*;

public class Memory {

	private String state;
	private String chain;
	private ArrayList<String> stack = new ArrayList<String>();	
	private int posTransitions;
	
	public Memory(String state, String chain, ArrayList<String> stack, int pos) {
		this.state = state;
		this.chain = chain;
		this.stack = stack;
		this.posTransitions = pos;
		
	}
	
	
	public String toString() {
		return " Estado:  " + state + " Cadena: " + chain + " Pila: " + stack.toString() + " Transicion: " + posTransitions;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getChain() {
		return chain;
	}


	public void setChain(String chain) {
		this.chain = chain;
	}


	public ArrayList<String> getStack() {
		return stack;
	}


	public void setStack(ArrayList<String> stack) {
		this.stack = stack;
	}


	public int getPosTransitions() {
		return posTransitions;
	}


	public void setPosTransitions(int posTransitions) {
		this.posTransitions = posTransitions;
	}
	
	
}
