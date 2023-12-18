package main;

import controller.UserController;
import controller.TransactionController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		UserController uc = new UserController();
		TransactionController tc = new TransactionController();
		
		uc.navigateVendor(primaryStage);
		primaryStage.show();
	}

}
