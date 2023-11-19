package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String username = "root";
	private final String password = "";
	private final String host = "localhost:3306";
	private final String database = "snova";
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);
		
	private Connection con;
	private static Connect connect;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static Connect getInstance() {
		if(connect == null) {
			connect = new Connect();
		}
		
		return connect;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(connection, username, password);
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertData(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet selectData(String query) {
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}

}
