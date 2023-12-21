package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminPage {

	UserController userController = new UserController();

	public class AdminVar {

		public Scene adminScene;

	}

	private void initialize(Stage stage, AdminVar var) {

		var.adminScene = userController.showFanAccount(stage, var);

	}

	public AdminPage(Stage stage) {

		AdminVar var = new AdminVar();

		initialize(stage, var);

		stage.setScene(var.adminScene);
		stage.setTitle("Admin Page");
		stage.setResizable(false);

	}

}
