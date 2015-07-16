package com.softserve.edu.atqc.controls;

import com.softserve.edu.atqc.tools.WebDriverUtils;

public class Alert {
	
	public static void accept(){
		WebDriverUtils.get().acceptAlert();
	}
	
	public static void dismiss(){
		WebDriverUtils.get().dismissAlert();
	}

}