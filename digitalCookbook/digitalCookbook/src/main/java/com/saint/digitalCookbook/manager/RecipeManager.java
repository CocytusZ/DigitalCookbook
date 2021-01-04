package com.saint.digitalCookbook.manager;

import java.util.List;


import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;

/**
 * InterLayer of DAO layer, used to encapsulate the method of DAO.
 * @author ZHouyao Yu
 */
public interface RecipeManager {
	/**
	 * Used to conduct a vague search recipes by name in search bar
	 * @param name
	 * @return RecipeBasicInfo as a list
	 */
	List<RecipeBasicInfo> searchRecipeByName(String name);
	
	/**
	 * Use to list recipes with a specific tag 
	 * @param tag
	 * @return RecipeBasicInfo as a list
	 */
	List<RecipeBasicInfo> searchRecipeByTag(String tag);
	
	/**
	 * Search several recipes with high click times
	 * @param amout is the amount of recipes
	 * @return RecipeBasicInfo as a list
	 */
	List<RecipeBasicInfo> searchRecommendedRecipe(int amount);
	
	/**
	 * Search recipes with DISLIKE mark
	 * @return RecipeBasicInfo as a list
	 */
	List<RecipeBasicInfo> searchDislikeRecipe();
	
	/**
	 * Search recipes with SUBSCRIBE mark
	 * @return RecipeBasicInfo as a list
	 */
	List<RecipeBasicInfo> searchSubscribeRecipe();
	
	/**
	 * Search a complete recipe with id
	 * @param recipeId
	 * @return Recipe
	 */
	Recipe searchCompleteRecipeById(String recipeId);
	
	/**
	 * Save a recipe
	 * If it is a new recipe without recipeId, insert a new recipe to DB
	 * If it is a existed recipe, update the recipe in the DB
	 * @param recipe
	 * @return true if succeed, false if failed
	 */
	boolean saveRecipe(Recipe recipe);
	
	/**
	 * delete a recipe By its id
	 * @param recipe
	 * @return true if succeed, false if failed
	 */
	boolean deleteRecipe(Recipe recipe);
	
	/**
	 * For Test only !!!
	 */
	void deleteAll();
}
