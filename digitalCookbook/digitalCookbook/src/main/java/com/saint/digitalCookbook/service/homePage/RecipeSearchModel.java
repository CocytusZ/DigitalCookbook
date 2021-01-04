package com.saint.digitalCookbook.service.homePage;

import java.util.List;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.manager.RecipeManager;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;

/**
 * model class for the app, contains recipe search methods
 * @author Han Jin
 * @Refactor Han Jin
 */
public class RecipeSearchModel {
	
	private List<RecipeBasicInfo> recipeCellList;
	private static RecipeManager manager;
	
	/**
	 * constructor, to construct a object with recipe basic info
	 */
	public RecipeSearchModel() {
		try {
			manager = new RecipeManagerImpliment();
		} catch (Exception e) {
			System.out.println("Create RecipeManager failed in RecipeSearchModel");
		}
	}
	
	/**
	 * 
	 * @param name: search recipe in database by name
	 * @return: a list if recipe basic info
	 */
	public List<RecipeBasicInfo> searchRecipeByName(String name) {
		
		try {
			recipeCellList = manager.searchRecipeByName(name);
		}	catch (Exception e) {
			e.printStackTrace();
		}	
			return recipeCellList;
		}
	
	/**
	 * 
	 * @param name: search recipe in database and order them by click times
	 * @return: a list if recipe basic info
	 */
	public List<RecipeBasicInfo> searchRecommendRecipe(int i) {
		try {
			recipeCellList = manager.searchRecommendedRecipe(i);
		}	catch (Exception e) {
			e.printStackTrace();
		}			
		return recipeCellList;
	}
	
	/**
	 * 
	 * @param name: search the subscribed recipes in database
	 * @return: a list if recipe basic info
	 */
	public List<RecipeBasicInfo> searchSubscribeRecipe() {
		try {
			recipeCellList = manager.searchSubscribeRecipe();
		}	catch (Exception e) {
			e.printStackTrace();
		}	
			return recipeCellList;
	}
	
	/**
	 * 
	 * @param name: search the disliked recipes in database
	 * @return: a list if recipe basic info
	 */
	public List<RecipeBasicInfo> searchDislikeRecipe() {
		try {
			recipeCellList = manager.searchDislikeRecipe();
		}	catch (Exception e) {
			e.printStackTrace();
		}	
			return recipeCellList;
	}
}
