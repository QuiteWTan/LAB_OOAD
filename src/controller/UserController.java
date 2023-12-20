package controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.database.UserModel;
import model.database.itemModel;
import model.object.User;
import view.InfluencerHomePage;
import view.InfluencerHomePage.InfluencerHomeVar;
import view.LoginPage;
import view.LoginPage.LoginVar;
import view.RegisterPage;
import view.RegisterPage.RegisterVar;
import view.VendorHomePage;
import view.AdminPage;
import view.AdminPage.AdminVar;
import view.FanHomePage;
import view.FanHomePage.HomeVar;
import view.FanVendorView;
import view.FanVendorView.FanVendorVar;
import view.VendorHomePage.VendorVar;
import view.ViewAccount;

public class UserController {
	
	UserModel userModel = new UserModel();
	itemModel ItemModel = new itemModel();
	//view handler
	
	public void navigateLogin(Stage stage) {
		new LoginPage(stage);
	}
	
	public void navigateRegister(Stage stage) {
		new RegisterPage(stage);
	}
	
	// Fan
	public void navigateFanHome(Stage stage, User user) {
		new FanHomePage(stage, user);
	}
	
	public void navigateFanVendor(Stage stage, User user) {
		new FanVendorView(stage, user);
	}
	
//	========================================================
	
	public void navigateVendor(Stage stage, User user) {
		new VendorHomePage(user, stage);
	}
	
	public void navigateInfluencer(Stage stage, User user) {
		new InfluencerHomePage(stage, user);
	}
	
	
	public void navigateAdmin(Stage stage) {
		
		new AdminPage(stage);
		
	}
	
	public void loginHandler(LoginVar var, Stage stage) {
		
		var.menuItemRegister.setOnAction(e->{
			navigateRegister(stage);
		});
		
		var.submitButton.setOnMouseClicked(e -> {			
			
			if(var.emailInput.getText().isEmpty()) {
				var.error.setText("Email must be filled");
				return;
				
			} else if(var.passInput.getText().isEmpty()) {
				var.error.setText("Password must be filled");
				return;
				
			}
			
			String email = var.emailInput.getText().toString();
			String password = var.passInput.getText().toString();
			
			User user = this.getUserByEmail(email);
			
			if(user != null) {
				
				if(user.getPassword().equals(password) && user.getRole().equals("Vendor")) {
					navigateVendor(stage, user);
					
				} else if (user.getPassword().equals(password) && user.getRole().equals("Influencer")) {
					navigateInfluencer(stage, user);
					
				}else if (user.getPassword().equals(password) && user.getRole().equals("Fan")) {
					navigateFanHome(stage, user);
					
				} else if (user.getPassword().equals(password) && user.getRole().equals("Admin")) {
					navigateAdmin(stage);
					
				} else {
					var.error.setText("The password is wrong");
				}
				
			} else {
				var.error.setText("Account doesn't exist");
			}
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
			String role = var.roleSelectionGroup.getSelectedToggle().getUserData().toString();
			
			Boolean success = this.addUser(var, username, email, password, confirmPassword, role);
			
			if(success == true) {
				navigateLogin(stage);
			}
		});

	}
	
	// Handler
	//Fan
	public void FanHandler(HomeVar fanVar, Stage stage, User user) {
		
		fanVar.menuItemPanel.setOnAction(e->{
			navigateFanHome(stage, user);
		});
		
		fanVar.menuItemVendor.setOnAction(e->{
			navigateFanVendor(stage, user);
		});
		
		fanVar.menuItemLogOut.setOnAction(e->{
			navigateLogin(stage);
		});
	}
	
	public void FanVendorHandler(FanVendorVar fanVar, Stage stage, User user) {
		fanVar.menuItemPanel.setOnAction(e->{
			navigateFanHome(stage, user);
		});
		
		fanVar.menuItemVendor.setOnAction(e->{
			navigateFanVendor(stage, user);
		});
		
		fanVar.menuItemLogOut.setOnAction(e->{
			navigateLogin(stage);
		});
	}
	
//	===========================================================================
	
	public void InfluencerPageHandler(InfluencerHomeVar influenceVar, Stage stage) {
		influenceVar.menuItemLogOut.setOnAction(e->{
			navigateLogin(stage);
		});
	}
	
	public void VendorPageHandler(VendorVar var, Stage stage, User user){
		var.menuItemPanel.setOnAction(e->{
			navigateVendor(stage, user);
		});
		
		var.menuItemLogOut.setOnAction(e->{
			navigateLogin(stage);
		});
	}
	

	//Register Validation logic
	
	private Boolean validateUsername(RegisterVar var, String username) {
		
		if(username.isEmpty()) {
			var.error.setText("username must be filled");
			return false;
			
		} else if(!userModel.searchExistingUsername(username)) {
			var.error.setText("username already exist");
			return false;
			
		}
		
		return true;
	}
	
	private Boolean validateEmail(RegisterVar var, String email) {
		
		if(!email.contains("@")) {
			var.error.setText("email format is wrong");
			return false;
			
		} else if (!userModel.searchExistingEmail(email)) {
			var.error.setText("email already exist");
			return false;
		}
		
		return true;
		
	}
	
	private Boolean hasChar(String str) {
		for (char ch : str.toCharArray()) {
			if(Character.isLetter(ch)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	private Boolean hasDigit(String str) {
		for (char ch : str.toCharArray()) {
			if(Character.isDigit(ch)) {
				return true;
			}
		}
		
		return false;
	}
	
	private Boolean validatePassword(RegisterVar var,  String password, String confirmPassword) {
		
		if(password.length() < 6){
			var.error.setText("password must be 6 or more characters long");
			return false;
			
		} else if (!hasChar(password) || !hasDigit(password)) {
			var.error.setText("password must be alphanumeric");
			return false;
			
		} else if (!password.equals(confirmPassword)) {
			var.error.setText("confirm password doesn't match");
			return false;
		}
		
		return true;
	}
	
	//Model function
	
	public Boolean addUser(RegisterVar var,  String username, String email, String password, String confirmPassword, String role) {
		
	    Boolean usernameValid = validateUsername(var, username);
	    Boolean emailValid = validateEmail(var, email);
	    Boolean passwordValid = validatePassword(var, password, confirmPassword);
	    
	    if(usernameValid && emailValid && passwordValid) {
	    	userModel.addUser(username, email, password, role);
	    	
	    	return true;
	    }
	    
	    return false;
  
	}
	
	public Boolean deleteUserById(Integer id) {
		userModel.deleteUser(id);
		
		return true;
	}
	
	public ArrayList<User> getAllVendor(String role) {
		ArrayList<User> res = userModel.getAllUsersInRole(role);
		
		return res;
	}
	public User getUserByEmail(String email) {
		
		return userModel.getUserByEmail(email);
		
	}
		
	// Account
	private ArrayList<User> users;

	public Scene showFanAccount(Stage stage, AdminVar var) {
		
		this.users = userModel.getAllUsersInRole("Fan");
		String titleString = "Fan Accounts";

		ViewAccount view = new ViewAccount(this.users, titleString);

		view.setButtonClickHandler(() -> {
			deleteSelectedUser(view.getSelectedUsers());
			this.users = userModel.getAllUsersInRole("Fan");
			view.refreshUserList(this.users);
		});

		view.menuItemInfluencer.setOnAction(e -> {
			var.adminScene = showInfluencerAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemFan.setOnAction(e -> {
			var.adminScene = showFanAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemVendor.setOnAction(e -> {
			var.adminScene = showVendorAccount(stage, var);

			stage.setScene(var.adminScene);
		});
		
		view.menuItemLogOut.setOnAction(e -> {
			
			navigateLogin(stage);
			
		});

		return view.getViewScene();

	}

	public Scene showInfluencerAccount(Stage stage, AdminVar var) {
		
		this.users = userModel.getAllUsersInRole("Influencer");
		String titleString = "Influencer Accounts";

		ViewAccount view = new ViewAccount(this.users, titleString);

		view.setButtonClickHandler(() -> {
			deleteSelectedUser(view.getSelectedUsers());
			this.users = userModel.getAllUsersInRole("Influencer");
			view.refreshUserList(this.users);
		});

		view.menuItemInfluencer.setOnAction(e -> {
			var.adminScene = showInfluencerAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemFan.setOnAction(e -> {
			var.adminScene = showFanAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemVendor.setOnAction(e -> {
			var.adminScene = showVendorAccount(stage, var);

			stage.setScene(var.adminScene);
		});
		
		view.menuItemLogOut.setOnAction(e -> {

			navigateLogin(stage);

		});

		return view.getViewScene();
	}

	public Scene showVendorAccount(Stage stage, AdminVar var) {
		
		this.users = userModel.getAllUsersInRole("Vendor");
		String titleString = "Vendor Accounts";

		ViewAccount view = new ViewAccount(this.users, titleString);

		view.setButtonClickHandler(() -> {
			deleteSelectedUser(view.getSelectedUsers());
			this.users = userModel.getAllUsersInRole("Vendor");
			view.refreshUserList(this.users);
		});

		view.menuItemInfluencer.setOnAction(e -> {
			var.adminScene = showInfluencerAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemFan.setOnAction(e -> {
			var.adminScene = showFanAccount(stage, var);

			stage.setScene(var.adminScene);
		});

		view.menuItemVendor.setOnAction(e -> {
			var.adminScene = showVendorAccount(stage, var);

			stage.setScene(var.adminScene);
		});
		
		view.menuItemLogOut.setOnAction(e -> {

			navigateLogin(stage);

		});

		return view.getViewScene();

	}

	public void deleteSelectedUser(ObservableList<User> users) {
		for (User user : users) {
			userModel.deleteUser(user.getUserId());
		}
	}
	
}
