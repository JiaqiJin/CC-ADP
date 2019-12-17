package TuringMachine;
import java.util.*;
import java.io.*;

/**
 * @author jiaqi
 *
 */
public class TuringMachine {

	Alfabeto alfabetoCinta;								//conjunto de símbolos del lenguaje
	Alfabeto alfabetoLenguaje;							//conjunto de símbolos de la cinta
	MachineState estadoActual;							//estado de la máquina (cinta cabezal y estado)
	ArrayList<Transition> transicionesPosibles;			//posibles transiciones 
	ArrayList<String> estados;							//conjunto de estados
	String estadoInicial;								//estado Inicioal
	ArrayList<String> estadosfinales;					//conjunto de estados finales
	String blanco;										// simbolo blanco
	
	public ArrayList traza;
	
	/**
	 * @param filename
	 */
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
			System.out.println("Error en la carga del autómata: " + e);
		}
	}
	
	
	/**
	 * @param cinta
	 * @return
	 */
	Boolean compute(String cinta) {
		this.traza = new ArrayList<MachineState>();
		String tmp = new String(this.blanco + this.blanco);
		cinta = tmp + cinta + tmp;
		String[] cintTemp = cinta.split("");
		ArrayList<String> cintaMachine = new ArrayList<String>();
		for(int i=0;i<cintTemp.length;i++) {
			cintaMachine.add(cintTemp[i]);
		}
		
		Boolean fin = false;
		this.estadoActual = new MachineState(this.estadoInicial,2,cintaMachine);
		while(!fin) {
			this.traza.add(new MachineState(this.estadoActual));
			Boolean encontrado = false;
			for(int i=0;i<transicionesPosibles.size();i++) {
				if(transicionesPosibles.get(i).getEstadoActual().equals(this.estadoActual.getEstado())
						&&transicionesPosibles.get(i).getLecturaCinta().equals(this.estadoActual.cinta.get(this.estadoActual.getPosHead()))) {
					this.estadoActual.cinta.set(this.estadoActual.posHead, transicionesPosibles.get(i).getEscrituraCinta()); // posibles transiciones
					this.estadoActual.setEstado(transicionesPosibles.get(i).estadoSiguiente);//actualizo al siguiente estado
					if(transicionesPosibles.get(i).movimiento.equals("L")) {
						this.estadoActual.setPosHead(this.estadoActual.getPosHead() - 1);
					}
					if(transicionesPosibles.get(i).movimiento.equals("R")) {
						this.estadoActual.setPosHead(this.estadoActual.getPosHead() + 1);
					}
					if(this.estadoActual.getPosHead() < 0) {
						this.estadoActual.cinta.add(0, ".");
					}
					if(this.estadoActual.getPosHead() > this.estadoActual.getCinta().size() - 1) {
						this.estadoActual.cinta.add(".");
					}
					encontrado = true;
				}
			}
			if(!encontrado) {
				fin = true;
			}
		}
		if(this.estadosfinales.contains(this.estadoActual.getEstado())) {
			return true;
		}
		return false;
	}
	
	
}
