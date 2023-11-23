package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

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
	
	public boolean AddTransactionHeader(Integer userId) {
		String query = String.format("INSERT INTO users VALUES('%d','%d')", transactionId, userId );
		
		Connect db = Connect.getInstance();
		db.execute(query);
		return true;
	}
	
	public ArrayList<Integer> GetAllIDByUser(Integer UserId){
		ArrayList<Integer> TransactionIds = new ArrayList<>();
		String query = String.format("SELECT * FROM transactionheaders where userId = '%d' ", UserId);
		Connect db = Connect.getInstance();
		ResultSet data = db.selectData(query);
		try {
			while(data.next()) {
				Integer transactionID = data.getInt("transactionId");;
				TransactionIds.add(transactionID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TransactionIds;
	}
	
	public boolean Delete(Integer TransactionId) {
		String query = String.format("DELETE FROM transactionheaders where transactionId = '%d' ", TransactionId);
		Connect db = Connect.getInstance();
		db.execute(query);
		return true;
	}
	
	public boolean DeleteAllTransactionByFan(Integer UserId) {
		String query = String.format("SELECT * FROM users where userId = '%d' ", UserId);
		Connect db = Connect.getInstance();
		ResultSet rs = db.selectData(query);
		try {
			String roleData = rs.getString("role");
			if(roleData != "Fan") {
				return false;
			}
			String queryDelete = String.format("SELECT * FROM transactionheaders where userId = '%d' ", UserId);
			db.execute(queryDelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
