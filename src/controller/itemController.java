package controller;

import java.util.ArrayList;

import model.database.TransactionDetailModel;
import model.database.TransactionHeaderModel;
import model.database.itemModel;
import model.object.Item;
import model.object.PanelHeader;
import model.object.TransactionHeader;
import model.object.User;
import view.ItemDetailView;
import view.ItemDetailView.ItemDetailVar;
import view.PanelDetailView;
import view.PanelDetailView.FanPanelDetail;

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
	
	public boolean buyItem(Integer amount) {
		if(amount <= 0) {
			return false;
		}
		return true;
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
			
			if(buyItem(boughtAmount)) {
				Boolean transaction = addTransactionHeader(userId);
				
				if(transaction) {
					Integer transactionId = getTransactionId();
					Boolean buy = addTransactionDetail(transactionId, itemId, boughtAmount);
					if(buy) {
						var.stage.close();
					}
					var.stage.close();
				} else {
					var.error.setText("There was an error with processing your request.");
				}
				
			}
			else {
				var.error.setText("Quantity must be more than 0!");
			}
		});
	}
	
	public Integer getTransactionId() {
		return transactionHeaderModel.getLatestTransactionId();
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
}
