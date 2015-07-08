package com.softserve.edu.oms.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		// { BrowserRepository.getChromeByTemporaryProfile() }
		};
	}
	
	@Test(dataProvider = "adminProvider")
	public void checkIfCreateNewUserLinkIsPressent(IBrowser browser,
			String url, IUser adminUser) {
		
		CreateNewUserPageLogic createNewUserPageLogic = StartApplication.load(browser, url)
				.successAdminLogin(adminUser).gotoAdministration().gotoCreateNewUser();
		
		createNewUserPageLogic.createNewUser(adminUser);
		
		createNewUserPageLogic.logout();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDriverUtils.get().stop();
	}
}
