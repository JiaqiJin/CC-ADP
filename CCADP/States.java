package CCADP;
import java.util.*;

public class States {

	private List<Transition> transitions = new ArrayList<Transition>();
	
	private String state;	
	
	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {
		String exitString = "";
		
		for(Transition trans :  transitions) {
			exitString += state + " " + trans.toString() + "\n"; 
		}
		
		return exitString;
	}
	//constructor
	public States(String state) {
		
		this.setState(state);
		
	}
	
	public void addTransition(int transition, String chainIn, String stackIn,String nextState,List<String> stackOutput) {
		transitions.add(new Transition(transition,chainIn, stackIn, nextState, stackOutput));
	}
	
	public ArrayList<Integer> searchTransitions(String state, String chainInput, String stackInput){
		ArrayList<Integer> transitions = new ArrayList<Integer>();
		if(state.equals( this.state)) {	//Si este es el estado a buscar transiciones
		for(Transition tran: this.transitions) {	//Se recorren todas sus transiciones
			if(tran.getStackInput().equals( stackInput))	//Si la pila coincide
				if(tran.getChainInput().equals(Character.toString(chainInput.charAt(0))) || tran.getChainInput().equals(".")) {	//Y la cadena tambien, o no se necesita consumir cadena
					transitions.add(tran.getnTransition());	//Se añade al vector de posibilidades se añade el identificador																			
				}
			
			}
		}
		
		return transitions;
		
	}
	
}
