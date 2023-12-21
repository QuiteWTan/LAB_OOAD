package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.object.TransactionDetail;

public class TransactionDetailModel {

	public void addTransactionDetail(Integer transactionId, Integer itemId, Integer quantity) {

		String query = String.format(
				"INSERT INTO `transactiondetails` (transactionId, itemId, quantity) VALUES (%d, %d, %d)", transactionId,
				itemId, quantity);

		Connect db = Connect.getInstance();

		db.execute(query);

	}

	public ArrayList<Integer> getAllIdByItem(Integer itemId) {

		ArrayList<Integer> ids = new ArrayList<>();

		String query = String.format("SELECT transactionId FROM `transactiondetails` WHERE itemId = %d", itemId);

		Connect db = Connect.getInstance();

		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
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

		String query = String.format(
				"SELECT th.transactionId, th.userId, td.itemId, td.quantity from `transactionheaders` th JOIN `transactiondetails` td ON th.transactionId = td.transactionId WHERE userId = %d",
				userId);
		Connect db = Connect.getInstance();

		db.execute(query);
	}

	public boolean DeleteAllTransactionByItem(Integer itemId) {
		String query = String.format("DELETE FROM transactionheaders where itemId = %d ", itemId);
		Connect db = Connect.getInstance();
		db.execute(query);
		return true;
	}

	public ArrayList<TransactionDetail> getTransactionDetail(Integer tId) {

		ArrayList<TransactionDetail> tds = new ArrayList<>();

		String query = String.format("SELECT * FROM `transactiondetails` WHERE transactionId = %d", tId);

		Connect db = Connect.getInstance();

		ResultSet rs = db.selectData(query);

		try {
			while (rs.next()) {
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
