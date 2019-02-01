package serpis.ad;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.mysql.cj.Query;



public class PedidoMain {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		
		App.getInstance();
		
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hmysql");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Categoria> categorias =
				entityManager.createQuery("select c from Categoria c", Categoria.class).getResultList();
		
		
		Articulo articulo = insert();
		articulo.setCategoria(categorias.get(new Random().nextInt(categorias.size()) ));
		entityManager.persist(articulo);
		
	
		
		show(articulo);
		//actualizarArticulo();
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Añadido articulo. Pulsa Enter para seguir...");
		new Scanner(System.in).nextLine();
		
		//remove(articulo);
		
		
		entityManagerFactory.close();

	}
	
	
	private static void show(Articulo articulo) {
		System.out.printf("%4s %-30s %-30s %s %n ", articulo.getId(), 
				articulo.getNombre(), articulo.getCategoria(), articulo.getPrecio());
	}

	
	private static Articulo insert() {
		Articulo articulo = new Articulo();
		articulo.setNombre("nuevo " + LocalDateTime.now());
		articulo.setPrecio(new BigDecimal(1.5));
		return articulo;
		
	}
	
	
	private static void remove(Articulo articulo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		//articulo = entityManager.find(Articulo.class, articulo.getId());
		articulo = entityManager.getReference(Articulo.class, articulo.getId());
		entityManager.remove(articulo);
		

		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	public static void updateArticulo() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hmysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Articulo articulo = new Articulo();
		articulo.setId((long) 1);
		articulo = entityManager.find(Articulo.class, articulo.getId());
		
		articulo.setId((long)4);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManagerFactory.close();
		
	}
	

}
