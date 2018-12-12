package serpis.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
	
	
	private static String insertSql = "insert into categoria (nombre) values (?)";
	public static int insert(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
		preparedStatement.setObject(1, categoria.getNombre());
		return preparedStatement.executeUpdate();
	}
	
	private static int update(Categoria categoria) throws SQLException{
		return -1;
	}
	
	public static int save(Categoria categoria) throws SQLException {
		if (categoria.getId() == 0)
			return insert(categoria);
		else
			return update(categoria);
	}
	
	public static Categoria load(long id)throws SQLException {
		return null;
	}

	private static String deleteSql = "delete from categoria (nombre) values (?)";
	public static int delete(long id)throws SQLException {
		PreparedStatement ps = App.getInstance().getConnection().prepareStatement(deleteSql);
		ps.setLong(1, id);
		return ps.executeUpdate();	
	}
	
	
	private static String getAllSql = "SELECT * FROM categoria";
	public static List<Categoria> getAll()throws SQLException {
		PreparedStatement stm = App.getInstance().getConnection().prepareStatement(insertSql);
	    ResultSet rst;
	    rst = stm.executeQuery(getAllSql);
	    List<Categoria> categorias = new ArrayList<>();
	    while (rst.next()) {
	    	Categoria categoria = new Categoria(rst.getLong("id"), rst.getString("name") );
	    	categorias.add(categoria);
	    }
	    return categorias;
		
	}
}
	

	


