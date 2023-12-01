package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.RegisterPage;

public class Main extends Application{

	Stage stage;
	Scene scene;
	
	BorderPane borderPane;
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
		
		menuItemRegister.setOnAction(e->{
			
			new RegisterPage(stage);
		});
	}
	
	private void initialize(){
		borderPane = new BorderPane();
		
		setMenu();
		
		scene = new Scene(borderPane, 600, 600);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		initialize();
		
		this.stage.setScene(scene);
		this.stage.setTitle("SNova");
		
		
		this.stage.show();	
	}

}
