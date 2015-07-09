package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.pages.CreateNewUserPage.CreateNewUserPageRegion;
import com.softserve.edu.oms.pages.LoginPage;

public class CreateNewUserPageLogic {
	
	// Elements
	private CreateNewUserPage createNewUserPage;
	
	public CreateNewUserPageLogic (CreateNewUserPage createNewUserPage) {
		this.createNewUserPage = createNewUserPage;
	}
	
	// getters
	
	public CreateNewUserPage getCreateNewUserPage(){
		return createNewUserPage;
	}
	
	public LoginPageLogic logout(){
		this.createNewUserPage.getLogout().click();
		return new LoginPageLogic(new LoginPage());
	}
	
	// setters
	
	public void setLoginNameField(String text){
		this.createNewUserPage.getLoginNameField().sendKeys(text);
	}
	public void setFirstNameField(String text){
		this.createNewUserPage.getFirstNameField().sendKeys(text);
	}
	public void setLastNameField(String text){
		this.createNewUserPage.getLastNameField().sendKeys(text);
	}
	public void setPasswordField(String text){
		this.createNewUserPage.getPasswordField().sendKeys(text);
	}
	public void setConfirmPasswordField(String text){
		this.createNewUserPage.getConfirmPasswordField().sendKeys(text);
	}
	public void setEmailField(String text){
		this.createNewUserPage.getEmailField().sendKeys(text);
	}
	public void setRegionSelect(CreateNewUserPageRegion region){
		this.createNewUserPage.selectRegion(region);
	}
	
	public void clickCreateButton(){
		this.createNewUserPage.getCreateButton().click();
	}
	
	public void setAllUserData(IUser user){
		setLoginNameField(user.getLoginName());
		setFirstNameField(user.getFirstName());
		setLastNameField(user.getLastName());
		setPasswordField(user.getPassword());
		setConfirmPasswordField(user.getPassword());
		setEmailField(user.getEmail());
		setRegionSelect(CreateNewUserPageRegion.WEST);
		//TODO change it
	}
	
	public void createNewUser(IUser user){
		setAllUserData(user);
		clickCreateButton();
	}
}
