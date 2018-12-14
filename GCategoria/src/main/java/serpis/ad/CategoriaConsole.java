package serpis.ad;

import java.util.List;

public class CategoriaConsole {
	
	public static long getId() {
		return ScannerHelper.getInt("Elige id:");
	}
	
	public static void newCategoria(Categoria categoria) {
		
		
	}
	
	public static void editCategoria(Categoria categoria) {
		
	}
	
	public static void idNotExists() {
		
	}
	
	public static boolean deleteConfirm() {
		if(ScannerHelper.getConfirm("") == 'y' | ScannerHelper.getConfirm("") == 'Y');
		return true;
	}
	
	public static void show(Categoria categoria) {
		System.out.printf("%5s %-20s []\n", categoria.getId(), categoria.getNombre());
	}
	
	public static void showList(List<Categoria> categorias) {
		for(Categoria categoria : categorias) 
			System.out.printf("%5s %-20s []\n", categoria.getId(), categoria.getNombre());
		
		
	}

}
