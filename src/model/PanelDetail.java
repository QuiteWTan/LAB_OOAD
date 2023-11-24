package model;

import java.sql.SQLException;

import database.Connect;

public class PanelDetail {
	private Integer panelId;
	private Integer userId;
	
	public PanelDetail(Integer panelId, Integer userId) {
		super();
		this.panelId = panelId;
		this.userId = userId;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void addAttendee(Integer panelId, Integer userId) throws SQLException {
        Connect db = Connect.getInstance();

        String query = String.format("INSERT INTO `PanelDetails` (panelId, userId) VALUES (%d, %d)", panelId, userId);

        db.execute(query);        
    }
    
    public void getAllAttendee(Integer panelId) throws SQLException {
    	Connect db = Connect.getInstance();
    	
    	String query = String.format("SELECT us.* FROM users us JOIN panelDetails pd ON us.userId = pd.userId WHERE pd.panelId = %d", panelId);
    	
    	db.execute(query);
    }
    
    public void deletePanel(Integer panelId) throws SQLException {
        Connect db = Connect.getInstance();
        
        String query = String.format("DELETE FROM `PanelDetails` WHERE panelId = %d", panelId);
        
        db.execute(query);        
    }
    
    //delete fan attendance
    
}
	
	

