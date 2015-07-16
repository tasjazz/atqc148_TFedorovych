package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.Button;
import com.softserve.edu.atqc.controls.IButton;
import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.IRadioButtonGroup;
import com.softserve.edu.atqc.controls.ISelect;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.controls.Label;
import com.softserve.edu.atqc.controls.Link;
import com.softserve.edu.atqc.controls.RadioButtonGroup;
import com.softserve.edu.atqc.controls.Select;
import com.softserve.edu.atqc.controls.TextField;

public class CreateNewUserPage {
	
	public static enum CreateNewUserPageRegion {
        EAST("East"), NORTH("North"), SOUTH("South"), WEST("West");
        private String field;

        private CreateNewUserPageRegion(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }
	
	public static enum LoginNameValidators{
		ALREADY_IN_USE("already in use"),
		LOGIN_NAME_IS_TOO_LONG("Login name is too long"),
		LOGIN_CANNOT_BE_BLANK("Login cannot be blank"),
		LOGIN_NAME_CANNOT_CONTAIN_DIGITS("Login name cannot contain digits");
		
		private String field;
		
		private LoginNameValidators(String field) {
			this.field = field;
		}
		
		@Override
		public String toString() {
			return this.field;
		}
	}
	
	public static enum EmailValidators{
		
		YOU_SHOULD_USE_VALID_EMAIL_ADDRESS("You should use valid email address");
		
		private String field;

		private EmailValidators(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}
	
	private class CreateNewUserPageUIMap {
		public final ITextField loginNameField;
		public final ITextField firstNameField;
		public final ITextField lastNameField;
		public final ITextField passwordField;
		public final ITextField confirmPasswordField;
		public final ITextField emailField;
		public final ISelect regionSelect;
		public final IRadioButtonGroup roleRadioButtons;
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
			this.regionSelect = Select.getById("regionID");
			this.roleRadioButtons = RadioButtonGroup.getByName("roleID");
			this.createButton = Button.getByXpath("/html/body/div/div/div[3]/div/div/form/input[4]");
			this.cancelButton = Button.getByXpath("/html/body/div/div/div[3]/div/div/form/input[5]");
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
		}
	}
	
	private class CreateNewUserPageUIMapValidators {
		public final ILabel loginNameValidator;
		public final ILabel firstNameValidator;
		public final ILabel lastNameValidator;
		public final ILabel passwordValidator;
		public final ILabel confirmPasswordValidator;
		public final ILabel emailNameValidator;
		
		public CreateNewUserPageUIMapValidators (){
			
			this.loginNameValidator = Label.getPresentById("nameError");
			this.firstNameValidator = Label.getPresentById("firstNameError");
			this.lastNameValidator = Label.getPresentById("lastNameError");
			this.passwordValidator = Label.getPresentById("passwordError");
			this.confirmPasswordValidator = Label.getPresentById("confirmError");
			this.emailNameValidator = Label.getPresentById("emailError");
			
			System.out.println("++++++++Allert: "+ loginNameValidator.getText());
			System.out.println("++++++++Allert: "+ firstNameValidator.getText());
			System.out.println("++++++++Allert: "+ lastNameValidator.getText());
			System.out.println("++++++++Allert: "+ passwordValidator.getText());
			System.out.println("++++++++Allert: "+ confirmPasswordValidator.getText());
			System.out.println("++++++++Allert: "+ emailNameValidator.getText());
								
		}
	}
	
	// Elements
	private CreateNewUserPageUIMap controls;
	// Validator Elements
	private CreateNewUserPageUIMapValidators controlsValidator;

	public CreateNewUserPage() {
		 controls = new CreateNewUserPageUIMap();
	}
	
	public CreateNewUserPageUIMapValidators getCreateNewUserPageUIMapValidators(){
		return new CreateNewUserPageUIMapValidators();
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
	public ISelect getRegionSelect(){
		return this.controls.regionSelect;
	}
	public IRadioButtonGroup getRoleRadioButtons(){
		return this.controls.roleRadioButtons;
	}
	
	//setters controls
	public void selectRegion(CreateNewUserPageRegion region){
		this.controls.regionSelect.selectByVisibleText(region.toString());
	}
	
}
