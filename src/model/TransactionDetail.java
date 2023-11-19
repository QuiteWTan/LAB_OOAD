package model;

public class TransactionDetail {
	private Integer transactionId;
	private Integer userId;
	private Integer quantity;
	public TransactionDetail(Integer transactionId, Integer userId, Integer quantity) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.quantity = quantity;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	

}
