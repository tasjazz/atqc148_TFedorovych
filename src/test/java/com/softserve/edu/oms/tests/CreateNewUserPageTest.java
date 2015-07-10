package com.softserve.edu.oms.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.atqc.span.AssertWrapper;
import com.softserve.edu.atqc.tools.BrowserRepository;
import com.softserve.edu.atqc.tools.IBrowser;
import com.softserve.edu.atqc.tools.WebDriverUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.Urls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.logics.CreateNewUserPageLogic;
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
	

	@Test(dataProvider = "adminProvider")
	public void checkIfCreateNewUserLinkIsPressentAndPageExist(IBrowser browser,
			String url, IUser adminUser) {
		
		//smoke test
		CreateNewUserPageLogic createNewUserPageLogic = StartApplication.load(browser, url)
				.successAdminLogin(adminUser).gotoAdministration().gotoCreateNewUser();
		createNewUserPageLogic.logout();
	}
	
	@DataProvider
	public Object[][] blankUser() {
		return new Object[][] { {
				BrowserRepository.getFirefoxByTemporaryProfile(),
				Urls.LOCAL_HOST.toString(),
				UserRepository.getBlankDefaultUser() },
		};
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
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDriverUtils.get().stop();
	}
}
