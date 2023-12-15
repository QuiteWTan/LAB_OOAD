package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.object.PanelHeader;

public class InfluencerHomePage {

	public class InfluencerHomeVar {
		// Scene
		Scene influencerHomeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(2);
		VBox tableContainer = new VBox();
		VBox formContainer = new VBox(13);

//		MenuBar menuBar = new MenuBar();
//		Menu menu = new Menu("Menu");
//		public MenuItem menuItemLogout = new MenuItem("Login");

		//Table
		TableView<PanelHeader> table = new TableView<PanelHeader>();
		TableColumn<PanelHeader, Integer> idCol = new TableColumn<>("Id");
		TableColumn<PanelHeader, String> titleCol = new TableColumn<>("Title");
		TableColumn<PanelHeader, String> statusCol = new TableColumn<>("Status");
		
		// Label
		Label pageTitle = new Label("Home Page");
		Label titleLabel = new Label("Panel Title");
		Label descLabel = new Label("Panel Description");
		Label locationLabel = new Label("Location");
		Label startTimeLabel = new Label("Start Time");
		Label endTimeLabel = new Label("End Time");
		Label error = new Label();

		// Input Field
		public TextField titleInput = new TextField();
		public TextField descInput = new TextField();
		public TextField locationInput = new TextField();
		public TextField startTimeInput = new TextField();
		public TextField endTimeInput = new TextField();

		// Button
		public Button submitButton = new Button("Register");
	}
	
	public void initialize(InfluencerHomeVar var) {
		
		//Table
		var.table.getColumns().addAll(var.idCol, var.titleCol, var.statusCol);
		
		
		//Body
		var.tableContainer.getChildren().add(var.table);
		
		var.formContainer.getChildren().addAll(
				var.titleLabel, var.titleInput, 
				var.descLabel, var.descInput, 
				var.locationLabel, var.locationInput, 
				var.startTimeLabel, var.startTimeInput, 
				var.endTimeLabel, var.endTimeInput, 
				var.submitButton, var.error
				);
		
		//Setup
		
		var.mainBox.getChildren().addAll(var.tableContainer, var.formContainer);
		
		var.mainContainer.setCenter(var.mainBox);
		
		var.influencerHomeScene = new Scene(var.mainContainer, 800, 600);
		
	}
	
	private void style(InfluencerHomeVar var) {

		var.mainBox.setAlignment(Pos.CENTER);
		var.formContainer.setMaxWidth(400);
		
		var.tableContainer.setAlignment(Pos.CENTER);
		var.table.setMaxWidth(750);
		var.idCol.setPrefWidth(50);
		var.titleCol.setPrefWidth(350);
		var.statusCol.setPrefWidth(350);
		
		var.error.setStyle("-fx-text-fill: RED");
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
	}

	public InfluencerHomePage(Stage stage) {
		InfluencerHomeVar var = new InfluencerHomeVar();
		
		initialize(var);
		style(var);
		
		stage.setScene(var.influencerHomeScene);
		stage.setTitle("Login");
		stage.setResizable(false);
		
	}

}
