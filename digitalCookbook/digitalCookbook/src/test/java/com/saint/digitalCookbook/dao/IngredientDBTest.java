package com.saint.digitalCookbook.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import com.saint.digitalCookbook.constant.UnitEnum;
import com.saint.digitalCookbook.entity.Ingredient;

import junit.framework.TestCase;

/**
 * Test code for IngredientMapper
 * @author Zhouyao Yu
 * @date 2020年10月21日
 */
public class IngredientDBTest extends TestCase {

	private SqlSession session;
	private Ingredient expected;
	private Ingredient newExpected;
	private Ingredient extraExpected;
	private final int EXPECTED_INGREDIENT_NUM = 2;

	@Override
	protected void setUp() throws Exception {
		// 根据 mybatis-config.xml 配置的信息得到 sqlSessionFactory
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 然后根据 sqlSessionFactory 得到 session
		session = sqlSessionFactory.openSession();

		// Clear the table
		clearTable();

		// New a expected instance
		expected = new Ingredient("1", "1", "TestIngredient", 1, UnitEnum.NONE.toString());
		newExpected = new Ingredient("1", "1", "UpdateIngredient", 2, UnitEnum.NONE.toString());
		extraExpected = new Ingredient("1", "2", "ExtraIngredient", 2, UnitEnum.NONE.toString());
	}
	
	@BeforeEach
	public void clearTable() {
		session.delete("deleteAllIngredient");
		session.commit();
	}
	
	@AfterAll
	public void closeSession() {
		session.close();
	}

	@Test
	public void testSelectIngredient() {
		session.insert("insertIngredient", expected);
		session.commit();

		Ingredient actual;
		actual = session.selectOne("selectIngredient", expected.getIngredientId());

		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testInsertIngredient() {
		session.insert("insertIngredient", expected);
		session.commit();

		Ingredient actual = session.selectOne("selectIngredient", expected.getIngredientId());
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testUpdateIngredient() {
		session.insert("insertIngredient", expected);
		session.commit();

		session.update("updateIngredient", newExpected);
		session.commit();

		Ingredient actual;
		actual = session.selectOne("selectIngredient", newExpected.getIngredientId());

		assertEquals(newExpected.toString(), actual.toString());
	}

	@Test
	public void testDeleteIngredient() {
		session.insert("insertIngredient", expected);
		session.commit();

		Ingredient actual;
		actual = session.selectOne("selectIngredient", expected.getIngredientId());

		session.delete("deleteIngredient");
		session.commit();

		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testSelectIngredientsByRecipe() {
		session.insert("insertIngredient", expected);
		session.commit();

		session.insert("insertIngredient", extraExpected);
		session.commit();

		List<Ingredient> tmp = session.selectList("selectIngredientsByRecipe", expected.getRecipeId());
		List<String> actual = new ArrayList<String>();
		for(Ingredient in : tmp) {
			actual.add(in.toString());
		}

		assertTrue(actual.contains(expected.toString()));
		assertTrue(actual.contains(expected.toString()));
	}

	@Test
	public void testDeleteIngredientByRecipe() {
		session.insert("insertIngredient", expected);
		session.commit();
		
		session.insert("insertIngredient", extraExpected);
		session.commit();
		
		session.delete("deleteIngredientsByRecipe", expected.getRecipeId());
		session.commit();
		
		int actual = session.selectOne("countIngredientsByRecipe", expected.getRecipeId());
		assertEquals(actual, 0);
	}

	@Test
	public void testCountIngredientsByRecipe() {
		session.insert("insertIngredient", expected);
		session.commit();

		session.insert("insertIngredient", extraExpected);
		session.commit();

		int actual = session.selectOne("countIngredientsByRecipe", expected.getRecipeId());

		assertEquals(EXPECTED_INGREDIENT_NUM, actual);
	}


}
