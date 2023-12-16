package view;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.object.User;


public class ViewAllVendor {

	UserController uc = new UserController();
	
	public class ViewVendorVar {
		
		Stage stage;
		Scene scene;
		BorderPane mainContainer = new BorderPane();
		VBox vendorTable = new VBox(8);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		
		TableView<User> table = new TableView<User>();
		TableColumn<User, Integer> id_col = new TableColumn<>("userId");
		TableColumn<User, String> username_col= new TableColumn<>("username");
		TableColumn<User, String> email_col= new TableColumn<>("email");
		TableColumn<User, Void> action_col =  new TableColumn<>("action");
		public MenuItem AdminMenu = new MenuItem("Log Out");
		
		Label pageTitle = new Label("All Vendors Page");
		
	}
	
	private void initialize(ViewVendorVar var) {
        
        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(uc.getAllVendor("Vendor"));
        
        for (User user : userList) {
        	var.table.getItems().add(user);
		}
        
        var.username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        var.id_col.setCellValueFactory(new PropertyValueFactory<>("userId"));
        var.email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        var.action_col.setCellFactory(new Callback<TableColumn<User,Void>, TableCell<User, Void>>() {		
			@Override
			public TableCell<User, Void> call(TableColumn<User, Void> param) {
				
				return new TableCell<User, Void>() {
					
					private final Button DeleteButton = new Button("Delete");
					
					{
						DeleteButton.setOnMouseClicked(e->{
							User data = getTableView().getItems().get(getIndex());
							uc.deleteUserById(data.getUserId());
						});
					}
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setGraphic(null);
						} else {
							setGraphic(DeleteButton);
						}
					}
					
					
				};
			};
        });
        var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.AdminMenu);
		
        var.mainContainer.setTop(var.menuBar);
        var.mainContainer.setCenter(var.vendorTable);
        var.table.setMaxHeight(450);
        
        var.username_col.setPrefWidth(200);
        var.id_col.setPrefWidth(50);
        var.email_col.setPrefWidth(200);
    	var.table.getColumns().addAll(var.id_col, var.username_col,  var.email_col, var.action_col);
    	var.vendorTable.setAlignment(Pos.TOP_CENTER);
    	var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
    	var.vendorTable.getChildren().add(var.pageTitle);
        var.vendorTable.getChildren().add(var.table);
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
