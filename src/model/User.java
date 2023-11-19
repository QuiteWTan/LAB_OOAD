package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class User {
	private Integer userId;
	private String username;
	private String email;
	private String password;
	private String role;
	
	public User(Integer userId, String username, String email, String password, String role) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public ArrayList<User> GetAllUsers() {
		
		Connect db = Connect.getInstance();
		
		String query = "SELECT * FROM Users";
		
		ResultSet data = db.selectData(query);
		
		ArrayList<User> userData = new ArrayList<>();
		
		try {
			while(data.next()) {
				Integer userIdData = data.getInt("userId");
				String usernameData = data.getString("username");
				String emailData = data.getString("email");
				String passwordData = data.getString("password");
				String roleData = data.getString("role");
				
				userData.add(new User(userIdData, usernameData, emailData, passwordData, roleData));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userData;
	}
}
