package com.saint.digitalCookbook.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import com.saint.digitalCookbook.entity.RecipeDetail;

import junit.framework.TestCase;

/**
 * Test code for recipeDetailMapper
 * @author Zhouyao Yu
 * @date 2020年10月21日
 */
public class RecipeDetailDBTest extends TestCase{
	private SqlSession session;
	private RecipeDetail expected;
	private RecipeDetail newExpected;

	@Override
	protected void setUp() throws Exception {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
		
		// Clear the table
		clearTable();

		// Initial instances
		expected = new RecipeDetail("1", "2", "5", "chop, boil and wash");
		newExpected = new RecipeDetail("1", "5", "10", "more chop, boil and wash");
	}
	
	@BeforeEach
	public void clearTable() {
		session.delete("deleteAllRecipeDetails");
		session.commit();
	}
	
	@AfterAll
	public void closeSession() {
		session.close();
	}
	
	@Test
	public void testSelectRecipeDetail() {
		session.insert("insertRecipeDetail", expected);
		session.commit();
		
		RecipeDetail actual = session.selectOne("selectRecipeDetail",expected.getRecipeId());
		
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	public void testInsertRecipeDetail() {
		session.insert("insertRecipeDetail", expected);
		session.commit();
		
		RecipeDetail actual = session.selectOne("selectRecipeDetail",expected.getRecipeId());
		
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	public void testUpdateRecipeDetail() {
		session.insert("insertRecipeDetail", expected);
		session.commit();
		
		session.update("updateRecipeDetail", newExpected);
		session.commit();
		
		RecipeDetail actual = session.selectOne("selectRecipeDetail",newExpected.getRecipeId());
		
		assertEquals(newExpected.toString(), actual.toString());
	}
	
	@Test
	public void testDeleteRecipeDetail() {
		session.insert("insertRecipeDetail", expected);
		session.commit();
		
		RecipeDetail actual = session.selectOne("selectRecipeDetail",expected.getRecipeId());
		
		session.delete("deleteRecipeDetail", expected.getRecipeId());
		session.commit();
		
		assertEquals(expected.toString(), actual.toString());
	}
}
