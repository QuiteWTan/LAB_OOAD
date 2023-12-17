package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminPage {

	UserController userController = new UserController();
	
	public class AdminVar {
		
		Scene adminScene;
//		BorderPane mainContainer = new BorderPane();
//		public BorderPane dataContainer;
		
//		MenuBar menuBar = new MenuBar();
//		Menu menu = new Menu("Menu");
//		public MenuItem menuItemFan = new MenuItem("Fan Account");
//		public MenuItem menuItemInfluencer = new MenuItem("Influencer Account");
//		public MenuItem menuItemVendor = new MenuItem("VendorAccount");
//		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
	}
	
	private void initialize(AdminVar var) {
		
//		var.menu.getItems().addAll(var.menuItemFan, var.menuItemInfluencer, var.menuItemVendor, var.menuItemLogOut);
//		var.menuBar.getMenus().add(var.menu);
		
//		var.dataContainer = userController.showFanAccount();
//		var.mainContainer.setTop(var.menuBar);
//		var.mainContainer.setCenter(var.dataContainer);
		
		var.adminScene = userController.showFanAccount();
	}
	
	public AdminPage(Stage stage) {
		
		AdminVar var = new AdminVar();
		
		initialize(var);
		
//		userController.adminHandler(var, stage);
		
		stage.setScene(var.adminScene);
		stage.setTitle("Admin Page");
		stage.setResizable(false);
		
	}
	
}
