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
import view.FanPanelView.FanPanelVar;

public class FanVendorView {
	ArrayList<PanelHeader> vendorList = new ArrayList<>();	
	ObservableList<PanelHeader> obsVendor;
	
	UserController uc = new UserController();
	PanelController panelController = new PanelController();
	PanelHeaderModel pd = new PanelHeaderModel();
	
	User fan;
	
	public class FanVendorVar {
		Scene homeScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox vendorContainer = new VBox(10);
		VBox contentBox = new VBox();
		VBox contentBox2 = new VBox();
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemHome = new MenuItem("Home");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
//		TableView<PanelHeader> vendorList = new TableView<PanelHeader>();
//		TableColumn<Vendor, String> vendorID = new TableColumn<>("Vendor ID");		
//		TableColumn<Vendor, Void> vendorDetail = new TableColumn<>("Detail");	
		
		Label pageTitle = new Label("Vendor Page");
		Label vendorList = new Label("Vendor List");		
		Label error = new Label();
	}
	
private void initialize(FanVendorVar var) {	
		
//		vendorList = 
//		obsVendor = FXCollections.observableArrayList(vendorList;
		
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemHome);
		var.menu.getItems().add(var.menuItemLogOut);
		
//		var.vendorID.setCellValueFactory(new PropertyValueFactory<>("vendorId"));
//		
//		var.vendorDetail.setCellFactory(new Callback<TableColumn<PanelHeader,Void>, TableCell<PanelHeader,Void>>() {
//			
//			@Override
//			public TableCell<PanelHeader, Void> call(TableColumn<PanelHeader, Void> param) {
//				
//				return new TableCell<PanelHeader, Void>() {
//					
//					private final Button viewDetailButton = new Button("View Detail");
//					{
//						viewDetailButton.setOnMouseClicked(e->{
//							User userData = uc.getUserByEmail(fan.getEmail());
//							PanelHeader data = getTableView().getItems().get(getIndex());
//							panelController.openPopUpFan(data, userData);
//						});
//					}
//					
//					@Override
//					protected void updateItem(Void item, boolean empty) {
//						super.updateItem(item, empty);
//						if(empty) {
//							setGraphic(null);
//						} else {
//							setGraphic(viewDetailButton);
//						}
//					}
//				};
//			}
//		});
		
//		for (PanelHeader panel : unfinishedPanel) {
//			var.unfinishedTable.getItems().add(panel);
//		}
		
//		var.unfinishedTable.getColumns().addAll(var.panelID, var.isFinished, var.panelDetail);
		
		
//		var.contentBox.getChildren().add(var.vendorList);
		
		var.vendorContainer.getChildren().addAll(
				var.pageTitle,
				var.vendorList
				);
		
		var.mainBox.getChildren().addAll(var.vendorContainer);
		
		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.mainBox);
		
		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}

	private void style(FanVendorVar var) {
		var.mainBox.setAlignment(Pos.CENTER);
		var.vendorContainer.setMaxWidth(600);
		
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
	}
	
	public FanVendorView(Stage stage, User user) {
		FanVendorVar var = new FanVendorVar();
		
		this.fan = user;
		
		initialize(var);
		style(var);
		uc.FanVendorHandler(var, stage, user);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}
}
