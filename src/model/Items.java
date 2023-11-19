package model;

public class Items {
	private Integer itemId;
	private String userId;
	private String itemName;
	private String itemDescription;
	private Integer price;
	
	public Items(String userId, String itemName, String itemDescription, Integer price) {
		super();
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
