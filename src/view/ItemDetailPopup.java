package view;

import controller.itemController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.object.Item;
import model.object.User;

public class ItemDetailPopup {
	itemController ic = new itemController();	
	
	User vendor;
	Item vendorItem;
	
	public class ItemDetailPopupVar {
		
		//Scene
		public Stage stage;
		Scene ItemDetailScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(16);
		VBox dataContainer = new VBox(4);
		HBox timeContainer = new HBox(50);
		
		//Label
		Label pageTitle = new Label("Item Details");
		public Label itemName = new Label();		
		public Label itemDesc = new Label();
		public Label itemPrice = new Label();

		
	}

	private void initialize(ItemDetailPopupVar var) {
		
		//Body		
		var.dataContainer.getChildren().addAll(
				var.pageTitle,
				var.itemName,
				var.itemDesc, 
				var.itemPrice 
				);		
		
		//Setup
		
		var.mainBox.getChildren().addAll(var.dataContainer);
		
		var.mainContainer.setCenter(var.mainBox);
		
		var.ItemDetailScene = new Scene(var.mainContainer, 600, 450);
		
		var.stage = new Stage();
		var.stage.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void style(ItemDetailPopupVar var) {
	
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		
		var.mainBox.setAlignment(Pos.CENTER);
		var.dataContainer.setMaxWidth(550);

	}
	
	public ItemDetailPopup(Item item) {
		
		ItemDetailPopupVar var = new ItemDetailPopupVar();
		
		this.vendorItem = item;
		
		initialize(var);
		style(var);
		ic.assignData(var, this.vendorItem);
		var.stage.setScene(var.ItemDetailScene);
		var.stage.setTitle("Item Detail");
		var.stage.setResizable(false);
		var.stage.show();
		
	}	
}
