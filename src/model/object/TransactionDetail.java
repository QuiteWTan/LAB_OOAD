package model.object;

public class TransactionDetail {
	private Integer transactionId;
	private Integer itemId;
	private Integer quantity;

	public TransactionDetail(Integer transactionId, Integer itemId, Integer quantity) {
		this.setTransactionId(transactionId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
