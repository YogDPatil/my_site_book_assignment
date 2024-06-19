package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.QuotationPage;


public final class ProjectDetailsPageTest extends BaseTest {

	private QuotationPage quotationPage;

	@BeforeClass()
	public void goToQuotationPage() {
		quotationPage = loginPage.doLogin().selectProjectFromList().clickOnDetailedEstimateLink();
	}

	@Test(description = "To test user redirects quotation page on clicking on detailed estiomate link")
	public void testUserRedirectsQuatationPageByClickingDetailedEstimate() {
		AssertJUnit.assertEquals(quotationPage.getQuotationPageTitle(), "Quotation");
	}

	@Test(description = "To test quotation details are showing correctly")
	public void testQuotationDetails() throws Exception {
		quotationPage.toValidateProjectQuotation();
	}
}
