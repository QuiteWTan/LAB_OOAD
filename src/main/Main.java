package main;

import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		UserController uc = new UserController();
		
		uc.navigateViewAllTransactionHistory(primaryStage);
		primaryStage.show();
	}

}
