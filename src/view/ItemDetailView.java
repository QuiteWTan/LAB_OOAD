package view;

import controller.itemController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.database.itemModel;
import model.object.Item;
import model.object.PanelHeader;
import model.object.User;
import view.PanelDetailView.FanPanelDetail;

public class ItemDetailView {
	itemController ic = new itemController();	
	
	User vendor;
	Item vendorItem;
	
public class ItemDetailVar {
		
		//Scene
		public Stage stage;
		Scene ItemDetailScene;
		BorderPane mainContainer = new BorderPane();
		VBox mainBox = new VBox(16);
		VBox dataContainer = new VBox(4);
		HBox timeContainer = new HBox(50);
		
		//Label
		Label pageTitle = new Label("Item Detail");
		Label title = new Label("Item name:");		
		Label desc = new Label("Description:");
		Label price = new Label("Price:");
		Label quantity = new Label("Amount to purchase:");
		public Label error = new Label();
		
		public Label titleData = new Label();
		public Label descData = new Label();		
		public Label priceData = new Label();
		
		public TextField quantityText = new TextField("0");
		
		public Button buyButton = new Button("Buy Item"); 
		
	}

	private void initialize(ItemDetailVar var) {
		
		//Body		
		var.dataContainer.getChildren().addAll(
				var.pageTitle,
				var.title, var.titleData, 
				var.desc, var.descData, 
				var.price, var.priceData,
				var.quantity, var.quantityText,
				var.buyButton, var.error
				);		
		
		//Setup
		
		var.mainBox.getChildren().addAll(var.dataContainer);
		
		var.mainContainer.setCenter(var.mainBox);
		
		var.ItemDetailScene = new Scene(var.mainContainer, 600, 450);
		
		var.stage = new Stage();
		var.stage.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void style(ItemDetailVar var) {
	
		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		var.error.setStyle("-fx-text-fill: RED");
		
		var.mainBox.setAlignment(Pos.CENTER);
		var.dataContainer.setMaxWidth(550);

	}
	
	public ItemDetailView(Item item, User user) {
		
		ItemDetailVar var = new ItemDetailVar();
		
		this.vendorItem = item;
		
		initialize(var);
		style(var);
		
		ic.getItemDetail(var, item);
		ic.ItemDetailHandler(var, item, user);
		
		var.stage.setScene(var.ItemDetailScene);
		var.stage.setTitle("Panel Statistics");
		var.stage.setResizable(false);
		var.stage.show();
		
	}	
}
