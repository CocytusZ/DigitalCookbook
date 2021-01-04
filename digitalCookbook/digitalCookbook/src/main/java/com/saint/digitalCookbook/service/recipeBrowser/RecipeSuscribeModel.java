package com.saint.digitalCookbook.service.recipeBrowser;

import java.util.List;

import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.manager.RecipeManager;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;

/**
 * model class for the app, contains recipe subscribe methods
 * @author Han Jin
 * @Refactor Han Jin
 */
public class RecipeSuscribeModel {
	
	private Recipe completeRecipe;
	private List<RecipeBasicInfo> recipeCellList;
	private static RecipeManager manager;
	
	/**
	 * constructor, to construct a object with recipe basic info
	 */
	public RecipeSuscribeModel() {
		try {
			manager = new RecipeManagerImpliment();
		} catch (Exception e) {
			System.out.println("Create RecipeManager failed in RecipeSuscribeModel");
		}
	}
	
	/**
	 * 
	 * @param recipeID: find the recipe to be handled in the database
	 * @param isSuscribe: the current status of the recipe
	 */
	public void subscribe(String recipeID, boolean isSuscribe) {
		completeRecipe = manager.searchCompleteRecipeById(recipeID);
		if (completeRecipe.getRecipeBasicInfo().isSubscribe()==true) 
			completeRecipe.unSubscribe();
		else
			completeRecipe.subscribe();
		manager.saveRecipe(completeRecipe);
		System.out.println(manager.searchSubscribeRecipe());
	} 
	
	/**
	 * 
	 * @param recipeID: find the recipe to be handled in the database
	 * @param isSuscribe: the current status of the recipe
	 */
	public void dislike(String recipeID) {
		completeRecipe = manager.searchCompleteRecipeById(recipeID);
		if (completeRecipe.getRecipeBasicInfo().isDislike()==true) 
			completeRecipe.unsetDislike();
		else
			completeRecipe.setDislike();
		manager.saveRecipe(completeRecipe);
	}

	/**
	 * 
	 * @param recipeID: find the recipe to be handled in the database, add its click times
	 */
	public void addClickTimes(String recipeID) {
		completeRecipe = manager.searchCompleteRecipeById(recipeID);
		completeRecipe.getRecipeBasicInfo().setClickTimes(completeRecipe.getRecipeBasicInfo().getClickTimes()+1);
		manager.saveRecipe(completeRecipe);
		
	}
	
	/**
	 * 
	 * @param recipeID: the recipe to be handled
	 * @return the current status of the recipe, after handled
	 */
	public boolean updateSubscribeStatus(String recipeID) {
		if (manager.searchCompleteRecipeById(recipeID).getRecipeBasicInfo().isSubscribe())
			return false;
		else
			return true;
	} 

	/**
	 * 
	 * @param recipeID: the recipe to be handled
	 * @return the current status of the recipe, after handled
	 */
	public boolean updateDislikeStatus(String recipeID) {
		if (manager.searchCompleteRecipeById(recipeID).getRecipeBasicInfo().isDislike())
			return false;
		else
			return true;
	}

	/**
	 * search disliked recipe in database
	 * @return a list if recipe basic info 
	 */
	public List<RecipeBasicInfo> searchDislikeRecipe() {
		try {
			recipeCellList = manager.searchDislikeRecipe();
		}	catch (Exception e) {
			e.printStackTrace();
		}	
			return recipeCellList;
	}

	/**
	 * 
	 * @param tag: search recipe in database by name
	 * @return a list if recipe basic info 
	 */
	public List<RecipeBasicInfo> searchRecipeByTag(String tag) {
		try {
			recipeCellList = manager.searchRecipeByTag(tag);
		}	catch (Exception e) {
			e.printStackTrace();
		}	
			return recipeCellList;
	}
}
