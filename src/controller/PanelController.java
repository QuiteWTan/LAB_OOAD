package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.database.PanelDetailModel;
import model.database.PanelHeaderModel;
import model.object.PanelHeader;
import model.object.User;
import view.InfluencerHomePage.InfluencerHomeVar;
import view.InfluencerHomePage;
import view.PanelStatistics;
import view.PanelStatistics.PSVar;

public class PanelController {
	
	PanelHeaderModel ph = new PanelHeaderModel();
	PanelDetailModel pd = new PanelDetailModel();
	
	//View Handler
	public void openPopUp(PanelHeader panel) {
		new PanelStatistics(panel);
	}
	
	public void assignData(PSVar var, PanelHeader panel) {
		
		var.titleData.setText(panel.getPanelTitle());
		var.descData.setText(panel.getPanelDescription());
		var.locationData.setText(panel.getLocation());
		var.startTimeData.setText(panel.getStartTime());
		var.endTimeData.setText(panel.getEndTime());
		
	}
	
	public void navigateInfluencerHome(Stage stage, User user) {
		new InfluencerHomePage(stage, user);
	}
	
	public void influencerHomeHandler(InfluencerHomeVar var, Stage stage, User user) {
		
		var.submitButton.setOnMouseClicked(e->{
			
			Integer userId = user.getUserId();
			String panelTitle = var.titleInput.getText().toString();
			String panelDescription = var.descInput.getText().toString();
			String location = var.locationInput.getText().toString();
			Integer startHour = var.startHour.getValue();
			Integer startMinute = var.startMinute.getValue();
			Integer endHour = var.endHour.getValue();
			Integer endMinute = var.endMinute.getValue();
			
			Boolean success = this.insertData(var, userId, panelTitle, panelDescription, location, startHour, startMinute, endHour, endMinute);
			
			if(success) {
				navigateInfluencerHome(stage, user);
			}
			
		});
		
	}
	
	//Validation Logic
	
	private Boolean validateTitle(InfluencerHomeVar var, String title) {
		
		if(title.isEmpty()) {
			var.error.setText("Title must be filled");
			
			return false;
		}
		
		return true;
	}
	
	private Boolean validateDesc(InfluencerHomeVar var, String desc) {
		
		if(desc.isEmpty()) {
			var.error.setText("Description must be filled");
			return false;
		} else if (desc.length() > 250) {
			var.error.setText("Description must be below 250 characters");
			return false;
		}
		
		return true;
	}
	
	private Boolean validateLocation(InfluencerHomeVar var, String location) {
		
		String[] count = location.split("\\s+");
		
		if(count.length < 2) {
			var.error.setText("Location must be 2 words or more");
			return false;
		}
		
		return true;
	}
	
	private Boolean validateTime(InfluencerHomeVar var, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {
		
		if((startHour == 21 && startMinute > 0) || (startHour < 9)) {
			var.error.setText("Start time must between 09:00 - 21:00");
			return false;
		} else if((endHour == 23 && endMinute > 0) || (endHour < 9)){
			var.error.setText("End time must between 09:00 - 23:00");
			return false;
		} else if((startHour > endHour) || (startHour == endHour && startMinute > endMinute) || (startHour == endHour && startMinute == endMinute)) {
			var.error.setText("End time must be above Start time");
			return false;
		}
		
		return true;
		
	}
	
	private Boolean insertData(InfluencerHomeVar var, Integer userId, String panelTitle, String panelDescription, String location, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {
		
		Boolean checkTitle = validateTitle(var, panelTitle);
		Boolean checkDescription = validateDesc(var, panelDescription);
		Boolean checkLocation = validateLocation(var, location);
		Boolean checkTime = validateTime(var, startHour, startMinute, endHour, endMinute);
		
		if(checkTitle && checkDescription && checkLocation && checkTime) {
			
			String startTime = String.format("%d:%d:00", startHour, startMinute);
			String endTime = String.format("%d:%d:00", endHour, endMinute);
			
			addPanel(userId, panelTitle, panelDescription, location, startTime, endTime);
			
			return true;
		}
		
		return false;
	}
	
	//Model function
	public ArrayList<PanelHeader> getAllPanels() {
		
		return ph.getAllPanels();
		
	}
	
	public ArrayList<PanelHeader> getAllPanelByInfluencer(Integer userId) {
		
		return ph.getAllPanelByInfluencer(userId);
	}
	
	public void finishPanel(Integer panelId) {
		
		ph.finishPanel(panelId);
	}
	
	public ArrayList<User> getAllAttendee(Integer panelId) {
		
		return pd.getAllAttendee(panelId);
		
	}
	
	public void addPanel(Integer userId, String panelTitle, String panelDescription, String location, String startTime, String endTime) {
		
		ph.addPanel(userId, panelTitle, panelDescription, location, startTime, endTime);
		
	}
	
}
