package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.object.User;

public class UserModel {

	public ArrayList<User> getAllUsers() {

		Connect db = Connect.getInstance();

		String query = "SELECT * FROM `users`";

		ResultSet data = db.selectData(query);

		ArrayList<User> userData = new ArrayList<>();

		try {
			while (data.next()) {
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

	public User getUser(Integer userId) {

		Connect db = Connect.getInstance();

		String query = String.format("SELECT * FROM `users` WHERE userId = %d", userId);

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

	public void addUser(String username, String email, String password, String role) {

		Connect db = Connect.getInstance();

		String query = String.format(
				"INSERT INTO `users` (username, email, password, role) VALUES ('%s', '%s', '%s', '%s')", username,
				email, password, role);

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

	public User getUserByEmail(String email) {
		Connect db = Connect.getInstance();

		String query = String.format("SELECT * FROM users WHERE email = '%s'", email);

		ResultSet data = db.selectData(query);

		try {
			if (data.next()) {
				Integer userIdData = data.getInt("userId");
				String usernameData = data.getString("username");
				String userEmailData = data.getString("email");
				String passwordData = data.getString("password");
				String roleData = data.getString("role");

				return new User(userIdData, usernameData, userEmailData, passwordData, roleData);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;
	}

	public Boolean searchExistingUsername(String username) {

		Connect db = Connect.getInstance();

		String query = String.format("SELECT username FROM users WHERE username = '%s'", username);

		ResultSet data = db.selectData(query);

		String usernameData = null;

		try {
			if (data.next()) {
				usernameData = data.getString("username");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (usernameData == null) {
			return true;
		}

		return false;
	}

	public Boolean searchExistingEmail(String email) {

		Connect db = Connect.getInstance();

		String query = String.format("SELECT email FROM users WHERE email = '%s'", email);

		ResultSet data = db.selectData(query);

		String emailData = null;

		try {
			if (data.next()) {
				emailData = data.getString("email");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (emailData == null) {
			return true;
		}

		return false;
	}

}
