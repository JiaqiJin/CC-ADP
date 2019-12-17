package TuringMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaqi
 *
 */
public class Alfabeto {

	private ArrayList<String> alfabet = new ArrayList<String>();//array de string para almacenar alfabetos

	public ArrayList<String> getAlfabet() {
		return alfabet;
	}

	public void setAlfabet(ArrayList<String> alfabet) {
		this.alfabet = alfabet;
	}
	
	public Alfabeto(String input) {
		String[] elements = input.split("\\s+");
		for(int i=0;i<elements.length;i++) {
			alfabet.add(elements[i]);
		}
	}
}
