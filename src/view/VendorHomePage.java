package view;

import java.util.ArrayList;

import controller.UserController;
import controller.itemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.object.Item;
import model.object.User;

public class VendorHomePage {

	UserController uc = new UserController();
	itemController ic = new itemController();
	User Vendor;

	public class VendorVar {
		Scene homeScene;
		VBox homeContainer = new VBox();
		HBox formBox = new HBox();
		VBox insertFormBox = new VBox();
		VBox updateFormBox = new VBox();
		BorderPane mainContainer = new BorderPane();

		Label InsertFormTitle = new Label("Insert Item");

		Label itemName_label = new Label("Item Name");
		public TextField itemName_tf = new TextField();
		Label itemDescription_label = new Label("Item Description");
		public TextField itemDescription_tf = new TextField();
		Label itemPrice_label = new Label("Item Price");
		public TextField itemPrice_tf = new TextField();
		public Label insertErrorMessage_label = new Label();
		public Button insertButton = new Button("Insert");

		Label UpdateFormTitle = new Label("Update Item");

		Label updateItemName_label = new Label("Select the item table row !");
		Label updateItemPrice_label = new Label("Item Price");
		public TextField updateItemPrice_tf = new TextField();
		public Label updateErrorMessage_label = new Label();
		public Button updateButton = new Button("Update");;

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		public MenuItem menuItemLogOut = new MenuItem("Log Out");

		public TableView<Item> ItemTable = new TableView<Item>();
		TableColumn<Item, Integer> itemName_col = new TableColumn<>("Item Name");
		TableColumn<Item, String> itemPrice_col = new TableColumn<>("Item Price");
		TableColumn<Item, String> itemDesc_col = new TableColumn<>("Item Description");
		TableColumn<Item, Void> delete_col = new TableColumn<>("Delete");
		TableColumn<Item, Void> detail_col = new TableColumn<>("Details");
		Label pageTitle = new Label("Vendor Home Page");
		Label upcoming = new Label("Item Table");

	}

	private void initialize(VendorVar var) {
		var.ItemTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		ArrayList<Item> itemList = new ArrayList<>();
		itemList.addAll(ic.getAllItems(Vendor.getUserId()));
		Integer i;
		for (Item item : itemList) {
			var.ItemTable.getItems().add(item);
		}
		var.menuBar.getMenus().add(var.menu);
		var.menu.getItems().add(var.menuItemLogOut);

		var.itemName_col.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		var.itemPrice_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		var.itemDesc_col.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
		var.detail_col.setCellFactory(new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
			@Override
			public TableCell<Item, Void> call(TableColumn<Item, Void> param) {

				return new TableCell<Item, Void>() {
					private final Button DetailButton = new Button("Details");

					{

						DetailButton.setOnMouseClicked(e -> {
							Item data = getTableView().getItems().get(getIndex());
							ic.showItemDetail(data);
						});

					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(DetailButton);
						}
					}
				};
			};
		});
		var.delete_col.setCellFactory(new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
			@Override
			public TableCell<Item, Void> call(TableColumn<Item, Void> param) {

				return new TableCell<Item, Void>() {
					private final Button DeleteButton = new Button("Delete");

					{

						DeleteButton.setOnMouseClicked(e -> {
							Item data = getTableView().getItems().get(getIndex());
							ic.deleteItem(data.getItemId());
							ArrayList<Item> itemList = ic.getAllItemsByVendor(data.getUserId());
							ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);

							var.ItemTable.setItems(itemList1);
							var.ItemTable.refresh();
						});

					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(DeleteButton);
						}
					}
				};
			};
		});

		var.insertFormBox.getChildren().addAll(
				var.InsertFormTitle, 
				var.itemName_label, var.itemName_tf, 
				var.itemDescription_label, var.itemDescription_tf, 
				var.itemPrice_label, var.itemPrice_tf, 
				var.insertErrorMessage_label, 
				var.insertButton
			);
		
		var.updateFormBox.getChildren().addAll(
				var.UpdateFormTitle, 
				var.updateItemName_label,
				var.updateItemPrice_label, var.updateItemPrice_tf, 
				var.updateErrorMessage_label, 
				var.updateButton
			);
		
		var.formBox.getChildren().addAll(var.insertFormBox, var.updateFormBox);
		
		var.ItemTable.getColumns().addAll(var.itemName_col, var.itemPrice_col, var.itemDesc_col,var.detail_col, var.delete_col);
	
		var.homeContainer.getChildren().addAll(
				var.pageTitle,
				var.upcoming,
				var.ItemTable,
				var.formBox
			);

		var.mainContainer.setTop(var.menuBar);
		var.mainContainer.setCenter(var.homeContainer);

		var.homeScene = new Scene(var.mainContainer, 800, 600);
	}

	private void style(VendorVar var) {

		var.homeContainer.setMaxWidth(750);

		var.pageTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 24px;");
		var.InsertFormTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 20px;");
		var.UpdateFormTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 20px;");
		var.formBox.setMaxWidth(750);
		var.ItemTable.setMaxHeight(200);
		var.itemName_col.setMinWidth(200);
		var.itemPrice_col.setPrefWidth(200);
		var.itemDesc_col.setPrefWidth(200);
		var.insertFormBox.setPrefWidth(250);
		var.updateFormBox.setPrefWidth(250);
		var.formBox.setSpacing(40);
		var.updateItemName_label.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 16px;");
	}

	public VendorHomePage(User user, Stage stage) {
		VendorVar var = new VendorVar();
		this.Vendor = user;
		initialize(var);
		style(var);
		uc.VendorPageHandler(var, stage, user);
		ic.addItem(var, this.Vendor);
		ic.updateItem(var, this.Vendor);
		stage.setScene(var.homeScene);
		stage.setTitle("User Homepage");
		stage.show();

	}

}
