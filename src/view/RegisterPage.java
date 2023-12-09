package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage{
	
	UserController userController = new UserController();

	Stage stage;
	Scene scene;
	
	VBox vBox;
	GridPane gridPane;
	BorderPane borderPane;
	
	Label label, usernameLabel, emailLabel, passwordLabel, confirmPasswordLabel, roleLabel, resultLabel;
	TextField usernameInput, emailInput, confirmPasswordInput;
	PasswordField passwordInput;
	RadioButton vendorRadioButton, influencerRadioButton;
	ToggleGroup roleSelectionGroup;
	Button submitButton;
	
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItemLogin;
	
	private void setMenu() {
		
		menuBar = new MenuBar();
		menu = new Menu("Menu");
		menuItemLogin = new MenuItem("Login");
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItemLogin);
		borderPane.setTop(menuBar);
		
	}
	
	private void initialize() {
		
		borderPane = new BorderPane();
		gridPane = new GridPane();
		
		label = new Label("RegisterForm");
		usernameLabel = new Label("Username: ");
		emailLabel = new Label("Email: ");
		passwordLabel = new Label("Password: ");
		confirmPasswordLabel = new Label("Confirm Password: ");
		roleLabel = new Label("Role: ");
		resultLabel = new Label();
		
		usernameInput = new TextField();
		emailInput = new TextField();
		passwordInput = new PasswordField();
		confirmPasswordInput = new TextField();
		
		roleSelectionGroup = new ToggleGroup();
		vendorRadioButton = new RadioButton("Vendor");
		vendorRadioButton.setToggleGroup(roleSelectionGroup);
		vendorRadioButton.setSelected(true);
		influencerRadioButton = new RadioButton("Influencer");
		influencerRadioButton.setToggleGroup(roleSelectionGroup);
		
		submitButton = new Button("Register");
		
		gridPane.add(label, 1, 1);
		gridPane.add(usernameLabel, 1, 3);
		gridPane.add(usernameInput, 2, 3);
		gridPane.add(emailLabel, 1, 4);
		gridPane.add(emailInput, 2, 4);
		gridPane.add(passwordLabel, 1, 5);
		gridPane.add(passwordInput, 2, 5);
		gridPane.add(confirmPasswordLabel, 1, 6);
		gridPane.add(confirmPasswordInput, 2, 6);
		gridPane.add(roleLabel, 1, 7);
		gridPane.add(vendorRadioButton, 1, 8);
		gridPane.add(influencerRadioButton, 1, 9);
		gridPane.add(submitButton, 1, 10);
		
		setMenu();
		
		borderPane.setCenter(gridPane);
		
		scene = new Scene(borderPane, 600, 600);
	}
	
	public RegisterPage(Stage stage) {
		this.stage = stage;
		
		initialize();
		
		this.stage.setScene(scene);
		this.stage.setTitle("Register");
		this.stage.show();
	}

}
