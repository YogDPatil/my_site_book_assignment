package com.ui.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.constant.Env;
import com.ui.pages.LoginPage;
import com.utils.TestUtils;

public abstract class BaseTest {

	protected WebDriver driver;
	protected LoginPage loginPage;

	@BeforeTest
    public void setUp() {
        try {
          
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            String baseUrl = TestUtils.getValueFromPropertiesFile(Env.QA, "BASE_URL");
            driver.get(baseUrl + "/auth/signin");
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver");
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
