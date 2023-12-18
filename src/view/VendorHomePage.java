package view;


import java.util.ArrayList;

import controller.UserController;
import controller.itemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.database.PanelHeaderModel;
import model.object.Item;
import model.object.User;

public class VendorHomePage {

	UserController uc = new UserController();
	
	
	public class VendorVar {
		Scene homeScene;
		VBox homeContainer = new VBox();
		HBox formBox = new HBox();
		VBox insertFormBox = new VBox();
		VBox updateFormBox = new VBox();
		BorderPane mainContainer = new BorderPane();
		
		Label InsertFormTitle = new Label("Insert Item");
		
		Label itemName_label = new Label("Item Name");
		TextField itemName_tf = new TextField();
		Label itemDescription_label = new Label("Item Label");
		TextField itemDescription_tf = new TextField();
		Label itemPrice_label = new Label("Item Label");
		TextField itemPrice_tf = new TextField();
		Label insertErrorMessage_label = new Label();
		Button insertButton = new Button("Insert");
		
		Label UpdateFormTitle = new Label("Update Item");
		
		Label updateItemName_label = new Label("Item Name");
		TextField updateItemName_tf = new TextField();
		Label updateItemPrice_label = new Label("Item Price");
		TextField updateItemPrice_tf = new TextField();
		Label updateErrorMessage_label = new Label();
		Button updateButton = new Button("Update");;
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");
		
		TableView<Item> ItemTable = new TableView<Item>();
		TableColumn<Item, Integer> itemName_col = new TableColumn<>("Item Name");
		TableColumn<Item, String> itemPrice_col = new TableColumn<>("Item Price");
		TableColumn<Item, String> itemDesc_col = new TableColumn<>("Item Description");
		TableColumn<Item, Void> action_col = new TableColumn<>("Action");
		
		Label pageTitle = new Label("Vendor Home Page");
		Label upcoming = new Label("Item Table");
	}
	

	private void initialize(VendorVar var) {	
		ArrayList<Item> itemList = new ArrayList<>();
//		itemList.addAll(uc.getAllItems(1));
        
        for (Item item : itemList) {
        	var.ItemTable.getItems().add(item);
		}
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemLogOut);
		
		var.itemName_col.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		var.itemPrice_col.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
		var.itemDesc_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		var.action_col.setCellFactory(new Callback<TableColumn<Item,Void>, TableCell<Item, Void>>() {		
			@Override
			public TableCell<Item, Void> call(TableColumn<Item, Void> param) {
				
				return new TableCell<Item, Void>() {
					private final Button SelectButton = new Button("Select");

					{
						
						SelectButton.setOnMouseClicked(e ->{
							Item data = getTableView().getItems().get(getIndex());
					       
					        for (Item item : itemList) {
					        	var.ItemTable.getItems().add(item);
							}
					        
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
//		for (PanelHeader panel : unfinishedPanel) {
//			var.unfinishedTable.getItems().add(panel);
//		}
		
//		var.InsertFormTitle = new Label("Insert Item");
//		
//		var.itemName_label = new Label("Item Name");
//		var.itemName_tf = new TextField();
//		var.itemDescription_label = new Label("Item Label");
//		var.itemDescription_tf = new TextField();
//		var.itemPrice_label = new Label("Item Label");
//		var.itemPrice_tf = new TextField();
//		var.insertErrorMessage_label = new Label();
//		var.insertButton = new Button("Insert");
		
		var.insertFormBox.getChildren().addAll(
				var.InsertFormTitle, var.itemName_label, var.itemName_tf
			);
		
		
		
		var.ItemTable.getColumns().addAll(var.itemName_col, var.itemPrice_col, var.itemDesc_col, var.action_col);
		
		var.homeContainer.getChildren().addAll(
				var.pageTitle,
				var.upcoming,
				var.ItemTable
			);
		
		
		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.homeContainer);
		
		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}
	
	private void style(VendorVar var) {
		
		var.homeContainer.setMaxWidth(750);
		
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		var.InsertFormTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 20px;");
		var.UpdateFormTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 20px;");
		var.formBox.setAlignment(Pos.CENTER);
		var.formBox.setMaxWidth(750);
		var.ItemTable.setMaxHeight(200);
		var.itemName_col.setMinWidth(200);
		var.itemPrice_col.setPrefWidth(200);
		var.itemDesc_col.setPrefWidth(200);
		var.formBox.setPadding(new Insets(20, 30, 30, 30));
	}
	
	public VendorHomePage(Stage stage) {
		VendorVar var = new VendorVar();
		
		initialize(var);
		style(var);
		uc.VendorPageHandler(var,stage);
		
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();
		
	}

}
