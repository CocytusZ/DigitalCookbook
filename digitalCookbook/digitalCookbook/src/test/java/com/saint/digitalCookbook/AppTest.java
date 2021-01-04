package com.saint.digitalCookbook;

import com.saint.digitalCookbook.dao.IngredientDBTest;
import com.saint.digitalCookbook.dao.RecipeBasicInfoDBTest;
import com.saint.digitalCookbook.dao.RecipeDetailDBTest;
import com.saint.digitalCookbook.manager.RecipeManagerTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		// All test cases of DAO
		suite.addTestSuite(IngredientDBTest.class);
		suite.addTestSuite(RecipeBasicInfoDBTest.class);
		suite.addTestSuite(RecipeDetailDBTest.class);

		// Test case of RecipeManager
		suite.addTestSuite(RecipeManagerTest.class);
		
		return suite;
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp()
	{
		assertTrue( true );
	}
}
