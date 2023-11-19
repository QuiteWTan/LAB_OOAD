package model;

public class TransactionHeader {
	private Integer transactionId;
	private Integer userId;
	
	public TransactionHeader(Integer transactionId, Integer userId) {
		this.transactionId = transactionId;
		this.userId = userId;
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
	
}
