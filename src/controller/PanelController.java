package controller;

import java.util.ArrayList;

import model.database.PanelDetailModel;
import model.database.PanelHeaderModel;
import model.object.PanelHeader;
import model.object.User;
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
	
}
