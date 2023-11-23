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
	
	public ArrayList<User> getAllUsers() {
		
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
	
	
	public static User getUser(Integer userId) {
        Connect db = Connect.getInstance();

        String query = String.format("SELECT * FROM Users WHERE userId = %d", userId);

        ResultSet data = db.selectData(query);

        try {
            if (data.next()) {
                String usernameData = data.getString("username");
                String emailData = data.getString("email");
                String passwordData = data.getString("password");
                String roleData = data.getString("role");

                return new User(userId, usernameData, emailData, passwordData, roleData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public static boolean addUser(String username, String email, String password, String role) throws SQLException {
	    Connect db = Connect.getInstance();

	    String query = String.format("INSERT INTO Users (username, email, password, role) VALUES ('%s', '%s', '%s', '%s')",
	            username, email, password, role);

	    db.execute(query);
		return true;
	}
	
	public static boolean deleteUser(Integer userId) {
        Connect db = Connect.getInstance();

        String query = String.format("DELETE FROM Users WHERE userId = %d", userId);

        db.execute(query);
        return true;
    }
	
    public static ArrayList<User> getAllUsersInRole(String role) {
        Connect db = Connect.getInstance();

        String query = String.format("SELECT * FROM Users WHERE role = '%s'", role);

        ResultSet data = db.selectData(query);

        ArrayList<User> userData = new ArrayList<>();

        try {
            while (data.next()) {
                Integer userIdData = data.getInt("userId");
                String usernameData = data.getString("username");
                String emailData = data.getString("email");
                String passwordData = data.getString("password");

                userData.add(new User(userIdData, usernameData, emailData, passwordData, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
    
    public static ArrayList<User> getAllUsersByEmail(String email) {
        Connect db = Connect.getInstance();

        String query = String.format("SELECT * FROM Users WHERE email = '%s'", email);

        ResultSet data = db.selectData(query);

        ArrayList<User> userData = new ArrayList<>();

        try {
            while (data.next()) {
                Integer userIdData = data.getInt("userId");
                String usernameData = data.getString("username");
                String passwordData = data.getString("password");
                String roleData = data.getString("role");

                userData.add(new User(userIdData, usernameData, email, passwordData, roleData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
    
    
}
