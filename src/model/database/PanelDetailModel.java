package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.object.User;

public class PanelDetailModel {

	public void addAttendee(Integer panelId, Integer userId) throws SQLException {
        Connect db = Connect.getInstance();

        String query = String.format("INSERT INTO `PanelDetails` (panelId, userId) VALUES (%d, %d)", panelId, userId);

        db.execute(query);        
    }
    
    public ArrayList<User> getAllAttendee(Integer panelId) throws SQLException {
    	ArrayList<User> listUser = new ArrayList<>();
    	Connect db = Connect.getInstance();
    	
    	String query = String.format("SELECT us.* FROM users us JOIN panelDetails pd ON us.userId = pd.userId WHERE pd.panelId = %d", panelId);
    	ResultSet rs = db.selectData(query);
    	
    	try {
			while(rs.next()) {
				Integer userId = rs.getInt("userId");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role"); 
				
				listUser.add(new User(userId, username, email, password, role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return listUser;	
    }
    
    public void deletePanel(Integer panelId) throws SQLException {
        Connect db = Connect.getInstance();
        
        String query = String.format("DELETE FROM `PanelDetails` WHERE panelId = %d", panelId);
        
        db.execute(query);        
    }
    
    public void deleteAttendee(Integer userId) throws SQLException {
    	Connect db = Connect.getInstance();
    	
    	String query = String.format("DELETE FROM `PanelDetails` WHERE userId = %d ", userId);
    	
    	db.execute(query);
    }
	
}
