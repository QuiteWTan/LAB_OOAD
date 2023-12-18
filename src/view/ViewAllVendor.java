package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.object.User;
import model.object.Item;
import model.database.itemModel;
import controller.itemController;

public class ViewAllVendor {

	UserController uc = new UserController();
	User Fan;
	
	public class ViewVendorVar {
		
		Stage stage;
		Scene scene;
		BorderPane mainContainer = new BorderPane();
		VBox vendorTable = new VBox(8);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem AdminMenu = new MenuItem("Log Out");
		
		Label pageTitle = new Label("All Vendors Page");
		
		TableView<User> VendorTable = new TableView<User>();
		TableColumn<User, Integer> id_col = new TableColumn<>("userId");
		TableColumn<User, String> username_col= new TableColumn<>("username");
		TableColumn<User, String> email_col= new TableColumn<>("email");
		TableColumn<User, Void> action_col =  new TableColumn<>("action");
		
		TableView<Item> ItemTable = new TableView<Item>();
		TableColumn<Item, Integer> itemId_col = new TableColumn<>("itemId");
		TableColumn<Item, String> itemName_col= new TableColumn<>("itemName");
		TableColumn<Item, String> itemDesc_col = new TableColumn<>("itemDescription");
		TableColumn<Item, String> price_col = new TableColumn<>("price");				
	}
	
	private void initialize(ViewVendorVar var) {
        
        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(uc.getAllVendor("Vendor"));
        
        for (User user : userList) {
        	var.VendorTable.getItems().add(user);
		}
        
        var.username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        var.id_col.setCellValueFactory(new PropertyValueFactory<>("userId"));
        var.email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        var.itemId_col.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        var.itemName_col.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        var.itemDesc_col.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        var.price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        var.action_col.setCellFactory(new Callback<TableColumn<User,Void>, TableCell<User, Void>>() {		
			@Override
			public TableCell<User, Void> call(TableColumn<User, Void> param) {
				
				return new TableCell<User, Void>() {
					private final Button SelectButton = new Button("Select");

					{
						
						SelectButton.setOnMouseClicked(e ->{
							User data = getTableView().getItems().get(getIndex());
							itemController ic = new itemController();
							
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
        
        
        
        var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.AdminMenu);
		
        
        var.mainContainer.setCenter(var.vendorTable);
        var.VendorTable.setMaxHeight(450);
        
        var.username_col.setPrefWidth(200);
        var.id_col.setPrefWidth(50);
        var.email_col.setPrefWidth(200);
    	var.VendorTable.getColumns().addAll(var.id_col, var.username_col,  var.email_col, var.action_col);
    	var.ItemTable.getColumns().addAll(var.itemId_col, var.itemName_col, var.itemDesc_col, var.price_col);
    	
    	var.itemId_col.setPrefWidth(200);
    	var.itemName_col.setPrefWidth(200);
    	var.itemDesc_col.setPrefWidth(200);
        var.price_col.setPrefWidth(200);
        
    	var.vendorTable.setAlignment(Pos.TOP_CENTER);
    	var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
    	var.vendorTable.getChildren().add(var.pageTitle);
        var.vendorTable.getChildren().addAll(var.VendorTable, var.ItemTable);
        var.vendorTable.setPadding(new Insets(20, 30, 30, 30));
        
        var.scene = new Scene(var.mainContainer, 800, 600);
	}
	

	
	public ViewAllVendor(Stage stage) {
		ViewVendorVar var = new ViewVendorVar();
		uc.ViewAllVendorPageHandler(var, stage);
		initialize(var);
		stage.setScene(var.scene);
		stage.setTitle("View All Vendor Page");
		stage.show();
	}


}
