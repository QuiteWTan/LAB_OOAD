package controller;

import java.util.ArrayList;

import database.Connect;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.database.UserModel;
import model.object.User;
import view.LoginPage;
import view.LoginPage.LoginVar;
import view.RegisterPage;
import view.RegisterPage.RegisterVar;
import view.ViewAccount;

public class UserController {
	
	UserModel userModel = new UserModel();
	
	//view handler
	
	public void navigateLogin(Stage stage) {
		try {
			new LoginPage().start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public void navigateRegister(Stage stage) {
		new RegisterPage(stage);
	}
	
	public void loginHandler(LoginVar var, Stage stage) {
		
		var.menuItemRegister.setOnAction(e->{
			navigateRegister(stage);
		});
		
	}
	
	public void registerHandler(RegisterVar var, Stage stage) {

		var.menuItemLogin.setOnAction(e -> {
			navigateLogin(stage);
		});
		
		var.submitButton.setOnMouseClicked(e->{
			
			String username = var.usernameInput.getText().toString();
			String email = var.emailInput.getText().toString();
			String password = var.passInput.getText().toString();
			String confirmPassword = var.cfPasswordInput.getText().toString();
			RadioButton selectedRole = (RadioButton) var.roleSelectionGroup.getSelectedToggle();
			String role = selectedRole.getText().toString();
			
			this.addUser(username, email, password, confirmPassword, role);
			navigateLogin(stage);
		});

	}
	
	
	//Validation logic
	
	public Boolean validateUsername(String username) {
		
		if(username.isEmpty()) {
			return false;
			
		} else if(!userModel.searchExistingUsername(username)) {
			return false;
			
		}
		
		return true;
	}
	
	public Boolean validateEmail(String email) {
		
		if(!email.contains("@") || !userModel.searchExistingEmail(email)) {
			return false;
			
		}
		
		return true;
		
	}
	
	public Boolean validatePassword(String password, String confirmPassword) {
		
		if(password.length() < 6 || !password.matches("^[a-zA-Z0-9]+$") || !password.equals(confirmPassword)){
			return false;
			
		}
		
		return true;
	}
	
	public Boolean addUser(String username, String email, String password, String confirmPassword, String role) {
		
	    Boolean usernameValid = validateUsername(username);
	    Boolean emailValid = validateEmail(email);
	    Boolean passwordValid = validatePassword(password, confirmPassword);
	    
	    if(usernameValid && emailValid && passwordValid) {
	    	
	    	userModel.addUser(username, email, password, role);
	    	
	    	return true;
	    }
	    
	    return false;
  
	}
	
//	private User currUser;
//	private Boolean isAdmin; 
//	
//	// Account
//	private ArrayList<User> users;
//	
//	
//	public UserController() {
//		
//		// Dummy User
//		this.currUser = new User(1, "admin", "admin@gmail.com", "admin", "Admin");
////		currUser = newcurrUser.getRole().equals("Admin") ? true : false;
//		 User(1, "user1", "user1@gmail.com", "user1", "User");
//		this.isAdmin = 
//		this.users = new ArrayList<>();
//	}
//	
//	public Scene showFanAccount() {
//		this.users = userModel.getAllUsersInRole("Fan");
//		String titleString = "Fan Accounts";
//		
//		ViewAccount view = new ViewAccount(this.users, titleString, this.isAdmin);
//		
//		view.setButtonClickHandler(() -> {
//			deleteSelectedUser(view.getSelectedUsers());
//			this.users = userModel.getAllUsersInRole("Fan");
//			view.refreshUserList(this.users);
//        });
//        
//        return view.getViewScene();
//
//	}
//	
//	public Scene showInfluencerAccount() {
//		this.users = userModel.getAllUsersInRole("Influencer");
//		String titleString = "Influencer Accounts";
//		
//		ViewAccount view = new ViewAccount(this.users, titleString, this.isAdmin);
//		
//		view.setButtonClickHandler(() -> {
//			deleteSelectedUser(view.getSelectedUsers());
//			this.users = userModel.getAllUsersInRole("Influencer");
//			view.refreshUserList(this.users);
//        });
//        
//        
//        return view.getViewScene();
//	}
//	
//	public Scene showVendorAccount() {
//		this.users = userModel.getAllUsersInRole("Vendor");
//		String titleString = "Vendor Accounts";
//		
//		ViewAccount view = new ViewAccount(this.users, titleString, this.isAdmin);
//		
//		view.setButtonClickHandler(() -> {
//			deleteSelectedUser(view.getSelectedUsers());
//			this.users = userModel.getAllUsersInRole("Vendor");
//			view.refreshUserList(this.users);
//        });
//        
//        
//        return view.getViewScene();
//		
//	}
//	
//	public void deleteSelectedUser(ObservableList<User> users) {
//		for (User user : users) {
//			userModel.deleteUser(user.getUserId());	
//		}	
//	}
	
}
