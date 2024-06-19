package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ui.pages.ProjectDetailsPage;
import com.ui.pages.ProjectListPage;
import com.ui.pages.QuotationPage;

public final class ProjectListPageTest extends BaseTest {
	private ProjectDetailsPage projectDetailsPage;

	@BeforeClass()
	public void goToProjectDetailsPage() {
		projectDetailsPage = loginPage.doLogin().selectProjectFromList();
	}

	@Test(description = "To test user can select project from uiand redirect project details page")
	public void testUserRedirectProjectDetailsPageAfterSelectingProject() {
		AssertJUnit.assertEquals(projectDetailsPage.getProjectTitle().trim(),
				"Sample Bungalow Project G+1 - Detailed Estimate");
	}
}
