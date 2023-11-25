package controller;

import database.Connect;
import model.User;

public class UserController {

	public static Boolean validateUsername(String username) {
		
		if(username.isBlank()) {
			return false;
			
		} else if(!User.searchExistingUsername(username)) {
			return false;
			
		}
		
		return true;
	}
	
	public static Boolean validateEmail(String email) {
		
		if(!email.contains("@") || !User.searchExistingEmail(email)) {
			return false;
			
		}
		
		return true;
		
	}
	
	public static Boolean validatePassword(String password, String confirmPassword) {
		
		if(password.length() < 6 || !password.matches("^[a-zA-Z0-9]+$") || !password.equals(confirmPassword)){
			return false;
			
		}
		
		return true;
	}
	
	public static Boolean addUser(String username, String email, String password, String confirmPassword, String role) {
		
	    Boolean usernameValid = validateUsername(username);
	    Boolean emailValid = validateEmail(email);
	    Boolean passwordValid = validatePassword(password, confirmPassword);
	    
	    if(usernameValid && emailValid && passwordValid) {
	    	
	    	User.addUser(username, email, password, role);
	    	
	    	return true;
	    }
	    
	    return false;
  
	}
	
}
