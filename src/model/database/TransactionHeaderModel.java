package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import database.Connect;

public class TransactionHeaderModel {

	public Boolean AddTransactionHeader(Integer userId) {
		String query = String.format("INSERT INTO transactionheaders (userId) VALUES (%d)", userId );
		
		Connect db = Connect.getInstance();
		db.execute(query);
		return true;
	}	
	
	public ArrayList<Integer> GetAllIDByUser(Integer UserId){
		
		ArrayList<Integer> TransactionIds = new ArrayList<>();
		String query = String.format("SELECT transactionId FROM transactionheaders where userId = '%d' ", UserId);
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
			if(!roleData.equals("Fan")) {
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
	
	public Integer getLatestTransactionId() {
		String query = String.format("SELECT transactionId FROM transactionheaders ORDER BY transactionId DESC LIMIT 1");
		Connect db = Connect.getInstance();
		ResultSet data = db.selectData(query);
		
		Integer latestTransactionId = 0;
		try {
			if(data.next()) {
				latestTransactionId = data.getInt("transactionId");
				return latestTransactionId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
