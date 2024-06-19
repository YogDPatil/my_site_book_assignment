package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constant.Env;

public class TestUtils {
	private static String filePath;

	public static String getValueFromPropertiesFile(Env env, String keyText) {
		try {
			String envName = env.toString().toLowerCase();
			filePath = System.getProperty("user.dir") + "/src/test/resources/config/" + envName + "Cred.properties";
			System.out.println(filePath);
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			Properties properties = new Properties();
			properties.load(fileReader);
			return properties.getProperty(keyText);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
