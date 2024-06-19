package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;
	static WebDriver staticWebDriver;

	public BrowserUtils(WebDriver driver) {
		super();
		this.driver = driver;
		staticWebDriver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void enterText(By locator, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	public void clickOn(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public String getCurrentPageUrl(String endPoint) {
		wait.until(ExpectedConditions.urlContains(endPoint));
		return driver.getCurrentUrl();
	}

	public String getElementText(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public static String takeScreenshot(String testName) {
		// typecasted driver to takescreenshot
		TakesScreenshot takeScreenshot = (TakesScreenshot) staticWebDriver;
		// now capture screenshot and store it in file formate
		File srcScreenshotFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		// create directory to store screenshots if directory is not present
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("d-MMM-YY hh:mm:ss");
		String formatedDate = formater.format(date);
		File fileDirectory = new File(System.getProperty("user.dir") + "/screenshots/" + formatedDate);
		try {
			// make directory if not present
			FileUtils.forceMkdir(fileDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create new file in png format so that screenshot file can copy into it
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + formatedDate + "/" + testName
				+ ".png";
		File destScreenshotFile = new File(screenshotPath);
		try {
			destScreenshotFile.createNewFile();
			// here copy captured source screenshot file and paste it into destination file
			// in .png format
			FileUtils.copyFile(srcScreenshotFile, destScreenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}

}
