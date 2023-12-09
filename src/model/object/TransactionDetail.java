package model.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class TransactionDetail {
	private Integer transactionId;
	private Integer itemId;
	private Integer quantity;
	
	public TransactionDetail(Integer transactionId, Integer itemId, Integer quantity) {
		this.transactionId = transactionId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	public void addTransactionDetail(Integer transactionId, Integer itemId, Integer quantity) {
		
		String query = String.format("INSERT INTO `transactiondetails` VALUES (%d, %d, %d", transactionId, itemId, quantity);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public ArrayList<Integer> getAllIdByItem(Integer itemId) {
		
		ArrayList<Integer> ids = new ArrayList<>();
		
		String query = String.format("SELECT transactionId FROM `transactiondetails` WHERE itemId = %d", itemId);
		
		Connect db = Connect.getInstance();
		
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer transactionId = rs.getInt("transactionId");
				
				ids.add(transactionId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ids;
		
	}
	
	public void delete(Integer transactionId) {
		
		String query = String.format("DELETE from `transactiondetails` WHERE transactionId = %d", transactionId);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public void getTransactionByFan(Integer userId) {
		
		String query = String.format("SELECT th.transactionId, th.userId, td.itemId, td.quantity from `transactionheaders` th JOIN `transactiondetails` td ON th.transactionId = td.transactionId WHERE userId = %d", userId);
		
		Connect db = Connect.getInstance();
		
		db.execute(query);
		
	}
	
	public ArrayList<TransactionDetail> getTransactionDetail(Integer tId) {
		
		ArrayList<TransactionDetail> tds = new ArrayList<>();
		
		String query = String.format("SELECT * FROM `transactiondetails` WHERE transactionId = %d", tId);
		
		Connect db = Connect.getInstance();
		
		ResultSet rs = db.selectData(query);
		
		try {
			while(rs.next()) {
				Integer transactionId = rs.getInt("transactionId");
				Integer itemId = rs.getInt("itemId");
				Integer quantity = rs.getInt("quantity");
				
				tds.add(new TransactionDetail(transactionId, itemId, quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tds;
	}
	

}
