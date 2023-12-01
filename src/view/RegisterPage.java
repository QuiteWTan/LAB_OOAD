package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import app.Main;

public class RegisterPage {

	Stage stage;
	Scene scene;
	
	GridPane gridPane;
	BorderPane borderPane;
	
	Label label, usernameLabel, emailLabel, passwordLabel, confirmPasswordLabel, roleLabel, resultLabel;
	TextField usernameInput, emailInput, passwordInput, confirmPasswordInput;
	RadioButton vendorRadioButton, influencerRadioButton;
	ToggleGroup roleSelectionGroup;
	Button submitButton;
	
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItemHome;
	
	ScrollPane scrollPane;
	
	private void setMenu() {
		menuBar = new MenuBar();
		menu = new Menu("Menu");
		menuItemHome = new MenuItem("Home");
		
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItemHome);
		
		borderPane.setTop(menuBar);
		
		menuItemHome.setOnAction(e->{
			
			Main main = new Main();
			try {
				main.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	private void action() {
		
		submitButton.setOnMouseClicked(e->{
			
			Boolean regis = UserController.addUser(usernameInput.getText(), emailInput.getText(), passwordInput.getText(), confirmPasswordInput.getText(), roleSelectionGroup.selectedToggleProperty().getName());
			
			if(regis == true) {
				resultLabel.setText("Register Success");
				
			} else {
				resultLabel.setText("Register Failed");
				
			}
			
		});
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
		passwordInput = new TextField();
		confirmPasswordInput = new TextField();
		
		vendorRadioButton = new RadioButton("Vendor");
		vendorRadioButton.setToggleGroup(roleSelectionGroup);
		vendorRadioButton.setSelected(true);
		influencerRadioButton = new RadioButton("Influencer");
		influencerRadioButton.setToggleGroup(roleSelectionGroup);
		
		submitButton = new Button("Register");
		
		gridPane.add(scrollPane, 1, 0);
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
		
		action();
		
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
