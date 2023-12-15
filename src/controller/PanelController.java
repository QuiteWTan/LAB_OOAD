package controller;

import java.util.ArrayList;

import model.database.PanelDetailModel;
import model.database.PanelHeaderModel;
import model.object.PanelHeader;

public class PanelController {
	
	PanelHeaderModel ph = new PanelHeaderModel();
	PanelDetailModel pd = new PanelDetailModel();
	
	public ArrayList<PanelHeader> getAllPanels(){
		
		return ph.getAllPanels();
		
	}
	
	public ArrayList<PanelHeader> getAllPanelByInfluencer(Integer userId) {
		
		return ph.getAllPanelByInfluencer(userId);
	}
	
}
