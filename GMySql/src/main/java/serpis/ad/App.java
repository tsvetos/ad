package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	public static void conn() throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
	}
	

}
