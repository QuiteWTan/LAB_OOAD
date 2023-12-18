package controller;

import java.util.ArrayList;
import model.database.itemModel;
import model.object.Item;

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
}
