package model.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class PanelHeader {
	private Integer panelId;
	private Integer userId;
	private String panelTitle;
	private String panelDescription;
	private String location;
	private String startTime;
	private String endTime;
	private Boolean isFinished;
	
	public PanelHeader(Integer panelId, Integer userId, String panelTitle, String panelDescription, String location,
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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
	
}
