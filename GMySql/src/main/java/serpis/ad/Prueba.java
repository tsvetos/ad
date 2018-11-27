package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Prueba {
	
	private static Connection connection;

	public static void main(String[] args) throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
		
		
		//insert();
		loadAll();
		
	
				
		
		
		
	}
	
	public static void loadAll() throws SQLException {
		String loadSql = "select * from categoria";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(loadSql);
		while (resultSet.next())
			System.out.printf("%s %s\n",resultSet.getObject(1), resultSet.getObject(2));
		statement.close(); //implicit resultSet.close()
		connection.close();
	}
	
	private static void insert() throws SQLException {
		String insertSql = "insert into categoria (nombre) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
		preparedStatement.setObject(1, "categoria " + LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static void delete(int id) {
		String deleteSql = "delete from categoria where id = ?";
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			
			
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("No se puede eliminar porque el registro es fundamental");
			System.out.println("______________________________________");
		}

		
		
	}

	
}