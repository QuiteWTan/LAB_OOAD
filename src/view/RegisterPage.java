package view;

import controller.UserController;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {

	UserController userController = new UserController();

	public class RegisterVar {
		// Scene
		Scene registerScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(16);
		VBox registerContainer = new VBox(8);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemLogin = new MenuItem("Login");

		// Label
		Label pageTitle = new Label("Register");
		Label usernameLabel = new Label("Username");
		Label emailLabel = new Label("Email");
		Label passLabel = new Label("Password");
		Label cfPassLabel = new Label("Confirm Password");
		Label roleLabel = new Label("Role");
		public Label resultLabel = new Label();
		public Label error = new Label();

		// Input Field
		public TextField usernameInput = new TextField();
		public TextField emailInput = new TextField();
		public PasswordField passInput = new PasswordField();
		public PasswordField cfPasswordInput = new PasswordField();

		public ToggleGroup roleSelectionGroup = new ToggleGroup();
		public RadioButton vendorRadioButton = new RadioButton("Vendor");
		public RadioButton influencerRadioButton = new RadioButton("Influencer");
		public RadioButton fanRadioButton = new RadioButton("Fan");

		// Button
		public Button submitButton = new Button("Register");

	}

	private void initialize(RegisterVar var) {
		// Menu
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemLogin);

		// Body
		var.registerContainer.getChildren().addAll(
				var.usernameLabel, var.usernameInput, 
				var.emailLabel, var.emailInput, 
				var.passLabel, var.passInput, 
				var.cfPassLabel, var.cfPasswordInput, 
				var.roleLabel, var.fanRadioButton, var.vendorRadioButton, var.influencerRadioButton, 
				var.submitButton, 
				var.error, var.resultLabel
				);

		var.fanRadioButton.setToggleGroup(var.roleSelectionGroup);
		var.fanRadioButton.setUserData("Fan");
		var.fanRadioButton.setSelected(true);
		var.vendorRadioButton.setToggleGroup(var.roleSelectionGroup);
		var.vendorRadioButton.setUserData("Vendor");
		var.influencerRadioButton.setToggleGroup(var.roleSelectionGroup);
		var.influencerRadioButton.setUserData("Influencer");

		// Setup
		var.mainBox.getChildren().addAll(var.pageTitle, var.registerContainer);

		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.mainBox);

		var.registerScene = new Scene(var.mainContainer, 800, 600);
	}

	private void style(RegisterVar var) {

		var.mainBox.setAlignment(Pos.CENTER);
		var.registerContainer.setMaxWidth(400);
		var.error.setStyle("-fx-text-fill: RED");
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
	}

	public RegisterPage(Stage stage) {

		RegisterVar var = new RegisterVar();

		initialize(var);
		style(var);
		userController.registerHandler(var, stage);

		stage.setScene(var.registerScene);
		stage.setTitle("Register");
		stage.setResizable(false);
	}

}
