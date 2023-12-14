package view;

import java.util.ArrayList;

import controller.UserController;
import database.Connect;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.object.PanelHeader;

public class FanHomePage {
	
	UserController uc = new UserController();
	
	
	private void getData() {
		VBox contentBox;
		TableView<PanelHeader> table;
		TableColumn<PanelHeader, String> panelTitle;
		TableColumn<PanelHeader, String> panelDescription;
		TableColumn<PanelHeader, String> location;
		TableColumn<PanelHeader, String> startTime;
		TableColumn<PanelHeader, String> endTime;
		TableColumn<PanelHeader, Boolean> isFinished;
		
		ArrayList<PanelHeader> panelList = new ArrayList<>();
		
		contentBox = new VBox();
		table = new TableView<PanelHeader>();
		panelTitle = new TableColumn<>("Panel Title");
		panelDescription = new TableColumn<>("Panel Description");
		location = new TableColumn<>("Panel Location");
		startTime = new TableColumn<>("Start Time");
		endTime = new TableColumn<>("End Time");
		isFinished = new TableColumn<>("Panel Status");
		table.getColumns().addAll(panelTitle, panelDescription, location, startTime, endTime, isFinished);
		
		
		
	}
	
	
	public class HomeVar {
		Scene homeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox homeContainer = new VBox(10);
		
		Label upcoming = new Label("Upcoming Panel");
		Label finished = new Label("Finished Panel");
	}
	
	private void initialize(HomeVar var) {
		var.homeContainer.getChildren().addAll(
				var.upcoming,
				var.finished
				);
		
		var.mainBox.getChildren().add(var.homeContainer);
		
		var.mainContainer.setCenter(var.mainBox);
		
		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}
	
	private void style(HomeVar var) {
		var.homeContainer.setMaxWidth(400);
	}
	
	public FanHomePage(Stage stage) {
		HomeVar var = new HomeVar();
		
		initialize(var);
		style(var);
		uc.HomePageHandler(var, stage);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}
	
}
