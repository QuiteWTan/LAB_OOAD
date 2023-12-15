package view;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.UserController;
import database.Connect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.database.PanelHeaderModel;
import model.object.PanelHeader;

public class FanHomePage {
	
	UserController uc = new UserController();
	PanelHeaderModel pd = new PanelHeaderModel();
	
	VBox contentBox;
	TableView<PanelHeader> table;
	TableColumn<PanelHeader, String> panelTitle;
	TableColumn<PanelHeader, String> panelDescription;
	TableColumn<PanelHeader, String> location;
	TableColumn<PanelHeader, String> startTime;
	TableColumn<PanelHeader, String> endTime;
	TableColumn<PanelHeader, Boolean> isFinished;
	
	private void getData() {
		
		
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
		
		try {
			panelList = pd.getAllPanels();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (PanelHeader panel : panelList) {
			table.getItems().add(panel);
		}
		
		panelTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		panelDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		location.setCellValueFactory(new PropertyValueFactory<>("location"));
		startTime.setCellValueFactory(new PropertyValueFactory<>("start"));
		endTime.setCellValueFactory(new PropertyValueFactory<>("end"));
		isFinished.setCellValueFactory(new PropertyValueFactory<>("finished"));
		
		table.setMaxHeight(150);
		panelTitle.setMinWidth(200);
		panelDescription.setPrefWidth(200);
		location.setPrefWidth(200);
		startTime.setPrefWidth(200);
		endTime.setPrefWidth(200);
		isFinished.setPrefWidth(200);
		
		contentBox.getChildren().add(table);
		contentBox.setPadding(new Insets(20, 30, 30, 30));
		
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
		getData();
		
		var.homeContainer.getChildren().addAll(
				var.upcoming,
				var.finished
				);
		
		var.mainBox.getChildren().add(var.homeContainer);
		
		var.mainContainer.setTop(contentBox);
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
