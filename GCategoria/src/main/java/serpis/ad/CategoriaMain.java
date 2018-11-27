package serpis.ad;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class CategoriaMain {
	
	private static Scanner scanner = new Scanner(System.in);
	
	@FunctionalInterface
	public interface Action{
		void execute();
	}

	
	private static boolean exit = false;
	public static void main(String[] args) {
		Menu.create("Menu categoría")
		.exitWhen("0 - Salir")
		.add("1 - Nuevo", CategoriaMain::nuevo)
		.add("2 - Editar", CategoriaMain::editar)
		.loop();
		
		
		
		List<Action> actions = new ArrayList<>();
		actions.add( () -> exit = true);
		actions.add( CategoriaMain:: nuevo);
		actions.add( CategoriaMain:: editar);


	}
	
	public static void nuevo() {
		System.out.println("Método nuevo");
	}
	
	public static void editar() {
		System.out.println("Método editar");
	}

}
