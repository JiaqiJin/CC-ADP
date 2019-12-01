package CCADP;
import java.util.*;

public class Alfabeto {

	private String type;
	private List<String> alfabet = new ArrayList<String>();
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<String> getAlfabet() {
		return alfabet;
	}
	
	public void setAlfabet(List<String> alfabet) {
		this.alfabet = alfabet;
	}
	
	//Constructor
	public Alfabeto(String[] strings, String type)  {
		this.type = type;
		for(String str : strings)
			this.alfabet.add(str);
	}
	
	public boolean pertenece(char letra) {
		if(!alfabet.contains(Character.toString(letra))) {
			return false;
		}
		return true;
	}
	
	
	public void pertenece(String letra) throws Exception {
		if(!alfabet.contains(letra) && !letra.equals(".")) {
			Exception e = new Exception("Caracter '" + letra + "' no pertenece al alfabeto " + type + ".");
        	throw e;
		}
	}
	
	public String toString() {
		return alfabet.toString();
	}
}
