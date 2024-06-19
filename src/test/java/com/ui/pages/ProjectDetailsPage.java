package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.BrowserUtils;

public final class ProjectDetailsPage extends BrowserUtils {

	private final By PROJECT_TITILE_LOCATOR = By.xpath("//h3[contains(text(),'Detailed Estimate')]");
	private final By DETAILED_ESTIMATE_LINK_LOCATOR = By.xpath("//p[contains(text(),'Detailed Estimate')]/..");

	private WebDriver driver;

	public ProjectDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public String getProjectTitle() {
		return getElementText(PROJECT_TITILE_LOCATOR);
	}
	
	public QuotationPage clickOnDetailedEstimateLink() {
		clickOn(DETAILED_ESTIMATE_LINK_LOCATOR);
		return new QuotationPage(driver);
	}

}
