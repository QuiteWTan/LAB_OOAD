package model;

import java.sql.SQLException;

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
	
	public void getAllPanels() throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("SELECT * FROM panelHeaders");
		
		db.execute(query);
	}
	
	public void getPanelById(Integer panelId) throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE panelId = %d", panelId);
		
		db.execute(query);
	}
	
	//Create panel
	
	//Set panel time
	
	public void finishPanel(Integer panelId) throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("UPDATE panelHeaders SET isFinished = 1 WHERE panelId = %d", panelId);
		
		db.execute(query);
	}
	
	public void getUnfinishedPanels() throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 0");
		
		db.execute(query);
	}
	
	public void getFinishedPanels() throws SQLException {
		Connect db = Connect.getInstance();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE isFinished = 1");
		
		db.execute(query);
	}
	
	//Delete panel by influence
	
	public void getPanelByInfluencer(Integer userId) {
		Connect db = Connect.getInstance();
		
		String query = String.format("SELECT * FROM panelHeaders WHERE userId = %d", userId);
		
		db.execute(query);
	}
	
	
	
}
