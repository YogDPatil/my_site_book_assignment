package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constant.Env;
import com.utils.BrowserUtils;
import com.utils.TestUtils;

public final class LoginPage extends BrowserUtils {

	private final By MOBILE_FIELD_TEXTBOX_LOCATOR = By.id("mobileNumber");
	private final By CONTINUE_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Continue')]");
	private final By PASSWORD_FIELD_TEXTBOX_LOCATOR = By.id("password");
	private final By LOGIN_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Login')]");

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public ProjectListPage doLogin() {
		enterText(MOBILE_FIELD_TEXTBOX_LOCATOR, TestUtils.getValueFromPropertiesFile(Env.QA, "MOB_NUMBER"));
		clickOn(CONTINUE_BUTTON_LOCATOR);
		enterText(PASSWORD_FIELD_TEXTBOX_LOCATOR, TestUtils.getValueFromPropertiesFile(Env.QA, "PASS"));
		clickOn(LOGIN_BUTTON_LOCATOR);
		return new ProjectListPage(driver);
	}

}
