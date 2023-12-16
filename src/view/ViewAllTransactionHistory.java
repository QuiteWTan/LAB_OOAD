package view;

import java.util.ArrayList;

import controller.UserController;
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
import model.object.User;

public class ViewAllTransactionHistory {
	UserController uc = new UserController();
	
	public class AllTransactionHistoryVar {
		
		Stage stage;
		Scene scene;
		BorderPane mainContainer = new BorderPane();
		VBox vendorTable = new VBox(8);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		
		TableView<User> table = new TableView<User>();
		TableColumn<User, Integer> itemName_col = new TableColumn<>("Item Name");
		TableColumn<User, String> price_col= new TableColumn<>("Price");
		TableColumn<User, String> quantity_col= new TableColumn<>("Quantity");
		public MenuItem AdminMenu = new MenuItem("Log Out");
		
		Label pageTitle = new Label("All History Transaction Page");
		
	}
	
	private void initialize(AllTransactionHistoryVar var) {
        
        ArrayList<User> transactionList = new ArrayList<>();
        
        for (User user : transactionList) {
        	var.table.getItems().add(user);
		}
        
        var.itemName_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        var.price_col.setCellValueFactory(new PropertyValueFactory<>("userId"));
        var.quantity_col.setCellValueFactory(new PropertyValueFactory<>("email"));
       
        var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.AdminMenu);
		
        var.mainContainer.setTop(var.menuBar);
        var.mainContainer.setCenter(var.vendorTable);
        var.table.setMaxHeight(450);
        
        var.itemName_col.setPrefWidth(200);
        var.price_col.setPrefWidth(50);
        var.quantity_col.setPrefWidth(50);
    	var.table.getColumns().addAll(var.itemName_col, var.price_col,  var.quantity_col);
    	var.vendorTable.setAlignment(Pos.TOP_CENTER);
    	var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
    	var.vendorTable.getChildren().add(var.pageTitle);
        var.vendorTable.getChildren().add(var.table);
        var.vendorTable.setPadding(new Insets(20, 30, 30, 30));
        
        var.scene = new Scene(var.mainContainer, 800, 600);
        
	}
	
	public ViewAllTransactionHistory(Stage stage) {
		AllTransactionHistoryVar var = new AllTransactionHistoryVar();
		uc.ViewAllTransactionHistory(var, stage);
		initialize(var);
		stage.setScene(var.scene);
		stage.setTitle("View All Vendor Page");
		stage.show();
	}
	

}
