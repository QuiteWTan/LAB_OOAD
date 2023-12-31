package view;

import java.util.ArrayList;

import controller.PanelController;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.object.PanelHeader;
import model.object.User;

public class InfluencerHomePage {

	PanelController panelController = new PanelController();
	UserController userController = new UserController();

	User influencer;
	ArrayList<PanelHeader> panelList;
	ObservableList<PanelHeader> obsPanelList;

	public class InfluencerHomeVar {
		// Scene
		Scene influencerHomeScene;
		BorderPane mainContainer = new BorderPane();
		ScrollPane scrollContainer = new ScrollPane();
		VBox mainBox = new VBox(16);
		VBox tableContainer = new VBox();
		VBox formContainer = new VBox(8);
		HBox startContainer = new HBox(8);
		HBox endContainer = new HBox(8);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");

		// Table
		TableView<PanelHeader> table = new TableView<PanelHeader>();
		TableColumn<PanelHeader, Integer> idCol = new TableColumn<>("Id");
		TableColumn<PanelHeader, String> titleCol = new TableColumn<>("Title");
		TableColumn<PanelHeader, String> statusCol = new TableColumn<>("isFinished");
		TableColumn<PanelHeader, Void> viewCol = new TableColumn<>("Detail");
		TableColumn<PanelHeader, Void> finishCol = new TableColumn<>("Finish");

		// Label
		Label pageTitle = new Label("Home Page");
		Label titleLabel = new Label("Panel Title");
		Label descLabel = new Label("Panel Description");
		Label locationLabel = new Label("Location");
		Label startTimeLabel = new Label("Start Time");
		Label endTimeLabel = new Label("End Time");
		public Label error = new Label();

		Label addPanelTitle = new Label("Add Panel");

		// Input Field
		public TextField titleInput = new TextField();
		public TextField descInput = new TextField();
		public TextField locationInput = new TextField();

		public Spinner<Integer> startHour = new Spinner<>(9, 21, 9);
		public Spinner<Integer> startMinute = new Spinner<>(0, 59, 0);
		public Spinner<Integer> endHour = new Spinner<>(9, 23, 9);
		public Spinner<Integer> endMinute = new Spinner<>(0, 59, 0);

		// Button
		public Button submitButton = new Button("Add");
	}

	private void initialize(InfluencerHomeVar var) {

		panelList = panelController.getAllPanelByInfluencer(influencer.getUserId());
		obsPanelList = FXCollections.observableArrayList(panelList);

		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemLogOut);

		// Table

		var.idCol.setCellValueFactory(new PropertyValueFactory<>("panelId"));
		var.titleCol.setCellValueFactory(new PropertyValueFactory<>("panelTitle"));
		var.statusCol.setCellValueFactory(new PropertyValueFactory<>("isFinished"));

		var.viewCol.setCellFactory(new Callback<TableColumn<PanelHeader, Void>, TableCell<PanelHeader, Void>>() {

			@Override
			public TableCell<PanelHeader, Void> call(TableColumn<PanelHeader, Void> param) {

				return new TableCell<PanelHeader, Void>() {

					private final Button Fa = new Button("Detail");
					{
						Fa.setOnMouseClicked(e -> {
							PanelHeader data = getTableView().getItems().get(getIndex());
							panelController.openPopUp(data);
						});
					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(Fa);
						}
					}
				};
			}
		});

		var.finishCol.setCellFactory(new Callback<TableColumn<PanelHeader, Void>, TableCell<PanelHeader, Void>>() {

			@Override
			public TableCell<PanelHeader, Void> call(TableColumn<PanelHeader, Void> param) {

				return new TableCell<PanelHeader, Void>() {

					private final Button finishPanelButton = new Button("Finish");
					{
						finishPanelButton.setOnMouseClicked(e -> {
							PanelHeader data = getTableView().getItems().get(getIndex());
							panelController.finishPanel(data.getPanelId());
							panelList = panelController.getAllPanelByInfluencer(influencer.getUserId());
							obsPanelList = FXCollections.observableArrayList(panelList);
							var.table.setItems(obsPanelList);
							var.table.refresh();
						});
					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(finishPanelButton);
						}
					}
				};
			}
		});

		var.table.setItems(obsPanelList);

		var.table.getColumns().addAll(var.idCol, var.titleCol, var.statusCol, var.viewCol, var.finishCol);

		// Body
		var.tableContainer.getChildren().add(var.table);

		var.startContainer.getChildren().addAll(var.startHour, var.startMinute);

		var.endContainer.getChildren().addAll(var.endHour, var.endMinute);

		var.formContainer.getChildren().addAll(
				var.titleLabel, var.titleInput, 
				var.descLabel, var.descInput, 
				var.locationLabel, var.locationInput, 
				var.startTimeLabel, var.startContainer, 
				var.endTimeLabel, var.endContainer, 
				var.submitButton, var.error
				);

		// Setup

		var.mainBox.getChildren().addAll(var.pageTitle, var.tableContainer, var.addPanelTitle, var.formContainer);

		var.scrollContainer.setContent(var.mainBox);

		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.scrollContainer);

		var.influencerHomeScene = new Scene(var.mainContainer, 800, 600);

	}

	private void style(InfluencerHomeVar var) {

		var.mainBox.setAlignment(Pos.CENTER);
		var.formContainer.setMaxWidth(400);
		var.scrollContainer.setFitToWidth(true);

		var.tableContainer.setAlignment(Pos.CENTER);
		var.table.setMaxWidth(750);
		var.table.setMinHeight(400);
		var.idCol.setPrefWidth(50);
		var.titleCol.setPrefWidth(250);
		var.statusCol.setPrefWidth(250);
		var.viewCol.setPrefWidth(100);
		var.finishCol.setPrefWidth(100);

		var.error.setStyle("-fx-text-fill: RED");
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		var.addPanelTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 20px;");
	}

	public InfluencerHomePage(Stage stage, User user) {
		InfluencerHomeVar var = new InfluencerHomeVar();

		this.influencer = user;

		userController.InfluencerPageHandler(var, stage);
		initialize(var);
		style(var);
		panelController.influencerHomeHandler(var, stage, user);

		stage.setScene(var.influencerHomeScene);
		stage.setTitle("Home Page");
		stage.setResizable(false);

	}

}
