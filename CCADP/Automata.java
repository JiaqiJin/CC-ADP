package CCADP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Automata {

	private ArrayList<String> stack = new ArrayList<String>(); //Pila

	private Alfabeto chainAlfabet;	//Alfabeto de cadena

	private Alfabeto stackAlfabet;	//Alfabeto de pila

	private String initialStack;	//Elemento inicial stack
	
	private String initialState;	//Estado inicial

	private List<States> states = new ArrayList<States>();	//Estados y transiciones del automata
	
	private ArrayList<Memory> memory = new ArrayList<Memory>();	//Pila de memoria para recorrer los caminos

	public Automata(String fichero) throws Exception {
		
		Scanner in = new Scanner(new File(fichero));
		String[] estados;
		
		do {
			//Compruebo que las primeras lineas no sean comentarios
			estados = in.nextLine().split(" ");
		}while(estados[0].charAt(0) == '#');
		
		for(String str : estados) {
			this.states.add(new States(str)); // guardo los estados
			System.out.println(str.toString());
		}
		
		
		chainAlfabet = new Alfabeto(in.nextLine().split(" "), "Cadena");
		//System.out.println(chainAlfabet.toString());
		stackAlfabet = new Alfabeto(in.nextLine().split(" "), "Pila");
		//System.out.println(stackAlfabet.toString());
		initialState = in.nextLine();
		//System.out.println(initialState.toString());
		initialStack = in.nextLine();
		//System.out.println(initialStack.toString());
		int nTransiciones = 1; // identificador de transiciones
		
		while(in.hasNextLine()) {
			String[] transiciones = in.nextLine().split(" ");
			
			for(States estado : states) {
				if(estado.getState().equals(transiciones[0])) {
					List<String> outStack = new ArrayList<String>();
					for(int i= 4 ;i<transiciones.length;i++) {
						stackAlfabet.pertenece(transiciones[i]); // comprobar si pertenece al alfabetos
						outStack.add(transiciones[i]); // elemento a escribir en la pila
						//System.out.println("*--------");
						//System.out.println(outStack.toString());
					}
					chainAlfabet.pertenece(transiciones[1]);
					stackAlfabet.pertenece(transiciones[2]);
					estado.addTransition(nTransiciones, transiciones[1], transiciones[2], transiciones[3], new ArrayList<String>(outStack));
					nTransiciones ++;
				}
			}
		}		
		
	}
	
	public static void main(String[] args) {
		
		Automata aut = null;
		int entrada = 0;
		try {
			aut = new Automata("C:\\Users\\fdsam\\OneDrive\\文档\\CC\\Automata de Pila\\Ficheros\\APv-2.txt");
			//System.out.printf("fichero cargado");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(aut.toString());
		String cadena = "1111";
		if(aut.Comprobaciones(cadena)) {
			System.out.println("La cadena se ACEPTA");
		}else {
			System.out.println("La cadena No se ACEPTA");
		}
		/*InputStreamReader isr = new InputStreamReader(System.in);	//Para leer de consola
		BufferedReader br = new BufferedReader (isr);
		
		try {
			String cadena = br.readLine();
			entrada = Integer.parseInt(cadena);
			
			boolean aceptada = true;	//Para saber si la cadena cumple el alfabeto
			do {
				aceptada = true;
				System.out.println("Introduce la cadena a probar: ");
				cadena = br.readLine();
				for(int i = 0; i < cadena.length(); i++)
					if(!aut.chainAlfabet.pertenece(cadena.charAt(i)))
						aceptada = false;	//Desde que un elemento no pertezca no se acepta
				
			}while(!aceptada);	//Hasta que se escriba una valida

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public boolean Comprobaciones(String chain) {
		
		int transicion = 0;
		boolean flag = false;
		String actualEstado = initialState;
		
		ArrayList<Integer> transiciones = new ArrayList<Integer>();
		
		for(States state : states) {
			if(state.getState().equals(actualEstado))
				transiciones = state.searchTransitions(actualEstado, chain, initialStack); // primero transiciones
		}
		stack.clear();
		stack.add(initialStack); // añadir primer elemento en la pila
		
		if(transiciones.size() != 0) {
			for(int i : transiciones) {
				// añadir en la memoria los datos
				memory.add(new Memory(actualEstado , chain , new ArrayList<String>(stack), i));
				//System.out.printf("memeoria " + memory.toString());
				//System.out.printf("\n");
				
			}
		}else {
			flag = true;
		}
		transiciones.clear(); 
		
		while(!flag) {
			chain = memory.get(memory.size()- 1).getChain();
			stack = memory.get(memory.size()-1).getStack(); // saco de la pila el primer pos 
			actualEstado = memory.get(memory.size()-1).getState();	//Saco la transicion del top
			transicion = memory.get(memory.size()-1).getPosTransitions();	//Saco el identificador de la transicion
			memory.remove(memory.size()-1); 
			//System.out.println(chain.toString() + "  " + stack.toString() + "  " + actualEstado.toString() + "  " + transicion);
			Transition tran_ = null;
			for(States state : states) {
				for(Transition trans : state.getTransitions()) { // transciones del identificador
					if(trans.getnTransition() == transicion)
						tran_ = trans;
				}
			}
			
			if(!tran_.getChainInput().equals(".")) { // si no es vacio quito 1 elemeto de la cadena
				chain = chain.substring(1); // quito la letra de la cadena
			}
			stack.remove(stack.size()-1); // quito el top de la pila
			actualEstado = tran_.getNextState();
			
			for(int i = tran_.getStackOutput().size()-1;i>=0;i--) {
				if (!tran_.getStackOutput().get(i).equals(".")) { // si no es epsilon
					//añadir elemetods la pila de tranbsicioens 
					stack.add(tran_.getStackOutput().get(i));
				}
			}
			// si la pila no vacio
			if(!stack.isEmpty()) {
				for(States state : states) {
					if(state.getState().equals(actualEstado)) // busco el estadpo actual
						if(chain.length() != 0) // si la cadena aun no vacio
							//busco posibles transiciones 
							transiciones = state.searchTransitions(actualEstado, chain, stack.get(stack.size() - 1)); 
						else
							//si es vacio busco transiciones que no cosume cadena
							transiciones = state.searchTransitions(actualEstado, ".", stack.get(stack.size() - 1)); 
				}
			}
			
			if(!transiciones.isEmpty()) {	//Si tiene transiciones, si no, no se añade a memoria pues es que no se acepta por dicho camino
				for(int i : transiciones) {
					memory.add(new Memory(actualEstado, chain, new ArrayList<String>(stack), i));		//Las meto en la memoria
				}
				transiciones.clear();
			}
			
			if(chain.isEmpty())	//Si la cadena esta vacia
				if(stack.isEmpty())	//Y la pila tambien
					flag = true;	//Se acepta la cadena
			if(memory.isEmpty())	//Si no hay nada en la memoria
				flag = true;	//Se han hecho todas las posibilidades y no se acepta
			
		}
		
		if(stack.isEmpty() && chain.isEmpty()) {
			return true;	//Se acepta
		}
		
		return false;
	}
	
	public String toString() {
		String cadena = "";
		
		for(States st : states) {
			cadena += st.getState() + " ";
		}
		cadena += "\n";
		
		cadena += chainAlfabet.toString() + "\n";
		cadena += stackAlfabet.toString() + "\n";
		cadena += initialState + "\n";
		cadena += initialStack + "\n";
		for(States st : states) {
			cadena += st.toString();
		}
		
		
		return cadena;
	}
	

}
