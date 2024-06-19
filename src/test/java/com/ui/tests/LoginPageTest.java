package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.constant.Env;
import com.ui.pages.LoginPage;
import com.ui.pages.ProjectListPage;
import com.utils.TestUtils;

public final class LoginPageTest extends BaseTest {
	private ProjectListPage projectListPage;

	@BeforeClass()
	public void goToProjectListPage() {
		projectListPage = loginPage.doLogin();
	}

	@Test
	public void doLogin() {
		AssertJUnit.assertEquals(projectListPage.getProjectPageUrl(),
				TestUtils.getValueFromPropertiesFile(Env.QA, "BASE_URL") + "/projects?type=active");
	}
}
