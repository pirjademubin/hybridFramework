package com.learnautomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties pro;
	public ConfigDataProvider() {
		File src = new File("./Config/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to load config file" + e.getMessage());
		}
	}
	
//	public String getDataFromConfig(String keyToSearch) {
//		return pro.getProperty(keyToSearch);
//	}
	
	public String getBrowser() {
		return pro.getProperty("Browser");
	}
	
	public String getURL() {
		return pro.getProperty("qaURL");
	}
	
}
