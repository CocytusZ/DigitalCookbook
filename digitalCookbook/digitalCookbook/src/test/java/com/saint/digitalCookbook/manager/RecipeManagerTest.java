package com.saint.digitalCookbook.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import com.saint.digitalCookbook.constant.RecipeTagEnum;
import com.saint.digitalCookbook.constant.UnitEnum;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.entity.RecipeDetail;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;

import junit.framework.TestCase;

/**
 * Test code for RecipeManager Interface
 * @author Zhouyao Yu
 * @date 2020年10月23日
 */
public class RecipeManagerTest extends TestCase {
	private RecipeManager recipeManager;
	private Recipe expected;
	private Recipe extraExpected;
	private static final int FIRST_ITEM_INDEX = 0;
	private static final int RECOMEND_RECIPE_NUM = 2;
	private static final String SEARCH_KEYWORD = "Boiled";
	
	@Override
	protected void setUp() throws Exception {
		recipeManager = new RecipeManagerImpliment();

		RecipeBasicInfo expectBasicInfo = RecipeBasicInfo.create();
		expectBasicInfo.setName("Boiled meat");
		expectBasicInfo.setTag(RecipeTagEnum.MEAT_DISH.toString());
		expectBasicInfo.setpicture("http://localhost:8080/Boiled_Meat");
		
		RecipeDetail expectDetail =  RecipeDetail.create();
		expectDetail.setTimeForPre("5 minutes");
		expectDetail.setTimeForCook("10 minutes");
		expectDetail.setCookSteps("Chop, boil and wash");
		
		List<Ingredient> expectIngredients = new ArrayList<Ingredient>();{
			Ingredient ingredient = Ingredient.create();
			ingredient.setName("pork");
			ingredient.setAmount(1);
			ingredient.setUnit(UnitEnum.KILOGRAM);
			expectIngredients.add(ingredient);
			
			ingredient = Ingredient.create();
			ingredient.setName("ginger");
			ingredient.setAmount(3);
			ingredient.setUnit(UnitEnum.SLICE);
			expectIngredients.add(ingredient);
		}
		expected = Recipe.create();
		expected.setRecipeBasicInfo(expectBasicInfo);
		expected.setRecipeDetail(expectDetail);
		expected.setIngredients(expectIngredients);
		expected.addClickTimes();
		expected.subscribe();
		
		RecipeBasicInfo extraBasicInfo = RecipeBasicInfo.create();
		extraBasicInfo.setName("Salad");
		extraBasicInfo.setTag(RecipeTagEnum.VEGETAVLE_DISH.toString());
		extraBasicInfo.setpicture("http://localhost:8080/Salad");
		RecipeDetail extraDetail =  RecipeDetail.create();
		expectDetail.setTimeForPre("1 minute");
		expectDetail.setTimeForCook("3 minutes");
		expectDetail.setCookSteps("Chop and Smash");
		List<Ingredient> extraIngredients = new ArrayList<Ingredient>();{
			Ingredient ingredient = Ingredient.create();
			ingredient.setName("potato");
			ingredient.setAmount(1);
			ingredient.setUnit(UnitEnum.NONE);
			extraIngredients.add(ingredient);
			
			ingredient = Ingredient.create();
			ingredient.setName("carrot");
			ingredient.setAmount(2);
			ingredient.setUnit(UnitEnum.NONE);
			extraIngredients.add(ingredient);
		}		
		extraExpected = Recipe.create();
		extraExpected.setRecipeBasicInfo(extraBasicInfo);
		extraExpected.setRecipeDetail(extraDetail);
		extraExpected.setIngredients(extraIngredients);
		extraExpected.setDislike();
		
		clearAllTables();
	}

	@AfterEach
	public void clearAllTables() {
		recipeManager.deleteAll();
	}
	
	@Test
	public void testSearchRecipeByName() {
		recipeManager.saveRecipe(expected);
		recipeManager.saveRecipe(extraExpected);
		RecipeBasicInfo actual = recipeManager.searchRecipeByName(SEARCH_KEYWORD).get(FIRST_ITEM_INDEX);
		assertEquals(actual.toString(), actual.toString());
	}
	
	@Test 
	public void testSearchRecipeByTag() {
		recipeManager.saveRecipe(expected);
		RecipeBasicInfo actual = recipeManager.searchRecipeByTag(expected.getRecipeBasicInfo().getTag()).get(FIRST_ITEM_INDEX);
		assertEquals(actual.toString(), actual.toString());
	}
	
	@Test
	public void testSearchRecommendedRecipe() {
		recipeManager.saveRecipe(expected);
		recipeManager.saveRecipe(extraExpected);
		
		List<RecipeBasicInfo> tmp = recipeManager.searchRecommendedRecipe(RECOMEND_RECIPE_NUM);
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}

		assertTrue(actual.contains(expected.getRecipeBasicInfo().toString()));
		assertTrue(actual.contains(extraExpected.getRecipeBasicInfo().toString()));
	}
	
	@Test 
	public void testSearchDislikeRecipe() {
		recipeManager.saveRecipe(expected);
		recipeManager.saveRecipe(extraExpected);
		
		List<RecipeBasicInfo> tmp = recipeManager.searchDislikeRecipe();
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}

		assertFalse(actual.contains(expected.getRecipeBasicInfo().toString()));
		assertTrue(actual.contains(extraExpected.getRecipeBasicInfo().toString()));
	}
	
	@Test
	public void testSearchSubscribeRecipe() {
		recipeManager.saveRecipe(expected);
		recipeManager.saveRecipe(extraExpected);
		
		List<RecipeBasicInfo> tmp = recipeManager.searchSubscribeRecipe();
		List<String> actual = new ArrayList<String>();
		for(RecipeBasicInfo info : tmp) {
			actual.add(info.toString());
		}

		assertTrue(actual.contains(expected.getRecipeBasicInfo().toString()));
		assertFalse(actual.contains(extraExpected.getRecipeBasicInfo().toString()));
	}
	
	@Test
	public void testSearchCompleteRecipeById() {
		recipeManager.saveRecipe(expected);
		Recipe actual = recipeManager.searchCompleteRecipeById(expected.getRecipeBasicInfo().getRecipeId());
		assertEquals(expected.getRecipeBasicInfo().toString(), actual.getRecipeBasicInfo().toString());
		assertEquals(expected.getRecipeDetail().toString(), actual.getRecipeDetail().toString());
		assertEquals(expected.getIngredients().toString(), actual.getIngredients().toString());
	}
	
	@Test
	public void testSaveRecipe() {
		recipeManager.saveRecipe(expected);
		Recipe actual = recipeManager.searchCompleteRecipeById(expected.getRecipeBasicInfo().getRecipeId());
		assertEquals(expected.getRecipeBasicInfo().toString(), actual.getRecipeBasicInfo().toString());
		assertEquals(expected.getRecipeDetail().toString(), actual.getRecipeDetail().toString());
		assertEquals(expected.getIngredients().toString(), actual.getIngredients().toString());
	}
}
