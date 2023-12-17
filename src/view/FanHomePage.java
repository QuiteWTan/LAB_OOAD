package view;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.PanelController;
import controller.UserController;
import database.Connect;
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
	UserController uc = new UserController();
	PanelController panelController = new PanelController();
	PanelHeaderModel pd = new PanelHeaderModel();
	
	User fan;
	
	public class HomeVar {
		Scene homeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox homeContainer = new VBox(10);
		VBox contentBox = new VBox();
		VBox contentBox2 = new VBox();
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
		TableView<PanelHeader> unfinishedTable = new TableView<PanelHeader>();
		TableColumn<PanelHeader, String> panelID = new TableColumn<>("Panel ID");
		TableColumn<PanelHeader, String> panelTitle = new TableColumn<>("Panel Title");
		TableColumn<PanelHeader, String> panelDescription = new TableColumn<>("Panel Description");
		TableColumn<PanelHeader, String> location = new TableColumn<>("Panel Location");
		TableColumn<PanelHeader, String> startTime = new TableColumn<>("Start Time");
		TableColumn<PanelHeader, String> endTime = new TableColumn<>("End Time");
		TableColumn<PanelHeader, Boolean> isFinished = new TableColumn<>("Panel Status");
		TableColumn<PanelHeader, Void> panelDetail = new TableColumn<>("Detail");		
		
		TableView<PanelHeader> finishedTable = new TableView<PanelHeader>();
		TableColumn<PanelHeader, String> finishedPanelID = new TableColumn<>("Panel ID");
		TableColumn<PanelHeader, String> finishedPanelTitle = new TableColumn<>("Panel Title");
		TableColumn<PanelHeader, String> finishedPanelDescription = new TableColumn<>("Panel Description");
		TableColumn<PanelHeader, String> finishedLocation = new TableColumn<>("Panel Location");
		TableColumn<PanelHeader, String> finishedStartTime = new TableColumn<>("Start Time");
		TableColumn<PanelHeader, String> finishedEndTime = new TableColumn<>("End Time");
		TableColumn<PanelHeader, Boolean> finishedIsFinished = new TableColumn<>("Panel Status");
		
		Label pageTitle = new Label("User Home Page");
		Label upcoming = new Label("Upcoming Panel");
		Label finished = new Label("Finished Panel");
		Label error = new Label();
	}
	
	private void initialize(HomeVar var) {	
		
		unfinishedPanel = pd.getUnfinishedPanels();
		
		finishedPanel = pd.getFinishedPanels();		
		
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemLogOut);
		
		var.panelTitle.setCellValueFactory(new PropertyValueFactory<>("panelTitle"));
		var.panelDescription.setCellValueFactory(new PropertyValueFactory<>("panelDescription"));
		var.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		var.startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		var.endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		var.isFinished.setCellValueFactory(new PropertyValueFactory<>("isFinished"));
		
		var.panelDetail.setCellFactory(new Callback<TableColumn<PanelHeader,Void>, TableCell<PanelHeader,Void>>() {
			
			@Override
			public TableCell<PanelHeader, Void> call(TableColumn<PanelHeader, Void> param) {
				
				return new TableCell<PanelHeader, Void>() {
					
					private final Button viewDetailButton = new Button("View Detail");
					{
						viewDetailButton.setOnMouseClicked(e->{
							User userData = uc.getUserByEmail(fan.getEmail());
							PanelHeader data = getTableView().getItems().get(getIndex());
							panelController.openPopUpFan(data, userData);
						});
					}
					
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setGraphic(null);
						} else {
							setGraphic(viewDetailButton);
						}
					}
				};
			}
		});
		
		var.finishedPanelTitle.setCellValueFactory(new PropertyValueFactory<>("panelTitle"));
		var.finishedPanelDescription.setCellValueFactory(new PropertyValueFactory<>("panelDescription"));
		var.finishedLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
		var.finishedStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		var.finishedEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		var.finishedIsFinished.setCellValueFactory(new PropertyValueFactory<>("isFinished"));
		
		for (PanelHeader panel : unfinishedPanel) {
			var.unfinishedTable.getItems().add(panel);
		}
		
		for (PanelHeader panel : finishedPanel) {
			var.finishedTable.getItems().add(panel);
		}
		
		var.unfinishedTable.getColumns().addAll(var.panelTitle, var.panelDescription, var.location, var.startTime, var.endTime, var.isFinished, var.panelDetail);
		var.finishedTable.getColumns().addAll(var.finishedPanelTitle, var.finishedPanelDescription, var.finishedLocation, var.finishedStartTime, var.finishedEndTime, var.finishedIsFinished);
		
		
		var.contentBox.getChildren().add(var.unfinishedTable);
		var.contentBox2.getChildren().add(var.finishedTable);
		
		var.homeContainer.getChildren().addAll(
				var.pageTitle,
				var.upcoming,
				var.unfinishedTable,
				var.finished,
				var.finishedTable
				);
		
		var.mainBox.getChildren().addAll(var.homeContainer);
		
		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.mainBox);
		
		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}
	
	private void style(HomeVar var) {
		var.mainBox.setAlignment(Pos.CENTER);
		var.homeContainer.setMaxWidth(750);
		
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		
		var.contentBox.setAlignment(Pos.CENTER);
		var.contentBox.setMaxWidth(750);
		var.contentBox2.setMaxWidth(750);
		
		var.unfinishedTable.setMaxHeight(150);
		var.finishedTable.setMaxHeight(150);
		
		var.panelTitle.setMinWidth(200);
		var.panelDescription.setPrefWidth(200);
		var.location.setPrefWidth(200);
		var.startTime.setPrefWidth(200);
		var.endTime.setPrefWidth(200);
		var.isFinished.setPrefWidth(200);
		
		var.finishedPanelTitle.setMinWidth(200);
		var.finishedPanelDescription.setPrefWidth(200);
		var.finishedLocation.setPrefWidth(200);
		var.finishedStartTime.setPrefWidth(200);
		var.finishedEndTime.setPrefWidth(200);
		var.finishedIsFinished.setPrefWidth(200);
		
		var.contentBox.setPadding(new Insets(20, 30, 30, 30));
	}
	
	public FanHomePage(Stage stage, User user) {
		HomeVar var = new HomeVar();
		
		this.fan = user;
		
		initialize(var);
		style(var);
		uc.HomePageHandler(var, stage);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}
	
}
