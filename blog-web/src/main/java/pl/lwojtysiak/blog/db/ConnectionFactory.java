package pl.lwojtysiak.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for creating connection to database.
 * 
 * @author lwojtysiak
 * 
 */
public class ConnectionFactory {
	String driverClassName = "org.sqlite.JDBC";
	String connectionUrl = "jdbc:sqlite:";
	
	private String dbFilePath = "d:/blog.db";

	/** ConnectionFactory object - to provide Singleton. */
	private static ConnectionFactory connectionFactory = null;

	/**
	 * Private constructor.
	 */
	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("ConnectionFactory Exception: " + e);
		}
	}

	/**
	 * Method returns connection to database.
	 * 
	 * @return Connection object
	 * @throws SQLException
	 *             Exception thrown when creating connection failed.
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl + dbFilePath);

		return conn;
	}

	/**
	 * Method returns singleton ConnectionFactory object.
	 * 
	 * @return ConnectionFactory object
	 */
	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}

		return connectionFactory;
	}
	
	/**
	 * @return the dbFilePath
	 */
	public String getDbFilePath() {
		return dbFilePath;
	}

	/**
	 * @param dbFilePath the dbFilePath to set
	 */
	public void setDbFilePath(String dbFilePath) {
		this.dbFilePath = dbFilePath;
	}
}
