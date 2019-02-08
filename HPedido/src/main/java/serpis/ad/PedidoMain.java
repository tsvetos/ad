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
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		App.getInstance().setEntityManagerFactory(Persistence.createEntityManagerFactory("serpis.ad.hmysql"));
		
		List<Categoria> categorias = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select c from Categoria c order by id", Categoria.class).getResultList();
		});
		
		for (Categoria categoria : categorias)
			System.out.printf("%4s %s %n", categoria.getId(), categoria.getNombre());
		
		System.out.println("Artículo añadido. Pulse Enter para continuar...");
		new Scanner(System.in).nextLine();
		
		JpaHelper.execute(entityManager -> {
			Articulo articulo = new Articulo();
			articulo.setNombre("nuevo " + LocalDateTime.now());
			articulo.setPrecio(new BigDecimal(1.5));
			entityManager.persist(articulo);
		});
		
		Articulo articulo4 = JpaHelper.execute(entityManager -> {
			return entityManager.find(Articulo.class, 3L);
		});
		
		show(articulo4);
		
		App.getInstance().getEntityManagerFactory().close();

		
		
	}

	
	
	private static void show(Articulo articulo) {
		System.out.printf("%4s %-30s %-30s %s %n ", articulo.getId(), 
				articulo.getNombre(), articulo.getCategoria(), articulo.getPrecio());
	}
	private static void showPedido(Pedido pedido) {
		System.out.printf("%4s %-30s %-30s %n", pedido.getId(), pedido.getFecha(),pedido.getImporte());
	
	}
	private static void showPedidoLinea(PedidoLinea pedidoLinea) {
		System.out.printf("%4s %-30s %-30s %n", pedidoLinea.getId(), pedidoLinea.getPrecio(),pedidoLinea.getUnidades(),pedidoLinea.getImporte());
	
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
	
	private static void remove(Pedido pedido) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		pedido=entityManager.getReference(Pedido.class, pedido.getId());
		entityManager.remove(pedido);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	private static void remove(PedidoLinea pedidolinea) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		pedidolinea = entityManager.getReference(PedidoLinea.class, pedidolinea.getId());
		entityManager.remove(pedidolinea);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	
	
	public static void updateArticulo() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hmysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Articulo articulo = new Articulo();
		articulo.setId((long) 28);
		articulo = entityManager.find(Articulo.class, articulo.getId());
		
		articulo.setId((long)38);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManagerFactory.close();
		
	}
	
	
	

}
