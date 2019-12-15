package TuringMachine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] argv) throws IOException {
		String a = "C:\\Users\\sam\\eclipse-workspace\\cc\\src\\MT\\Cambia0por1.txt";
		TuringMachine mt = new TuringMachine(a);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input; 
		
		System.out.println("Introduce la cadena (exit para salir) ");		
		input = br.readLine();
		do {
			
		}while(!input.equals("exit"));
	}
	
}
