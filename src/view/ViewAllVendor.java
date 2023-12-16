package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.object.User;
import view.LoginPage.LoginVar;

public class ViewAllVendor {

	Stage stage;
	Scene scene;
	BorderPane bp;
	VBox vb;
	
	TableView<User> table;
	TableColumn<User, Integer> id_col;
	TableColumn<User, String> username_col, email_col;
	TableColumn<User, Button> action_col;
	
	Label title1, title2, username, password, username_del;
	TextField username_tf, username_tf2;
	PasswordField pass_pf;
	Button btnUpdate, btnDelete;
	UserController uc;
	
	private void getData() {
		ArrayList<User> userList = new ArrayList<>();
		Connect con = Connect.getInstance();
		uc = new UserController();
		
		vb = new VBox();
		table = new TableView<User>();
		username_col = new TableColumn<>("username");
		email_col = new TableColumn<>("email");
		id_col = new TableColumn<>("userId");
		action_col =  new TableColumn<>("action");
		
        table.getColumns().addAll(id_col, username_col,  email_col );
        
        userList.addAll(uc.getAllVendor("vendor"));
        btnUpdate = new Button("UPDATE");
        
        for (User user : userList) {
        	table.getItems().add(user);
		}
        btnDelete = new Button("DELETE");
        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("userId"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setMaxHeight(150);
        username_col.setMinWidth(200);
        id_col.setPrefWidth(200);
        email_col.setPrefWidth(200);
        
        vb.getChildren().add(table);
        vb.setPadding(new Insets(20, 30, 30, 30));
        scene = new Scene(vb, 650, 650);
	}
	
//	private void delete(String query) {
//		Connect con = Connect.getInstance();
//		
//		con.deleteUser(query, username_tf2.getText());
//		
//		username_tf2.clear();
//		
//		uc.navigateLogin(stage);
//	}
	
//	private void handling() {
//		btnUpdate.setOnAction(e->{
//			String query = "UPDATE User SET Password = ? WHERE Username = ?";
//			
//			update(query);
//		});
//		
//		btnDelete.setOnAction(e->{
//			String query = "DELETE FROM User WHERE Username = ?";
//			
//			delete(query);
//		});
//	}
	
	public ViewAllVendor(Stage stage) {
		getData();
//		handling();
		this.stage = stage;
		this.stage.setResizable(false);
		this.stage.setScene(scene);
		this.stage.show();
	}


}
