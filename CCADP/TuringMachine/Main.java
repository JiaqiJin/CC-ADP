package TuringMachine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] argv) throws IOException {
		//String a = "C:\\Users\\sam\\eclipse-workspace\\cc\\src\\MT\\Cambia0por1.txt";
		String b = "C:\\Users\\sam\\eclipse-workspace\\cc\\src\\TuringMachine\\Ejemplo_MT.txt";
		TuringMachine mt = new TuringMachine(b);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; 
		
		System.out.println("Introduce la cadena (exit para salir) ");		
		input = br.readLine();		
			if(!input.equals("exit")) {
				if(mt.compute(input)) {
					System.out.println("La cadena " + input + " es aceptada por la máquina");
				}else {
					System.out.println("La cadena " + input + " ! NO es aceptada por la máquina");
				}
			}
		
	}
	
}
