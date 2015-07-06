package com.softserve.edu.atqc.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser implements IBrowser {

	public WebDriver getWebDriver() {
		return new FirefoxDriver();
	}

	public String getWebDriverName() {
		return this.getClass().getName();
		//return "FirefoxBrowser";
	}

	public void quit() {
	}

}
