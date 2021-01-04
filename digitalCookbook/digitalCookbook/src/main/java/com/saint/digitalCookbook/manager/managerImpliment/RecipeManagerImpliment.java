package com.saint.digitalCookbook.manager.managerImpliment;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.saint.digitalCookbook.dao.IngredientMapper;
import com.saint.digitalCookbook.dao.RecipeBasicInfoMapper;
import com.saint.digitalCookbook.dao.RecipeDetailMapper;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.entity.RecipeDetail;
import com.saint.digitalCookbook.manager.RecipeManager;

/**
 * Implement of RecipeManager using Mybatis and MySQL
 * @author Zhouyao Yu
 */
public class RecipeManagerImpliment implements RecipeManager {
	private static SqlSession session;
	private static IngredientMapper ingredientMapper;
	private static RecipeBasicInfoMapper basicInfoMapper;
	private static RecipeDetailMapper detailMapper;
	
	private static final int FIRST_ITEM_INDEX = 0;

	public RecipeManagerImpliment() throws Exception {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();

		ingredientMapper = session.getMapper(IngredientMapper.class);
		basicInfoMapper = session.getMapper(RecipeBasicInfoMapper.class);
		detailMapper = session.getMapper(RecipeDetailMapper.class);
	}

	public List<RecipeBasicInfo> searchRecipeByName(String name) {
		return basicInfoMapper.selectRecipeBasicInfosByName(name);
	}

	public List<RecipeBasicInfo> searchRecipeByTag(String tag) {
		return basicInfoMapper.selectRecipeBasicInfosByTag(tag);
	}

	public List<RecipeBasicInfo> searchRecommendedRecipe(int amount) {
		return basicInfoMapper.selectRecipeBasicInfosByRecommend(amount);
	}

	public List<RecipeBasicInfo> searchDislikeRecipe() {
		return basicInfoMapper.selectRecipeBasicInfosByDislike();
	}

	public List<RecipeBasicInfo> searchSubscribeRecipe() {
		return basicInfoMapper.selectRecipeBasicInfosBySubscribe();
	}

	public Recipe searchCompleteRecipeById(String recipeId) {
		Recipe recipe = Recipe.create();
		RecipeBasicInfo recipeBasicInfo = basicInfoMapper.selectRecipeBasicInfoById(recipeId).get(FIRST_ITEM_INDEX);
		RecipeDetail recipeDetail = detailMapper.selectRecipeDetail(recipeId).get(FIRST_ITEM_INDEX);
		List<Ingredient> ingredients = ingredientMapper.selectIngredientsByRecipe(recipeId);

		recipe.setRecipeBasicInfo(recipeBasicInfo);
		recipe.setRecipeDetail(recipeDetail);
		recipe.setIngredients(ingredients);
		return recipe;
	}

	public boolean saveRecipe(Recipe recipe) {
		String recipeId = recipe.getRecipeBasicInfo().getRecipeId();
		try {
			if (recipeId.equals("")) {
				addRecipe(recipe);
			} else {
				changeRecipe(recipe);
			}
			return true;
		} catch (Exception e) {
			System.out.println("The saving recipe failed");
			return false;
		}
	}

	/**
	 * Insert a new recipe into the DB with a new recipeId
	 * @param recipe
	 * @return true if operation succeed, false if operation failed
	 */
	private boolean addRecipe(Recipe recipe) {
		RecipeBasicInfo recipeBasicInfo = recipe.getRecipeBasicInfo();
		RecipeDetail recipeDetail = recipe.getRecipeDetail();

		String recipeId = recipe.getRecipeBasicInfo().getRecipeId();
		String existedMaxId = basicInfoMapper.getRecipeBasicInfosMaxId();
		if(existedMaxId == null) {
			recipeId = String.valueOf(FIRST_ITEM_INDEX);
		} else {
			recipeId = String.valueOf(Integer.parseInt(existedMaxId) + 1);
		}
		recipe.setRecipeId(recipeId);
		
		try {
			basicInfoMapper.insertRecipeBasicInfo(recipeBasicInfo);
			session.commit();
			detailMapper.insertRecipeDetail(recipeDetail);
			session.commit();
			storeIngredient(recipe);
			return true;
		} catch (Exception e) {
			System.out.println("Insert Recipe failed");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update a existed recipe in database
	 * @param recipe
	 * @return true if operation succeed, false if operation failed
	 */
	private boolean changeRecipe(Recipe recipe) {
		RecipeBasicInfo recipeBasicInfo = recipe.getRecipeBasicInfo();
		RecipeDetail recipeDetail = recipe.getRecipeDetail();

		try {
			basicInfoMapper.updateRecipeBasicInfo(recipeBasicInfo);
			session.commit();
			detailMapper.updateRecipeDetail(recipeDetail);
			session.commit();
			storeIngredient(recipe);
			return true;
		} catch (Exception e) {
			System.out.println("Update Recipe failed");
			return false;
		}
	}
	
	/**
	 * When store the ingredients of a recipe
	 * @param recipe
	 * @throws Exception of database error
	 */
	private void storeIngredient(Recipe recipe) throws Exception {
		String recipeId = recipe.getRecipeBasicInfo().getRecipeId();
		List<Ingredient> existedIngredients = ingredientMapper.selectIngredientsByRecipe(recipeId);
		
		if(!existedIngredients.isEmpty()) {
			ingredientMapper.deleteIngredientsByRecipe(recipeId);
			session.commit();
		}

		int ingredientId = FIRST_ITEM_INDEX;
		for(Ingredient in : recipe.getIngredients()) {
			in.setIngredientId(String.valueOf(ingredientId++));
			ingredientMapper.insertIngredient(in);
			session.commit();
		}
	}

	public boolean deleteRecipe(Recipe recipe) {
		String recipeId = recipe.getRecipeBasicInfo().getRecipeId();
		try {
			basicInfoMapper.deleteRecipeBasicInfo(recipe.getRecipeBasicInfo());
			session.commit();
			detailMapper.deleteRecipeDetail(recipe.getRecipeDetail());
			session.commit();
			ingredientMapper.deleteIngredientsByRecipe(recipeId);
			session.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void deleteAll() {
		basicInfoMapper.deleteAllRecipeBasicInfo();
		session.commit();
		detailMapper.deleteAllRecipeDetails();
		session.commit();
		ingredientMapper.deleteAllIngredient();
		session.commit();
	}

}
