package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.Button;
import com.softserve.edu.atqc.controls.IButton;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.controls.Link;
import com.softserve.edu.atqc.controls.TextField;

public class CreateNewUserPage {
	private class CreateNewUserPageUIMap {
		public final ITextField loginNameField;
		public final ITextField firstNameField;
		public final ITextField lastNameField;
		public final ITextField passwordField;
		public final ITextField confirmPasswordField;
		public final ITextField emailField;
		public final IButton createButton;
		public final IButton cancelButton;
		public final ILink logout;
		

		public CreateNewUserPageUIMap() {
			this.loginNameField = TextField.getById("login");
			this.firstNameField = TextField.getById("firstName");
			this.lastNameField = TextField.getById("lastName");
			this.passwordField = TextField.getById("password");
			this.confirmPasswordField = TextField.getById("confirmPassword");
			this.emailField = TextField.getById("email");
			this.createButton = Button.getByXpath("/html/body/div/div/div[3]/div/div/form/input[4]");
			this.cancelButton = Button.getByXpath("/html/body/div/div/div[3]/div/div/form/input[5]");
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
			
		}
	}
	
	// Elements
	private CreateNewUserPageUIMap controls;

	public CreateNewUserPage() {
		 this.controls = new CreateNewUserPageUIMap();
	}
	
	// getters controls
	
	public ILink getLogout() {
		return this.controls.logout;
	}
	public ITextField getLoginNameField(){
		return this.controls.loginNameField;
	}
	public ITextField getFirstNameField(){
		return this.controls.firstNameField;
	}
	public ITextField getLastNameField(){
		return this.controls.lastNameField;
	}
	public ITextField getPasswordField(){
		return this.controls.passwordField;
	}
	public ITextField getConfirmPasswordField(){
		return this.controls.confirmPasswordField;
	}
	public ITextField getEmailField(){
		return this.controls.emailField;
	}
	public IButton getCreateButton(){
		return this.controls.createButton;
	}
	public IButton getCancelButton(){
		return this.controls.cancelButton;
	}
}
