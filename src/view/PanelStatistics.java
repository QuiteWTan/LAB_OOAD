package view;

import java.util.ArrayList;

import controller.PanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.object.PanelHeader;
import model.object.User;

public class PanelStatistics {

	PanelController panelController = new PanelController();

	PanelHeader panel;

	ArrayList<User> attendees;
	ObservableList<User> obsAttendees;

	public class PSVar {

		// Scene
		Stage stage;
		Scene PanelStatisticScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(16);
		VBox dataContainer = new VBox(4);
		HBox timeContainer = new HBox(50);
		VBox startTimeContainer = new VBox(4);
		VBox endTimeContainer = new VBox(4);
		VBox tableContainer = new VBox();

		// Table
		TableView<User> table = new TableView<User>();
		TableColumn<User, String> idCol = new TableColumn<>("Id");
		TableColumn<User, String> usernameCol = new TableColumn<>("Username");
		TableColumn<User, String> emailCol = new TableColumn<>("Email");

		// Label
		Label title = new Label("Title:");
		public Label titleData = new Label();
		Label desc = new Label("Description:");
		public Label descData = new Label();
		Label location = new Label("Location:");
		public Label locationData = new Label();
		Label startTime = new Label("Start time:");
		public Label startTimeData = new Label();
		Label endTime = new Label("End time:");
		public Label endTimeData = new Label();

	}

	private void initialize(PSVar var) {

		attendees = panelController.getAllAttendee(panel.getPanelId());
		obsAttendees = FXCollections.observableArrayList(attendees);

		// Table
		var.idCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
		var.usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		var.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		var.table.setItems(obsAttendees);

		var.table.getColumns().addAll(var.idCol, var.usernameCol, var.emailCol);

		// Body
		var.startTimeContainer.getChildren().addAll(var.startTime, var.startTimeData);
		var.endTimeContainer.getChildren().addAll(var.endTime, var.endTimeData);
		var.timeContainer.getChildren().addAll(var.startTimeContainer, var.endTimeContainer);

		var.dataContainer.getChildren().addAll(
				var.title, var.titleData, 
				var.desc, var.descData, 
				var.location, var.locationData, 
				var.timeContainer
				);

		var.tableContainer.getChildren().add(var.table);

		// Setup

		var.mainBox.getChildren().addAll(var.dataContainer, var.tableContainer);

		var.mainContainer.setCenter(var.mainBox);

		var.PanelStatisticScene = new Scene(var.mainContainer, 600, 450);

		var.stage = new Stage();
		var.stage.initModality(Modality.APPLICATION_MODAL);
	}

	private void style(PSVar var) {

		var.mainBox.setAlignment(Pos.CENTER);
		var.dataContainer.setMaxWidth(550);

		var.tableContainer.setAlignment(Pos.CENTER);
		var.table.setMaxWidth(550);
		var.table.setMinHeight(150);
		var.idCol.setPrefWidth(50);
		var.usernameCol.setPrefWidth(250);
		var.emailCol.setPrefWidth(250);

		var.title.setStyle("-fx-font-weight: bold;");
		var.desc.setStyle("-fx-font-weight: bold;");
		var.location.setStyle("-fx-font-weight: bold;");
		var.startTime.setStyle("-fx-font-weight: bold;");
		var.endTime.setStyle("-fx-font-weight: bold;");

	}

	public PanelStatistics(PanelHeader panelHeader) {

		PSVar var = new PSVar();

		this.panel = panelHeader;

		initialize(var);
		style(var);

		panelController.assignData(var, panelHeader);

		var.stage.setScene(var.PanelStatisticScene);
		var.stage.setTitle("Panel Statistics");
		var.stage.setResizable(false);
		var.stage.show();

	}

}
