package com.softserve.edu.oms.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.atqc.span.AssertWrapper;
import com.softserve.edu.atqc.tools.BrowserRepository;
import com.softserve.edu.atqc.tools.DataSource;
import com.softserve.edu.atqc.tools.IBrowser;
import com.softserve.edu.atqc.tools.WebDriverUtils;
import com.softserve.edu.oms.data.DataSourceRepository;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.Urls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.logics.AdministrationPageLogic;
import com.softserve.edu.oms.logics.CreateNewUserPageLogic;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageConditions;
import com.softserve.edu.oms.pages.AdministrationPage.AdministrationPageFields;
import com.softserve.edu.oms.spans.StartApplication;

public class CreateNewUserPageTest {
	
	@DataProvider
	public Object[][] adminProvider() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.LOCAL_HOST.toString(),
				UserRepository.getAdminUser() },
		};
	}
	
	@DataProvider
	public Object[][] blankUser() {
		return new Object[][] { {
			BrowserRepository.getFirefoxByTemporaryProfile(),
			Urls.LOCAL_HOST.toString(),
			UserRepository.getBlankDefaultUser() },
		};
	}
	
	@DataProvider
	public Object[][] newUser() {
		return new Object[][] { {
			BrowserRepository.getFirefoxByTemporaryProfile(),
			Urls.LOCAL_HOST.toString(),
			UserRepository.getNewUser(),
			DataSourceRepository.getJtdsMsSqlLocalHostOms()
			},
		};
	}

	@Test(dataProvider = "adminProvider")
	public void checkIfCreateNewUserLinkIsPressentAndPageExist(IBrowser browser,
			String url, IUser adminUser) {
		
		//smoke test
		CreateNewUserPageLogic createNewUserPageLogic = StartApplication.load(browser, url)
				.successAdminLogin(adminUser).gotoAdministration().gotoCreateNewUser();
		createNewUserPageLogic.logout();
	}
	
	
	@Test(dataProvider = "blankUser")
	public void checkIfCreateNewUserPageHasAllCorrectFields(IBrowser browser,
			String url, IUser blankUser) {
		
		//precondition
		CreateNewUserPageLogic createNewUserPageLogic = StartApplication.load(browser, url)
				.successAdminLogin(UserRepository.getAdminUser()).gotoAdministration().gotoCreateNewUser();

		// Check
		AssertWrapper.get().
		forElement(createNewUserPageLogic.getCreateNewUserPage().getLoginNameField())
			.valueMatch(blankUser.getLoginName())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getFirstNameField())
			.valueMatch(blankUser.getFirstName())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getLastNameField())
			.valueMatch(blankUser.getLastName())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getPasswordField())
			.valueMatch(blankUser.getPassword())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getConfirmPasswordField())
			.valueMatch(blankUser.getPassword())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getEmailField())
			.valueMatch(blankUser.getEmail())
			.next()
		.forElement(createNewUserPageLogic.getCreateNewUserPage().getRegionSelect().getFirstSelectedOption())
			.valueMatch(blankUser.getRegion())
			.next()
//		.forElement(createNewUserPageLogic.getCreateNewUserPage().getRoleRadioButtons().toString())
//			.valueMatch(blankUser.getRole())
//			.next()
		.check();
		
		//logout
		createNewUserPageLogic.logout();
	}
	
	@Test(dataProvider = "newUser")
	public void checkCreateButtonNewUserPage(IBrowser browser,
			String url, IUser newUser, DataSource dataSource) {
		
		//preconditions
		AdministrationPageLogic administrationPageLogic = StartApplication.load(browser, url)
				  .successAdminLogin(UserRepository.getAdminUser())
				  .gotoAdministration();
		administrationPageLogic.deleteByLoginName(newUser);
	
		//Test

		CreateNewUserPageLogic createNewUserPageLogic = administrationPageLogic.gotoCreateNewUser();
		administrationPageLogic = createNewUserPageLogic.createNewUser(newUser);
		administrationPageLogic.searchByLoginName(AdministrationPageFields.LOGIN_NAME, 
				AdministrationPageConditions.STARTS_WITH, newUser);
		
		IUser searchUserFromDB = UserRepository.getUserFromDB(dataSource, newUser.getLoginName());

		AssertWrapper.get().
		forElement(administrationPageLogic.getAdministrationPage().getUsersFound())
		.valueMatch(1)
		.next()
		.forElement(administrationPageLogic.getAdministrationPage().getFirstName())
		.valueMatch(newUser.getFirstName())
		.valueMatch(searchUserFromDB.getFirstName())
		.next()
		.forElement(administrationPageLogic.getAdministrationPage().getLastName())
		.valueMatch(newUser.getLastName())
		.valueMatch(searchUserFromDB.getLastName())
		.next()
		.forElement(administrationPageLogic.getAdministrationPage().getLogin())
		.valueMatch(newUser.getLoginName())
		.valueMatch(searchUserFromDB.getLoginName())
		.next()
		.check();

		//logout
		administrationPageLogic.logout();
	}
	
	
	@Test(dataProvider = "newUser")
	public void checkCancelButtonNewUserPage(IBrowser browser,
			String url, IUser newUser, DataSource dataSource) {
		
		//preconditions
		AdministrationPageLogic administrationPageLogic = StartApplication.load(browser, url)
				  .successAdminLogin(UserRepository.getAdminUser())
				  .gotoAdministration();		
		administrationPageLogic.deleteByLoginName(newUser);
	
		//Test
		CreateNewUserPageLogic createNewUserPageLogic = administrationPageLogic.gotoCreateNewUser();
		createNewUserPageLogic.setAllUserData(newUser);
		administrationPageLogic = createNewUserPageLogic.clickCancelButton();
		administrationPageLogic.searchByLoginName(AdministrationPageFields.LOGIN_NAME, 
				AdministrationPageConditions.STARTS_WITH, newUser);
		
		AssertWrapper.get().
		forElement(administrationPageLogic.getAdministrationPage().getUsersFound())
		.valueMatch(0)
		.next()
		.check();
		
		//logout
		administrationPageLogic.logout();
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDriverUtils.get().stop();
	}
}
