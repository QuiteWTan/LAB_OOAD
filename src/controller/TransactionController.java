package controller;

import java.util.ArrayList;

import javafx.stage.Stage;
import model.object.FanTransaction;
import model.object.User;
import model.object.Item;
import model.object.TransactionDetail;
import view.ViewAllTransactionHistory;
import view.ViewAllTransactionHistory.AllTransactionHistoryVar;
import model.database.TransactionDetailModel;
import model.database.TransactionHeaderModel;
import model.database.itemModel;

public class TransactionController {
	
	TransactionDetailModel tdm = new TransactionDetailModel();
	TransactionHeaderModel thm = new TransactionHeaderModel();
	itemModel im = new itemModel();
	UserController uc = new UserController();
	itemController ic = new itemController();
	
	public ArrayList<FanTransaction> getAllTransactionByFan(Integer userId) {
		ArrayList<TransactionDetail> transactionDetailList = new ArrayList<>();
		ArrayList<Integer> transactionHeaderList = new ArrayList<>();
		ArrayList<Item> itemList = new ArrayList<>();
		ArrayList<FanTransaction> ft = new ArrayList<>();
		
		transactionHeaderList.addAll(thm.GetAllIDByUser(userId));
		
		for(Integer tId : transactionHeaderList) {
			transactionDetailList.addAll(tdm.getTransactionDetail(tId));
		}
		
		for(TransactionDetail td : transactionDetailList) {
			itemList.add(im.getItem(td.getItemId()));
		}
	
		for(int i =0;i < transactionHeaderList.size();i++) {
			ft.add(new FanTransaction(itemList.get(i).getItemName(),itemList.get(i).getPrice(), transactionDetailList.get(i).getQuantity()));
		}
		
		
		return ft;
	}
	
	
	public void TransactionHistoryHandler(AllTransactionHistoryVar var, Stage stage, User user) {

		var.menuItemPanel.setOnAction(e -> {
			uc.navigateFanHome(stage, user);
		});

		var.menuItemVendor.setOnAction(e -> {
			uc.navigateFanVendor(stage, user);
		});

		var.menuItemLogOut.setOnAction(e -> {
			uc.navigateLogin(stage);
		});

	}
	
}
