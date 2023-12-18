package model.object;

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
}
