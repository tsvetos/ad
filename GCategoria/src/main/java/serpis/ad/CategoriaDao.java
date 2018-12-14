package serpis.ad;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
	
	
	private static String insertSql = "insert into categoria (nombre) values (?)";
	public static int insert(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
		preparedStatement.setObject(1, categoria.getNombre());
		return preparedStatement.executeUpdate();
	}
	
	private static String updateSql = "update categoria set nombre = @nombre where id = @id";
	private static int update(Categoria categoria) throws SQLException{
		PreparedStatement ps = App.getInstance().getConnection().prepareStatement(updateSql);
		ps.setObject(0, categoria.getId());
		ps.setObject(1, categoria.getNombre());
		return ps.executeUpdate();
	}
	
	public static int save(Categoria categoria) throws SQLException {
		if (categoria.getId() == 0)
			return insert(categoria);
		else
			return update(categoria);
	}
	
	private static String selectSql = "select id, nombre from categoria where id = ?";
	public static Categoria load(long id)throws SQLException {
		PreparedStatement ps = App.getInstance().getConnection().prepareStatement(selectSql);
		ps.setObject(1, id);
		ResultSet rst = ps.executeQuery();
		
		if(rst.next()) {;
			Categoria categoria = new Categoria();
			categoria.setId( rst.getLong("id")  );
			categoria.setNombre((String)rst.getObject("nombre"));
			return categoria;
		}
		return null;
		
	}

	private static String deleteSql = "delete from categoria where id = ?";
	public static int delete(long id)throws SQLException {
	
		PreparedStatement ps = App.getInstance().getConnection().prepareStatement(deleteSql);
		ps.setObject(1, id);
		return ps.executeUpdate();
	
	}
	
	
	private static String getAllSql = "select id, nombre from categoria";
	public static List<Categoria> getAll()throws SQLException {

		List<Categoria> categorias = new ArrayList<>();
		Statement stm = App.getInstance().getConnection().createStatement();
	    ResultSet rst = stm.executeQuery(getAllSql);
	   
	    while (rst.next()) {
	    	Categoria categoria = new Categoria();
	    	//categoria.setId( ((BigInteger)rst.getObject("id")).longValue()  );
	    	categoria.setId( rst.getLong("id")  );
	    	categoria.setNombre((String)rst.getObject("nombre"));
	    	
	    	categorias.add(categoria);
	    }
	    stm.close();
	    return categorias;
		
	}
}
	

	


