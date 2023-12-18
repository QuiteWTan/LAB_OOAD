package view;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.PanelController;
import controller.UserController;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.database.PanelHeaderModel;
import model.object.PanelHeader;
import model.object.User;

public class FanHomePage {
	
	ArrayList<PanelHeader> unfinishedPanel = new ArrayList<>();
	ArrayList<PanelHeader> finishedPanel = new ArrayList<>();
	
	ObservableList<PanelHeader> obsUnfinishedPanel;
	ObservableList<PanelHeader> obsFinishedPanel;
	
	UserController uc = new UserController();
	PanelController panelController = new PanelController();
	PanelHeaderModel pd = new PanelHeaderModel();
	
	User fan;
	
	public class HomeVar {
		Scene homeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox homeContainer = new VBox(10);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemPanel = new MenuItem("View Panels");
		public MenuItem menuItemVendor = new MenuItem("View Vendors");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
		Label pageTitle = new Label("User Home Page");
		Label welcome = new Label("Welcome, select one of the menus to navigate.");
		Label error = new Label();
	}
	
	private void initialize(HomeVar var) {	
	
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemPanel);
		var.menu.getItems().add(var.menuItemVendor);
		var.menu.getItems().add(var.menuItemLogOut);

		var.homeContainer.getChildren().addAll(
				var.pageTitle,
				var.welcome
				);
		
		var.mainBox.getChildren().addAll(var.homeContainer);
		
		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.mainBox);
		
		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}
	
	private void style(HomeVar var) {
		var.mainBox.setAlignment(Pos.CENTER);
		var.homeContainer.setMaxWidth(600);
		
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
	}
	
	public FanHomePage(Stage stage, User user) {
		HomeVar var = new HomeVar();
		
		this.fan = user;
		
		initialize(var);
		style(var);
		uc.HomePageHandler(var, stage, user);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}
	
}
