package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.database.TransactionDetailModel;
import model.database.TransactionHeaderModel;
import model.database.itemModel;
import model.object.Item;
import model.object.User;
import view.ItemDetailPopup;
import view.ItemDetailPopup.ItemDetailPopupVar;
import view.ItemDetailView;
import view.ItemDetailView.ItemDetailVar;
import view.VendorHomePage.VendorVar;

public class itemController {
	
	TransactionDetailModel transactionDetailModel = new TransactionDetailModel();
	TransactionHeaderModel transactionHeaderModel = new TransactionHeaderModel();

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
	
	public void openPopUpItem(Item item, User user) {
		new ItemDetailView(item, user);
	}
	
	public void getItemDetail(ItemDetailVar var, Item item) {
		
		var.titleData.setText(item.getItemName());
		var.descData.setText(item.getItemDescription());
		var.priceData.setText(String.valueOf(item.getPrice()));
		
	}
	
	public void ItemDetailHandler(ItemDetailVar var, Item item, User user) {
		
		var.buyButton.setOnMouseClicked(e->{			
			Integer boughtAmount = Integer.parseInt(var.quantityText.getText().toString());
			Integer itemId = item.getItemId();
			Integer userId = user.getUserId();
			
			if(boughtAmount > 0) {
				Boolean transaction = addTransactionHeader(userId);
				
				if(transaction) {
					Integer transactionId = transactionHeaderModel.getLatestTransactionId();
					Boolean buy = addTransactionDetail(transactionId, itemId, boughtAmount);
					if(buy) {
						var.stage.close();
					}
				} else {
					var.error.setText("There was an error with processing your request.");
				}
				
			}
			else {
				var.error.setText("Quantity must be more than 0!");
			}
		});
	}
	
	
	public Boolean addTransactionHeader(Integer userId) {
		transactionHeaderModel.AddTransactionHeader(userId);
		
		return true;
	}
	
	public Boolean addTransactionDetail(Integer transactionId, Integer ItemId, Integer quantity) {
		if(transactionId == 0) {
			return false;
		} else {
			transactionDetailModel.addTransactionDetail(transactionId, ItemId, quantity);
			return true;
		}
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
	

	public void addItem( VendorVar var, User Vendor) {
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
			ArrayList<Item> itemList = getAllItemsByVendor(Vendor.getUserId());
	        ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);
	        
			var.ItemTable.setItems(itemList1);
			var.ItemTable.refresh();
		});
		
	
	}
	
	public void updateItem(VendorVar var, User Vendor) {
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

			im.setItemPrice(priceTemp, item.getItemId());
			
			ArrayList<Item> itemList = getAllItemsByVendor(Vendor.getUserId());
	        ObservableList<Item> itemList1 = FXCollections.observableArrayList(itemList);
	        
			var.ItemTable.setItems(itemList1);
			var.ItemTable.refresh();
		});
		
	}
	
	public void showItemDetail(Item item) {
		new ItemDetailPopup(item);
	}
	
	public void assignData(ItemDetailPopupVar var, Item item) {
		var.itemName.setText("Item Name : " + item.getItemName());
		var.itemDesc.setText("Item Description : " + item.getItemDescription());
		var.itemPrice.setText("Item Price : " + item.getPrice());
		
	}
}
