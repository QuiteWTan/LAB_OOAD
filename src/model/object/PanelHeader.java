package model.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class PanelHeader {
	private Integer panelId;
	private String userId;
	private String panelTitle;
	private String panelDescription;
	private String location;
	private String startTime;
	private String endTime;
	private Boolean isFinished;
	
	public PanelHeader(Integer panelId, String userId, String panelTitle, String panelDescription, String location,
			String startTime, String endTime, Boolean isFinished) {
		super();
		this.panelId = panelId;
		this.userId = userId;
		this.panelTitle = panelTitle;
		this.panelDescription = panelDescription;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isFinished = isFinished;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	public String getPanelDescription() {
		return panelDescription;
	}

	public void setPanelDescription(String panelDescription) {
		this.panelDescription = panelDescription;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public static ArrayList<PanelHeader> getAllPanels() throws SQLException {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();
		
		String query = String.format("SELECT * FROM panelHeaders");
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer panelId = rs.getInt("panelId");
				String userId = rs.getString("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");
				
				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime, endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PanelList;
		
	}
	
	public PanelHeader getPanelById(Integer panelId) throws SQLException {
		Connect db = Connect.getInstance();		
		
		String query = String.format("SELECT * FROM panelHeaders WHERE panelId = %d", panelId);
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer panelIds = rs.getInt("panelId");
				String userId = rs.getString("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");
				
				return new PanelHeader(panelIds, userId, panelTitle, panelDescription, location, startTime, endTime, isFinished);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	//Add panel
//	public void addPanel(Integer userId, String panelTitle, String panelDescription, String location, String startTime, String endTime) throws SQLException {
//		
//	}
	
	//Set panel time
	
	public void finishPanel(Integer panelId) throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("UPDATE panelHeaders SET isFinished = 1 WHERE panelId = %d", panelId);
		
		db.execute(query);
	}
	
	public ArrayList<PanelHeader> getUnfinishedPanels() throws SQLException {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 0");
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer panelId = rs.getInt("panelId");
				String userId = rs.getString("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");
				
				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime, endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PanelList;
	}
	
	public ArrayList<PanelHeader> getFinishedPanels() throws SQLException {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 1");
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer panelId = rs.getInt("panelId");
				String userId = rs.getString("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");
				
				PanelList.add(new PanelHeader(panelId, userId, panelTitle, panelDescription, location, startTime, endTime, isFinished));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PanelList;
	}
	
	//Delete panel by influence
	
	public PanelHeader getPanelByInfluencer(Integer userId) {
		Connect db = Connect.getInstance();
		ArrayList<PanelHeader> PanelList = new ArrayList<>();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE userId = %d", userId);
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer panelId = rs.getInt("panelId");
				String userIds = rs.getString("userId");
				String panelTitle = rs.getString("panelTitle");
				String panelDescription = rs.getString("panelDescription");
				String location = rs.getString("location");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				Boolean isFinished = rs.getBoolean("isFinished");
				
				return new PanelHeader(panelId, userIds, panelTitle, panelDescription, location, startTime, endTime, isFinished);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
