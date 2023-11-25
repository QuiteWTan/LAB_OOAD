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
		
		String query = "SELECT * FROM `users`";
		
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

        String query = String.format("SELECT * FROM `users` WHERE userId = %d", userId);

        ResultSet data = db.selectData(query);

        try {
            String usernameData = data.getString("username");
            String emailData = data.getString("email");
            String passwordData = data.getString("password");
            String roleData = data.getString("role");

            return new User(userId, usernameData, emailData, passwordData, roleData);
                
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	public static void addUser(String username, String email, String password, String role) {
		
	    Connect db = Connect.getInstance();

	    String query = String.format("INSERT INTO `users` (username, email, password, role) VALUES ('%s', '%s', '%s', '%s')",
	            username, email, password, role);

	    db.execute(query);
  
	}
	
	public void deleteUser(Integer userId) {
        Connect db = Connect.getInstance();

        String query = String.format("DELETE FROM `users` WHERE userId = %d", userId);

        db.execute(query);
        
    }
	
    public ArrayList<User> getAllUsersInRole(String role) {
        Connect db = Connect.getInstance();

        String query = String.format("SELECT * FROM `users` WHERE role = '%s'", role);

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
    
    public ArrayList<User> getAllUsersByEmail(String email) {
        Connect db = Connect.getInstance();

        String query = String.format("SELECT * FROM users WHERE email = '%s'", email);

        ResultSet data = db.selectData(query);

        ArrayList<User> userData = new ArrayList<>();

        try {
            while (data.next()) {
                Integer userIdData = data.getInt("userId");
                String usernameData = data.getString("username");
                String userEmailData = data.getString("email");
                String passwordData = data.getString("password");
                String roleData = data.getString("role");

                userData.add(new User(userIdData, usernameData, userEmailData, passwordData, roleData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }
    
    public static Boolean searchExistingUsername(String username) {
    	
    	Connect db = Connect.getInstance();
    	
    	String query = String.format("SELECT username FROM users WHERE username = '%s'", username);
    	
    	ResultSet data = db.selectData(query);
    	
    	String usernameData = null;
    	
    	try {
			usernameData = data.getString("username");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(usernameData == null) {
    		return true;
    	}
    	
    	return false;
    }
    
public static Boolean searchExistingEmail(String email) {
    	
    	Connect db = Connect.getInstance();
    	
    	String query = String.format("SELECT email FROM users WHERE email = '%s'", email);
    	
    	ResultSet data = db.selectData(query);
    	
    	String emailData = null;
    	
    	try {
			emailData = data.getString("email");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(emailData == null) {
    		return true;
    	}
    	
    	return false;
    }
    
}
