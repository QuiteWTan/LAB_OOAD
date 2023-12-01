package app;

import controller.FanController;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewAccount;

public class Main extends Application {	
	
	public static void main(String[] args) {
		launch(args);
	}

//	@Override
	public void start(Stage stage) throws Exception {
//		UserController uc = new UserController();
		FanController fc = new FanController();
		
		stage.setScene(fc.showAllPanel());
		
		stage.setTitle("JAVA FX");
		stage.show();
	}

}
