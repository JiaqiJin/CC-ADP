package TuringMachine;
import java.util.*;
import java.io.*;

public class TuringMachine {

	Alfabeto alfabetoCinta;								//conjunto de s�mbolos del lenguaje
	Alfabeto alfabetoLenguaje;							//conjunto de s�mbolos de la cinta
	MachineState estadoActual;							//estado de la m�quina (cinta cabezal y estado)
	ArrayList<Transition> transicionesPosibles;			//posibles transiciones 
	ArrayList<String> estados;							//conjunto de estados
	String estadoInicial;								//estado Inicioal
	ArrayList<String> estadosfinales;					//conjunto de estados finales
	String blanco;										// simbolo blanco
	
	public ArrayList traza;
	
	public TuringMachine(String filename) {
		this.transicionesPosibles = new ArrayList<Transition>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line;
			Integer count = 0;
			while((line = br.readLine()) != null) {
				if(line.charAt(0) != '#') {
					switch(count) {
					case 0:
						this.estados = new ArrayList<String>();
						String [] dummy = line.split("\\s+");
						for(int i=0;i<dummy.length;i++) {
							this.estados.add(dummy[i]);
							
						}
						count ++;
						break;
					case 1 :
						this.alfabetoLenguaje = new Alfabeto(line);
						count ++;
						break;
					case 2 :
						this.alfabetoCinta = new Alfabeto(line);
						count ++;
						break;
					case 3 :
						this.estadoInicial = new String(line.split("\\s+")[0]);
						count ++;
						break;
					case 4 :
						this.blanco = new String(line.split("\\s+")[0]);
						count ++;
						break;
					case 5 :
						this.estadosfinales = new ArrayList<String>();
						String[] dummy1 = line.split("\\s+");
						for(int i = 0; i < dummy1.length; i++) {
	    					this.estadosfinales.add(dummy1[i]);
	    				}
						count ++;
						break;
					case 6:
	    				this.transicionesPosibles.add(new Transition(line, this.alfabetoCinta.getAlfabet(), this.estados));
					}
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la carga del fichero: " + e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la carga del aut�mata: " + e);
		}
	}
	
	
	Boolean compute(String cinta) {
		this.traza = new ArrayList<MachineState>();
		String tmp = new String(this.blanco + this.blanco);
		cinta = tmp + cinta + tmp;
		
		
		return false;
	}
}