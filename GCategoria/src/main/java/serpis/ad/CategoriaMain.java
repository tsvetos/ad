package serpis.ad;

import java.util.List;

import Menu;
import ScannerHelper;
import Menu.Action;

import java.util.ArrayList;

public class CategoriaMain {
	
	
	
	private static boolean exit = false;
	public static void main(String[] args) {
		Menu.create("Menu categoría")
		.exitWhen("\t0 - Salir")
		.add("\t1 - Nuevo", CategoriaMain::nuevo)
		.add("\t2 - Editar", CategoriaMain::editar)
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
		int id = ScannerHelper.getInt("Id: ");
	}
	
	

}
