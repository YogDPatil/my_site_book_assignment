package com.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.BrowserUtils;

public final class QuotationPage extends BrowserUtils {

	private final By QUOTATION_PAGE_TITLE_LOCATOR = By.xpath("//h4[contains(text(),'Quotation')]");
	private final By WORK_TYPE_TOTAL_PRICE_LOCATOR = By.xpath("//tbody/tr[contains(@class,'add-bottom-border')]");
	private final By TOTAL_PROJECT_COST_PRICE_LOCATOR = By.xpath("//h2[@id='total-amount']");

	// tbody/tr/td[@id='total']

	private WebDriver driver;
	private WebDriverWait wait;

	public QuotationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public String getQuotationPageTitle() {
		return getElementText(QUOTATION_PAGE_TITLE_LOCATOR);
	}

	public void toValidateProjectQuotation() throws Exception {

		float workTypeTotalCost;
		float totalCost = 0.0f;

		List<WebElement> workTypeTotalCostList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(WORK_TYPE_TOTAL_PRICE_LOCATOR));
		for (WebElement workTypeCost : workTypeTotalCostList) {
			String workTypeValue = workTypeCost.findElement(By.xpath("td[@id='total']")).getText();
			String amount = workTypeValue.replaceAll("[^\\d.,]", "");
			String finalAmount = amount.replace(",", "");
			workTypeTotalCost = Float.parseFloat(finalAmount);
			totalCost = totalCost + workTypeTotalCost;

		}
		System.out.println("******* " + totalCost);

		String projectCost = getElementText(TOTAL_PROJECT_COST_PRICE_LOCATOR);
		String ProjectAmount = projectCost.replaceAll("[^\\d.,]", "");
		String totalProjectAmount = ProjectAmount.replace(",", "");
		float finalProjectAmount = Float.parseFloat(totalProjectAmount);
		System.out.println("$$$$$$$ " + finalProjectAmount);

		if (totalCost == finalProjectAmount) {
			System.out.println("Quotation data is correct");
		} else {
			throw new Exception("Quotation data is not matching");
		}
	}

}
