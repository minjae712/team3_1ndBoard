package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		// DriverManager는 JDBC 드라이버를 통하여 Connection을 만드는 역할
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:team3");
	}
}