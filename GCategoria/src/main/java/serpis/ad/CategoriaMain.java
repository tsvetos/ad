package serpis.ad;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CategoriaMain {
	
	
	public static void main(String[] args) throws SQLException {
		App.getInstance().setConnection(
				DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?user=root&password=sistemas")
		);
		Menu.create("Menu categoría")
		.exitWhen("\t0 - Salir")
		.add("\t1 - Nuevo", CategoriaMain::nuevo)
		.add("\t2 - Editar", CategoriaMain::editar)
		.loop();
	
	}
	
	public static void tryAction(DaoAction daoAction, String errorMessage) {
		
	}
	
	public static void nuevo() throws SQLException {
		Categoria categoria = new Categoria();
		CategoriaConsole.newCategoria(categoria);
		CategoriaDao.save(categoria);
	}
	
	public static void editar() {
		long id = CategoriaConsole.getId();
		Categoria categoria = CategoriaDao.load(id);
		if(categoria == null) {
			CategoriaConsole.idNotExists();
			return;
		}
		CategoriaConsole.newCategoria(categoria);
		CategoriaDao.save(categoria);
		
		
	}
	public static void eliminar() {
		long id = CategoriaConsole.getId();
		if(CategoriaConsole.deleteConfirm())
			CategoriaDao.delete(id);
	}
	
	public static void consultar() {
		long id = CategoriaConsole.getId();
		Categoria categoria = CategoriaDao.load(id);
		if(categoria == null) {
			CategoriaConsole.idNotExists();
			return;
		}
		CategoriaConsole.show(categoria);
	}
	
	public static void listar() {
		List<Categoria> categorias = CategoriaDao.getAll();
		CategoriaConsole.showList(categorias);
	}
	
	

}
