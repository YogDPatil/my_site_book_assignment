
package com.runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class Runner {

	public static String componant, testType, browser;

	public static void main(String[] args) {

		componant = args[0];

		TestNG testNg = new TestNG();

		XmlSuite xmlSuit = new XmlSuite();
		xmlSuit.setName(componant + " " + testType + " Suit");

		XmlTest xmlTest = new XmlTest(xmlSuit);
		xmlTest.setName("App Test");
		xmlTest.setParallel(ParallelMode.TESTS);
		xmlTest.setThreadCount(5);

		String componantData = componant.toLowerCase();

		List<XmlPackage> packageList = new ArrayList<>();
		XmlPackage xmlPackage = new XmlPackage("com." + componantData + ".tests");
		packageList.add(xmlPackage);

		xmlTest.setPackages(packageList);

		xmlTest.addParameter("browser", browser);

		List<XmlTest> testList = new ArrayList<>();
		testList.add(xmlTest);

		xmlSuit.setTests(testList);

		List<XmlSuite> suitList = new ArrayList<>();
		suitList.add(xmlSuit);

		testNg.setXmlSuites(suitList);
		testNg.run();
	}

}
