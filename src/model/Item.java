package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class Item {
	private Integer itemId;
	private Integer userId;
	private String itemName;
	private String itemDescription;
	private Integer price;
	
	public Item(Integer itemId, Integer userId, String itemName, String itemDescription, Integer price) {
		this.itemId = itemId;
		this.userId = userId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.price = price;
	}

	public ArrayList<Item> getAllItems(Integer uId) {
		
		ArrayList<Item> items = new ArrayList<>();
		
		String query = String.format("SELECT * FROM `items` WHERE userId = %d", uId);
		
		Connect db = Connect.getInstance();
		
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer itemId = rs.getInt("itemId");
				Integer userId = rs.getInt("userId");
				String itemName = rs.getString("itemName");
				String itemDescription = rs.getString("itemDescription");
				Integer itemPrice = rs.getInt("price");
				
				items.add(new Item(itemId, userId, itemName, itemDescription, itemPrice));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}
	
	public Item getItem(Integer Id) {
		
		String query = String.format("SELECT * FROM `items` WHERE itemId = %d", Id);
		
		Connect db = Connect.getInstance();
		
		ResultSet rs = db.selectData(query);
		
		Item item = null;
		
		try {
			Integer itemId = rs.getInt("itemId");
			Integer userId = rs.getInt("userId");
			String itemName = rs.getString("itemName");
			String itemDescription = rs.getString("itemDescription");
			Integer itemPrice = rs.getInt("price");
			
			item = new Item(itemId, userId, itemName, itemDescription, itemPrice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}
	
	public void addItem(Integer userId, String itemName, String itemDescription, Integer price) {
		
		String query = String.format("INSERT INTO `items` VALUES (%d, %s, %s, %d", userId, itemName, itemDescription, price);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public void setItemPrice(Integer price) {
		
		String query = String.format("UPDATE `items` SET price = %d WHERE itemId = %d AND userId = %d", price, itemId, userId);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public void deleteItem(Integer itemId) {
		
		String query = String.format("DELETE from `items` WHERE itemId = %d", itemId);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public void deleteAllItemByVendor(Integer userId) {
		
		String query = String.format("DELETE from `items` WHERE userId = %d", userId);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public ArrayList<Item> getAllItemByVendor(Integer uId) {
		
		ArrayList<Item> items = new ArrayList<>();
		
		String query = String.format("SELECT * FROM `items` WHERE userId = %d", uId);
		
		Connect db = Connect.getInstance();
		
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer itemId = rs.getInt("itemId");
				Integer userId = rs.getInt("userId");
				String itemName = rs.getString("itemName");
				String itemDescription = rs.getString("itemDescription");
				Integer itemPrice = rs.getInt("price");
				
				items.add(new Item(itemId, userId, itemName, itemDescription, itemPrice));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}
}
