package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.Alert;
import com.softserve.edu.atqc.controls.ILabel;
import com.softserve.edu.atqc.controls.ILink;
import com.softserve.edu.atqc.controls.ISelect;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.controls.Label;
import com.softserve.edu.atqc.controls.Link;
import com.softserve.edu.atqc.controls.Select;
import com.softserve.edu.atqc.controls.TextField;

public class AdministrationPage {
	public static enum AdministrationPageFields {
		ALL_COLUMNS("All Columns"),
		FIRST_NAME("First Name"),
		LAST_NAME("Last Name"),
		ROLE("Role"),
		LOGIN_NAME("Login Name");
		private String field;

		private AdministrationPageFields(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	public static enum AdministrationPageConditions {
		EQUALS("equals"),
		NOT_EQUALS_TO("not equals to"),
		STARTS_WITH("starts with"),
		CONTAINS("contains"),
		DOES_NOT_CONTAIN("does not contain");
		private String field;

		private AdministrationPageConditions(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	private class AdministrationPageUIMap {
//		public final ILink createNewUser;
		public final ISelect field;
		public final ISelect condition;
		public final ITextField searchField;
		public final ILink logout;

		public AdministrationPageUIMap() {
			this.searchField = TextField.getById("searchField");
			this.field = Select.getById("field");
			this.condition = Select.getById("condition");
//			this.createNewUser = Link.getByPartialLinkText("Create New User");
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
		}
	}

	private class AdministrationPageUIMapAjax {
		public final ILabel usersFound;
		public final ILabel firstName;
		public final ILabel lastName;
		public final ILabel login;
		public final ILink delete;
		public  ILink createNewUser;
		public final ILink logout;

		public AdministrationPageUIMapAjax() {
			this.usersFound = Label.getById("usersFound");
			if (Integer.parseInt(usersFound.getText()) > 0) {
				this.firstName = Label.getByXpath("//tbody/tr[1]/td[1]");
				this.lastName = Label.getByXpath("//tbody/tr[1]/td[2]");
				this.login = Label.getByXpath("//tbody/tr[1]/td[3]");
				this.delete = Link.getByXpath("//tbody/tr[1]/td[7]/a");
				this.createNewUser = Link.getByPartialLinkText("Create New User");
				this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
			} else {
				this.firstName = Label.getByXpath("//thead/tr[1]/th[1]");
				this.lastName = Label.getByXpath("//thead/tr[1]/th[2]");
				this.login = Label.getByXpath("//thead/tr[1]/th[3]");
				this.delete = Link.getByXpath("//thead/tr[1]/th[1]");
				this.createNewUser = Link.getByPartialLinkText("Create New User");
				this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
			}
		}

		public AdministrationPageUIMapAjax(String login) {
			this.usersFound = Label.getById("usersFound");
			//
			this.login = Label.getByXpath("//tbody//td[3][text()='" + login + "']");
			this.lastName = Label.getByXpath("//tbody//td[3][text()='" + login + "']/preceding-sibling::td[1]");
			this.firstName = Label.getByXpath("//tbody//td[3][text()='" + login + "']/preceding-sibling::td[2]");
			this.delete = Link.getByXpath("//tbody//td[3][text()='" + login + "']/following-sibling::td[4]/a");
			this.createNewUser = Link.getByPartialLinkText("Create New User");
			this.logout = Link.getByXpath("//a[@href='/OMS/logout.htm']");
		}

	}

    // Elements
    private AdministrationPageUIMap controls;
	// AJAX Elements
    private AdministrationPageUIMapAjax controlsAjax;
	// Alert Elements
	//private IAlertLight controlsAlert = null;

    public AdministrationPage() {
        controls = new AdministrationPageUIMap();
        controlsAjax = new AdministrationPageUIMapAjax();
    }

    // getters controls

	public ILink getCreateNewUser() {
		return this.controlsAjax.createNewUser;
	}

	public ISelect getField() {
		return this.controls.field;
	}

	public ISelect getCondition() {
		return this.controls.condition;
	}

	public ITextField getSearchField() {
		return this.controls.searchField;
	}

	public ILink getLogout() {
		return this.controlsAjax.logout;
	}

    // getters controlsAjax

	public ILabel getUsersFound() {
		return this.controlsAjax.usersFound;
	}

	public ILabel getFirstName() {
		return this.controlsAjax.firstName;
	}

	public ILabel getLastName() {
		return this.controlsAjax.lastName;
	}

	public ILabel getLogin() {
		return this.controlsAjax.login;
	}

	public ILink getDelete() {
		return this.controlsAjax.delete;
	}

    // getters controlsAlert

	//public IAlert getAlert() {
	//	if (this.controlsAlert != null) {
	//	return this.controlsAlert;
	//	}
	//}

	// setters controls

	public void createNewUserClick() {
		this.controlsAjax.createNewUser.click();
	}

	public void selectColumnFields(AdministrationPageFields field) {
		this.controls.field.selectByVisibleText(field.toString());
	}

	public void selectMatchConditions(AdministrationPageConditions condition) {
		this.controls.condition.selectByVisibleText(condition.toString());
	}

	public void searchFieldClear() {
		this.controls.searchField.clear();
	}

	public void searchFieldClick() {
		this.controls.searchField.click();
	}
	
	public void searchFieldSendKeys(String text) {
		this.controls.searchField.clear();
		this.controls.searchField.sendKeys(text);
	}

	public void logoutClick() {
		this.controlsAjax.logout.click();
	}
	
	// setters controlsAjax

	public void resetTable() {
//		if (this.controlsAjax.firstName.isStalenessOf()) {
			controlsAjax = new AdministrationPageUIMapAjax();
//		}
	}
	
	public void resetTable(String login) {
//		if (this.controlsAjax.firstName.isStalenessOf()) {
			controlsAjax = new AdministrationPageUIMapAjax(login);
//		}
	}
	
	public void resetCreateNewUserLink(){
		this.controlsAjax.createNewUser = Link.getByPartialLinkText("Create New User");
	}
	
	public void deleteClick() {
		resetTable();
		System.out.println("Delete click");
		this.controlsAjax.delete.click();
		// this.controlsAlert = new Alert();
	}

	// setters controlsAlert

	public void alertAccept() {
		Alert.accept();
//		if (this.controlsAlert != null) {
//		this.controlsAlert.click();
//		this.controlsAlert = null;
//        controls = new AdministrationPageUIMap();
//        controlsAjax = new AdministrationPageUIMapAjax();
//	}
	}

	public void alertDismiss() {
		Alert.dismiss();
//		if (this.controlsAlert != null) {
//			this.controlsAlert.click();
//			this.controlsAlert = null;
//		}
	}	

}
