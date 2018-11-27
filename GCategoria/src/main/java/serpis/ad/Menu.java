package serpis.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import serpis.ad.CategoriaMain.Action;


public class Menu {
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static Menu create(String labelMenu) {
		return new Menu(labelMenu);
	}
	
	private String labelMenu;
	private Menu(String labelMenu) {
		this.labelMenu = labelMenu;
	}
	
	List<String> options = new ArrayList<>();
	List<String> labels = new ArrayList<>();
	List<Action> actions = new ArrayList<>();
	
	public Menu add(String label, Action action) {
		String option = label.trim().substring(0, 1).toUpperCase();
		options.add(option);
		labels.add(label);
		actions.add(action);
		return this;
	}
	
	private boolean exit = false;
	public Menu exitWhen(String label) {
		return add(label, () -> exit = true);
		
	}
	public void loop() {
		while(!exit) {
			for (String label : labels) {
				System.out.println(label);
			}
			System.out.println("Elige la opción: ");
			int option = Integer.parseInt(scanner.nextLine());
			actions.get(option).execute();
		}
	}
	
	

}
