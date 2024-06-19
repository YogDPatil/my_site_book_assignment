package com.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.BrowserUtils;

public final class ProjectListPage extends BrowserUtils {

	private final By PROJECT_LIST_LOCATOR = By.xpath("//div[@class='col-9 inner-card']");

	private WebDriver driver;
	private WebDriverWait wait;

	public ProjectListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public String getProjectPageUrl() {
		return getCurrentPageUrl("projects");
	}

	public ProjectDetailsPage selectProjectFromList() {
		List<WebElement> projectList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PROJECT_LIST_LOCATOR));
//		List<WebElement> projectList = driver.findElements(PROJECT_LIST_LOCATOR);
		for (WebElement project : projectList) {
			String projectName = project.findElement(By.xpath("h6/span")).getText();
			if (projectName.contains("Sample Bungalow Project G+1 - Detailed Estimate")) {
				project.click();
				break;
			}

		}
		return new ProjectDetailsPage(driver);
	}

}
