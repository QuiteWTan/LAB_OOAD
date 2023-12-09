package view;

import controller.UserController;
import javafx.application.Application;
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

	Stage stage;
	Scene scene;

	VBox vBox;
	GridPane gridPane;
	BorderPane borderPane;

	Label label, emailLabel, passwordLabel;
	TextField emailInput;
	PasswordField passwordInput;
	Button submitButton;

	MenuBar menuBar;
	Menu menu;
	MenuItem menuItemRegister;

	private void setMenu() {

		menuBar = new MenuBar();
		menu = new Menu("Menu");
		menuItemRegister = new MenuItem("Register");
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItemRegister);
		borderPane.setTop(menuBar);

	}

	private void initialize() {

		borderPane = new BorderPane();
		gridPane = new GridPane();

		label = new Label("RegisterForm");
		emailLabel = new Label("Email: ");
		passwordLabel = new Label("Password: ");

		emailInput = new TextField();
		passwordInput = new PasswordField();

		submitButton = new Button("Register");

		gridPane.add(label, 1, 1);
		gridPane.add(emailLabel, 1, 3);
		gridPane.add(emailInput, 2, 3);
		gridPane.add(passwordLabel, 1, 4);
		gridPane.add(passwordInput, 2, 4);
		gridPane.add(submitButton, 1, 5);

		setMenu();
		
		borderPane.setCenter(gridPane);

		scene = new Scene(borderPane, 600, 600);
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		initialize();

		this.stage.setScene(scene);
		this.stage.setTitle("Login");
		this.stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
