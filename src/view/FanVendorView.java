package view;

import java.util.ArrayList;

import controller.PanelController;
import controller.UserController;
import controller.itemController;
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
import model.database.itemModel;
import model.object.Item;
import model.object.PanelHeader;
import model.object.User;
import view.FanHomePage.HomeVar;

public class FanVendorView {
	ArrayList<User> userList = new ArrayList<>();
	ObservableList<User> obsVendor;
	
	UserController uc = new UserController();
	itemController ic= new itemController();
	itemModel im = new itemModel();
	
	Item vendorItem;
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
		public MenuItem menuItemPanel = new MenuItem("View Panels");
		public MenuItem menuItemVendor = new MenuItem("View Vendors");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
		TableView<User> VendorTable = new TableView<User>();
		TableColumn<User, Integer> id_col = new TableColumn<>("userId");
		TableColumn<User, String> username_col= new TableColumn<>("username");
		TableColumn<User, String> email_col= new TableColumn<>("email");
		TableColumn<User, Void> action_col =  new TableColumn<>("action");
		
		TableView<Item> ItemTable = new TableView<Item>();
		TableColumn<Item, Integer> itemId_col = new TableColumn<>("itemId");
		TableColumn<Item, String> itemName_col= new TableColumn<>("itemName");
		TableColumn<Item, Void> itemAction_col =  new TableColumn<>("action");
		
		Label pageTitle = new Label("Vendor Page");
		Label vendorList = new Label("Vendor List");
		Label itemList = new Label("Item List");
		Label error = new Label();
	}
	
private void initialize(FanVendorVar var) {	
		
	    userList.addAll(uc.getAllVendor("Vendor"));
	    obsVendor = FXCollections.observableArrayList(userList);
		
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemPanel);
		var.menu.getItems().add(var.menuItemVendor);
		var.menu.getItems().add(var.menuItemLogOut);
		
		var.username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        var.id_col.setCellValueFactory(new PropertyValueFactory<>("userId"));
        var.email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        var.itemId_col.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        var.itemName_col.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        
        var.action_col.setCellFactory(new Callback<TableColumn<User,Void>, TableCell<User, Void>>() {		
			@Override
			public TableCell<User, Void> call(TableColumn<User, Void> param) {
				
				return new TableCell<User, Void>() {
					private final Button SelectButton = new Button("Select");

					{
						
						SelectButton.setOnMouseClicked(e ->{							
							User data = getTableView().getItems().get(getIndex());
							itemController ic = new itemController();
							itemModel im = new itemModel();
							
							ArrayList<Item> itemList = ic.getAllItemsByVendor(data.getUserId());							
					        ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);
					        
					        var.ItemTable.setItems(itemList1);
					        var.ItemTable.refresh();
						});
						
					}
					
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setGraphic(null);
						} else {
							setGraphic(SelectButton);
						}
					}
				};
			};
			
        });
        
        var.itemAction_col.setCellFactory(new Callback<TableColumn<Item,Void>, TableCell<Item, Void>>() {		
			@Override
			public TableCell<Item, Void> call(TableColumn<Item, Void> param) {
				
				return new TableCell<Item, Void>() {
					private final Button SelectButton = new Button("Select");

					{
						
						SelectButton.setOnMouseClicked(e ->{
							Item itemData = getTableView().getItems().get(getIndex());							
							
							ic.openPopUpItem(itemData, fan);
						});
						
					}
					
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setGraphic(null);
						} else {
							setGraphic(SelectButton);
						}
					}
				};
			};
			
        });
        
        
		
        var.VendorTable.setItems(obsVendor);
		
        var.VendorTable.getColumns().addAll(var.id_col, var.username_col,  var.email_col, var.action_col);
    	var.ItemTable.getColumns().addAll(var.itemId_col, var.itemName_col, var.itemAction_col);
		
		
    	var.contentBox.getChildren().add(var.VendorTable);
    	var.contentBox2.getChildren().add(var.ItemTable);
		
		var.vendorContainer.getChildren().addAll(
				var.pageTitle,
				var.vendorList,
				var.VendorTable,
				var.itemList,
				var.ItemTable
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
		
		var.contentBox.setAlignment(Pos.CENTER);
		var.contentBox.setMaxWidth(450);
		var.contentBox2.setMaxWidth(600);
		
		var.VendorTable.setMaxHeight(150);
		var.ItemTable.setMaxHeight(150);
		
		var.id_col.setPrefWidth(50);
		var.username_col.setPrefWidth(200);        
        var.email_col.setPrefWidth(200);
        
        var.itemId_col.setPrefWidth(200);
    	var.itemName_col.setPrefWidth(200);
        
        
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
