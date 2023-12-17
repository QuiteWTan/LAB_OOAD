package view;

import java.util.ArrayList;

import controller.PanelController;
import controller.UserController;
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
import view.FanHomePage.HomeVar;

public class FanPanelView {
	ArrayList<PanelHeader> unfinishedPanel = new ArrayList<>();
	ArrayList<PanelHeader> finishedPanel = new ArrayList<>();
	
	ObservableList<PanelHeader> obsUnfinishedPanel;
	ObservableList<PanelHeader> obsFinishedPanel;
	
	UserController uc = new UserController();
	PanelController panelController = new PanelController();
	PanelHeaderModel pd = new PanelHeaderModel();
	
	User fan;
	
	public class FanPanelVar {
		Scene homeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox homeContainer = new VBox(10);
		VBox contentBox = new VBox();
		VBox contentBox2 = new VBox();
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemHome = new MenuItem("Home");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
		TableView<PanelHeader> unfinishedTable = new TableView<PanelHeader>();
		TableColumn<PanelHeader, String> panelID = new TableColumn<>("Panel ID");
		TableColumn<PanelHeader, Boolean> isFinished = new TableColumn<>("Panel Status");
		TableColumn<PanelHeader, Void> panelDetail = new TableColumn<>("Detail");		
		
		TableView<PanelHeader> finishedTable = new TableView<PanelHeader>();
		TableColumn<PanelHeader, String> finishedPanelID = new TableColumn<>("Panel ID");
		TableColumn<PanelHeader, Boolean> finishedIsFinished = new TableColumn<>("Panel Status");
		
		Label pageTitle = new Label("All Panels");
		Label upcoming = new Label("Upcoming Panel");
		Label finished = new Label("Finished Panel");
		Label error = new Label();
	}
	
private void initialize(FanPanelVar var) {	
		
		unfinishedPanel = pd.getUnfinishedPanels();
		obsUnfinishedPanel = FXCollections.observableArrayList(unfinishedPanel);
		
		finishedPanel = pd.getFinishedPanels();
		obsFinishedPanel = FXCollections.observableArrayList(finishedPanel);
		
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemHome);
		var.menu.getItems().add(var.menuItemLogOut);
		
		var.panelID.setCellValueFactory(new PropertyValueFactory<>("panelId"));
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

		var.finishedPanelID.setCellValueFactory(new PropertyValueFactory<>("panelId"));
		var.finishedIsFinished.setCellValueFactory(new PropertyValueFactory<>("isFinished"));
		
		for (PanelHeader panel : unfinishedPanel) {
			var.unfinishedTable.getItems().add(panel);
		}
		
		for (PanelHeader panel : finishedPanel) {
			var.finishedTable.getItems().add(panel);
		}
		
		var.unfinishedTable.getColumns().addAll(var.panelID, var.isFinished, var.panelDetail);
		var.finishedTable.getColumns().addAll(var.finishedPanelID, var.finishedIsFinished);
		
		
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

	private void style(FanPanelVar var) {
		var.mainBox.setAlignment(Pos.CENTER);
		var.homeContainer.setMaxWidth(600);
		
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		
		var.contentBox.setAlignment(Pos.CENTER);
		var.contentBox.setMaxWidth(600);
		var.contentBox2.setMaxWidth(600);
		
		var.unfinishedTable.setMaxHeight(150);
		var.finishedTable.setMaxHeight(150);
		
		var.panelID.setPrefWidth(200);
		var.isFinished.setPrefWidth(200);
		var.panelDetail.setPrefWidth(200);
		
		var.finishedPanelID.setPrefWidth(300);
		var.finishedIsFinished.setPrefWidth(300);
		
		var.contentBox.setPadding(new Insets(20, 30, 30, 30));
	}
	
	public FanPanelView(Stage stage, User user) {
		FanPanelVar var = new FanPanelVar();
		
		this.fan = user;
		
		initialize(var);
		style(var);
		uc.FanPanelHandler(var, stage, user);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}
}
