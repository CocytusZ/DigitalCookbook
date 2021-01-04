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

import com.saint.digitalCookbook.constant.RecipeTagEnum;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;

import junit.framework.TestCase;

/**
 * Test code for RecipeBasicInfoMapper
 * @author Zhouyao Yu
 * @date 2020年10月21日
 */
public class RecipeBasicInfoDBTest extends TestCase {
	private SqlSession session;
	private RecipeBasicInfo expected;
	private RecipeBasicInfo newExpected;
	private RecipeBasicInfo extraExpected;
	private RecipeBasicInfo dislikeExpected;
	private RecipeBasicInfo subscribeExpected;
	private final String RECIPE_TAG = RecipeTagEnum.MEAT_DISH.toString();
	private final String RECIPE_KEY_WORD = "Meat";
	private final int EXPECTED_RECIPE_NUM = 2;

	@Override
	protected void setUp() throws Exception {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();

		// Clear the table
		clearTable();
		session.delete("deleteAllRecipeBasicInfo");
		session.commit();


		// Initial instances
		expected = new RecipeBasicInfo("1", "Boiled Meat", RecipeTagEnum.MEAT_DISH.toString(), "http://localhost:8080/Boiled_Meat", false, false, 0);
		newExpected = new RecipeBasicInfo("1", "Salad", RecipeTagEnum.VEGETAVLE_DISH.toString(), "http://localhost:8080/Salad", false, false, 0);
		extraExpected =  new RecipeBasicInfo("2", "Fried Meat", RecipeTagEnum.MEAT_DISH.toString(), "http://localhost:8080/Fried_Meat", false, false, 0);
		dislikeExpected = new RecipeBasicInfo("1", "Boiled Meat", RecipeTagEnum.MEAT_DISH.toString(), "http://localhost:8080/Boiled_Meat", true, false, 0);
		subscribeExpected =  new RecipeBasicInfo("2", "Boiled Meat", RecipeTagEnum.MEAT_DISH.toString(), "http://localhost:8080/Boiled_Meat", false, true, 0);

	}

	@BeforeEach
	public void clearTable() {
		session.delete("deleteAllRecipeBasicInfo");
		session.commit();
	}

	@AfterAll
	public void closeSession() {
		session.close();
	}

	@Test
	public void testSelectRecipeBasicInfo() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		RecipeBasicInfo actual = session.selectOne("selectRecipeBasicInfoById", expected.getRecipeId());

		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testInsertRecipeBasicInfo() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		RecipeBasicInfo actual = session.selectOne("selectRecipeBasicInfoById", expected.getRecipeId());

		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void testUpdateRecipeBasicInfo() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.update("updateRecipeBasicInfo", newExpected);
		session.commit();

		RecipeBasicInfo actual = session.selectOne("selectRecipeBasicInfoById", newExpected.getRecipeId());

		assertEquals(newExpected.toString(), actual.toString());
	}

	@Test
	public void testDeleteRecipeBasicInfo() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.delete("deleteRecipeBasicInfo", expected);
		session.commit();

		int actual = session.selectOne("countRecipeBasicInfos", expected.getRecipeId());

		assertEquals(0, actual);
	}

	@Test
	public void testSelectRecipeBasicInfosByName() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.insert("insertRecipeBasicInfo", extraExpected);
		session.commit();

		List<RecipeBasicInfo> tmp = session.selectList("selectRecipeBasicInfosByName", RECIPE_KEY_WORD);
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}

		assertTrue(actual.contains(expected.toString()));
		assertTrue(actual.contains(expected.toString()));
	} 

	@Test
	public void testSelectRecipeBasicInfosByTag() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.insert("insertRecipeBasicInfo", extraExpected);
		session.commit();
		
		List<RecipeBasicInfo> tmp = session.selectList("selectRecipeBasicInfosByTag", RECIPE_TAG);
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}
		
		assertTrue(actual.contains(expected.toString()));
		assertTrue(actual.contains(extraExpected.toString()));
	}

	@Test
	public void testSelectRecipeBasicInfosByRecommend() {
		expected.setClickTimes(10);
		extraExpected.setClickTimes(5);
		
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.insert("insertRecipeBasicInfo", extraExpected);
		session.commit();
		
		List<RecipeBasicInfo> tmp = session.selectList("selectRecipeBasicInfosByRecommend", 2);
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}
		
		assertTrue(actual.size() == 2);
		assertTrue(actual.contains(expected.toString()));
		assertTrue(actual.contains(extraExpected.toString()));
	}
	
	@Test
	public void testSelectRecipeBasicInfosByDislike() {
		session.insert("insertRecipeBasicInfo", dislikeExpected);
		session.commit();

		session.insert("insertRecipeBasicInfo", subscribeExpected);
		session.commit();

		List<RecipeBasicInfo> actual = session.selectList("selectRecipeBasicInfosByDislike");

		assertEquals(dislikeExpected.toString(), actual.get(0).toString());
	}

	@Test
	public void testSelectRecipeBasicInfosBySubscribe() {
		session.insert("insertRecipeBasicInfo", dislikeExpected);
		session.commit();

		session.insert("insertRecipeBasicInfo", subscribeExpected);
		session.commit();

		List<RecipeBasicInfo> actual = session.selectList("selectRecipeBasicInfosBySubscribe");

		assertEquals(subscribeExpected.toString(), actual.get(0).toString());
	}

	
	@Test
	public void testGetRecipeBasicInfosMaxId() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.insert("insertRecipeBasicInfo", extraExpected);
		session.commit();

		String actual = session.selectOne("getRecipeBasicInfosMaxId");

		assertEquals(extraExpected.getRecipeId(), actual);
	}
	
	
	@Test
	public void testCountRecipeBasicInfos() {
		session.insert("insertRecipeBasicInfo", expected);
		session.commit();

		session.insert("insertRecipeBasicInfo", extraExpected);
		session.commit();

		int actual = session.selectOne("countRecipeBasicInfos");
		assertEquals(EXPECTED_RECIPE_NUM, actual);
	}



}
