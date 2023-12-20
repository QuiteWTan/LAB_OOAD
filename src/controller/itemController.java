package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.database.itemModel;
import model.database.TransactionDetailModel;
import model.database.TransactionHeaderModel;
import model.object.Item;
import model.object.User;
import view.VendorHomePage.VendorVar;

public class itemController {

	public ArrayList<Item> getAllItemsByVendor(Integer userId) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		itemModel item = new itemModel();
		
		itemList.addAll(item.getAllItemByVendor(userId));
		
		return itemList;
	}
	
	public ArrayList<Item> getAllItems(Integer userId) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		itemModel item = new itemModel();
		
		itemList.addAll(item.getAllItemByVendor(userId));
	
		return itemList;
	}
	
	public ArrayList<Item> getItem(Integer userId) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		itemModel item = new itemModel();
		
		itemList.add(item.getItem(userId));
		
		return itemList;
	}
	
	public void deleteItem(Integer itemId) {
		TransactionDetailModel tdm = new TransactionDetailModel();
		TransactionHeaderModel thm = new TransactionHeaderModel();
		itemModel im = new itemModel();
		
		ArrayList<Integer> tIdList = tdm.getAllIdByItem(itemId);
		
		for(Integer tId : tIdList) {
			tdm.delete(tId);
			thm.Delete(tId);
		}
		im.deleteItem(itemId);
	}
	
	
//	di add usernya
	public void addItem( VendorVar var) {
		itemModel im = new itemModel();
		
		var.insertButton.setOnMouseClicked(e -> {			
			Integer priceTemp = !var.itemPrice_tf.getText().isEmpty() ? Integer.parseInt(var.itemPrice_tf.getText().toString()) : null;
			System.out.println(priceTemp);
			if(var.itemName_tf.getText().isEmpty()) {
				var.insertErrorMessage_label.setText("Item Name Must be Filled");
				return;
				
			} else if(var.itemDescription_tf.getText().isEmpty()) {
				var.insertErrorMessage_label.setText("Item Description Must Be Filled");
				return;
				
			}else if(var.itemPrice_tf.getText().isEmpty() || priceTemp <= 0) {
				var.insertErrorMessage_label.setText("Item Price Must Be Filled");
				return;
				
			}
			
			String itemName = var.itemName_tf.getText();
			String itemDesc = var.itemDescription_tf.getText();
			Integer itemPrice = priceTemp;
//			di add user idnya
			
			im.addItem(2, itemName, itemDesc, itemPrice);
			ArrayList<Item> itemList = getAllItemsByVendor(2);
	        ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);
	        
			var.ItemTable.setItems(itemList1);
			var.ItemTable.refresh();
		});
		
	
	}
	
//	di add usernya
	
//	dikasih user parameter
	public void updateItem(VendorVar var) {
		itemModel im = new itemModel();
		var.updateButton.setOnMouseClicked(e -> {
			Item item = var.ItemTable.getSelectionModel().getSelectedItem(); 
			Integer priceTemp = !var.updateItemPrice_tf.getText().toString().isEmpty() ? Integer.parseInt(var.updateItemPrice_tf.getText().toString()) : null;
			
			if(var.updateItemPrice_tf.getText().isEmpty() || priceTemp <= 0) {
				var.updateErrorMessage_label.setText("Item Price Must Be Filled");
				return;		
			}
			
			String itemName = var.itemName_tf.getText();
			String itemDesc = var.itemDescription_tf.getText();
//			di add user idnya
			im.setItemPrice(priceTemp, item.getItemId());
			
			ArrayList<Item> itemList = getAllItemsByVendor(2);
	        ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);
	        
			var.ItemTable.setItems(itemList1);
			var.ItemTable.refresh();
		});
		
	}
}
