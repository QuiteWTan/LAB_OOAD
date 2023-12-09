package view;

import controller.UserController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {

	UserController userController = new UserController();

	public class LoginVar {
		
		//Scene
		Scene loginScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox();
		VBox loginContainer = new VBox(10);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemRegister = new MenuItem("Register");
		
		//Label
		Label title = new Label("Login");
		Label emailLabel = new Label("Email");
		Label passLabel = new Label("Password");
		Label error = new Label();
		
		//Input Field
		public TextField emailInput = new TextField();
		public PasswordField passInput = new PasswordField();
		
		//Button
		public Button submitButton = new Button("Login");

	}

	private void initialize(LoginVar var) {
		
		//Menu
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemRegister);
		
		//Body
		var.loginContainer.getChildren().addAll(
				var.title, 
				var.emailLabel, var.emailInput, 
				var.passLabel, var.passInput, 
				var.submitButton, 
				var.error
				);
		
		//Setup
		var.mainBox.getChildren().add(var.loginContainer);
		
		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.mainBox);
		
		var.loginScene = new Scene(var.mainContainer, 800, 600);
	}
	
	private void style(LoginVar var) {
		
		var.mainBox.setAlignment(Pos.CENTER);
		var.loginContainer.setMaxWidth(400);
		var.error.setStyle("-fx-text-fill: RED");
		var.title.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
	}

	@Override
	public void start(Stage stage) throws Exception {

		LoginVar var = new LoginVar();
		
		initialize(var);
		style(var);
		userController.loginHandler(var, stage);

		stage.setScene(var.loginScene);
		stage.setTitle("Login");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
