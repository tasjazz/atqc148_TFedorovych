package com.softserve.edu.atqc.tools;

public class BrowserRepository {

	public static IBrowser getFirefoxByTemporaryProfile() {
		return new FirefoxBrowser();
	}

	public static IBrowser getChromeByTemporaryProfile() {
		return new ChromeBrowser();
	}

}
