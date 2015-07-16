package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageConditions;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageFields;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.pages.LoginPage;

public class AdministrationPageLogic {
	// Elements
	private AdministrationPage administrationPage;

	public AdministrationPageLogic(AdministrationPage administrationPage) {
		this.administrationPage = administrationPage;
	}

	// getters

	public AdministrationPage getAdministrationPage() {
		return administrationPage;
	}

	// business

	public CreateNewUserPageLogic gotoCreateNewUser() {
		this.administrationPage.getCreateNewUser().click();
		return new CreateNewUserPageLogic(new CreateNewUserPage());
		// return new CreateNewUserPageLogic();
	}

	public void searchByLoginName(AdministrationPageFields field,
			AdministrationPageConditions condition, IUser user) {
		this.administrationPage.selectColumnFields(field);
		this.administrationPage.selectMatchConditions(condition);
		this.administrationPage.searchFieldSendKeys(user.getLoginName());
		// Initialize AJAX Elements
		 this.administrationPage.resetTable(user.getLoginName()); //yaroslVer
		this.administrationPage.resetTable(); // my version
	}

	public void deleteByLoginName(IUser user) {
		searchByLoginName(AdministrationPageFields.LOGIN_NAME,
				AdministrationPageConditions.STARTS_WITH, user);
			if (getNumberOfUsersFound() > 0) {
				System.out.println("Users =  " + getNumberOfUsersFound());
				this.administrationPage.deleteClick();
				this.administrationPage.alertAccept();
				this.administrationPage.resetTable();
			} else {System.out.println("Users = " + getNumberOfUsersFound());};
			this.administrationPage.resetTable();
			this.administrationPage.resetCreateNewUserLink();
	}
	
	public Integer getNumberOfUsersFound() {
		return Integer.parseInt(this.administrationPage.getUsersFound()
				.getText());
	}

	public LoginPageLogic logout() {
		this.administrationPage.getLogout().click();
		return new LoginPageLogic(new LoginPage());
	}

}
