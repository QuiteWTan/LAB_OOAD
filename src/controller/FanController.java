package controller;

import java.util.ArrayList;

import javafx.scene.Scene;
import model.PanelHeader;
import model.User;
import view.ViewAccount;
import view.ViewPanelFan;

public class FanController {
	
	private User currUser;
	private ArrayList<PanelHeader> panels;
	
	public FanController() {
//		this.currUser = new User(1, "admin", "admin@gmail.com", "admin", "Admin");
		this.currUser = new User(1, "fan", "fan@gmail.com", "fan", "Fan");
		this.panels = new ArrayList<>();
	}
	
	
	public Scene showAllPanel() {
		this.panels = PanelHeader.getAllPanels();
		
		String titleString = "Panels";
		ViewPanelFan view = new ViewPanelFan(this.panels, titleString);
		
        
        return view.getViewScene();
	}


	public static void showDetails(Integer panelId, Integer userId) {
		System.out.println(panelId);
		System.out.println(userId);
	}
	
	
	

}
