package view;

import controller.PanelController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.object.PanelHeader;
import model.object.User;

public class PanelDetailView {

	PanelController panelController = new PanelController();

	User fan;
	PanelHeader panel;

	public class FanPanelDetail {

		// Scene
		public Stage stage;
		Scene PanelDetailScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(16);
		VBox dataContainer = new VBox(4);
		HBox timeContainer = new HBox(50);
		VBox startTimeContainer = new VBox(4);
		VBox endTimeContainer = new VBox(4);

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

		public Button attendButton = new Button("Attend Panel");

	}

	private void initialize(FanPanelDetail var) {

		// Body
		var.startTimeContainer.getChildren().addAll(var.startTime, var.startTimeData);
		var.endTimeContainer.getChildren().addAll(var.endTime, var.endTimeData);
		var.timeContainer.getChildren().addAll(var.startTimeContainer, var.endTimeContainer);

		var.dataContainer.getChildren().addAll(
				var.title, var.titleData, 
				var.desc, var.descData, 
				var.location, var.locationData, 
				var.timeContainer, var.attendButton
				);

		// Setup

		var.mainBox.getChildren().addAll(var.dataContainer);

		var.mainContainer.setCenter(var.mainBox);

		var.PanelDetailScene = new Scene(var.mainContainer, 600, 450);

		var.stage = new Stage();
		var.stage.initModality(Modality.APPLICATION_MODAL);
	}

	private void style(FanPanelDetail var) {

		var.mainBox.setAlignment(Pos.CENTER);
		var.dataContainer.setMaxWidth(550);

	}

	public PanelDetailView(PanelHeader panelHeader, User user) {

		FanPanelDetail var = new FanPanelDetail();

		this.panel = panelHeader;
		this.fan = user;

		initialize(var);
		style(var);

		panelController.getPanelDetail(var, panelHeader);
		panelController.FanPanelDetailHandler(var, user, panelHeader);

		var.stage.setScene(var.PanelDetailScene);
		var.stage.setTitle("Panel Detail");
		var.stage.setResizable(false);
		var.stage.show();

	}

}
