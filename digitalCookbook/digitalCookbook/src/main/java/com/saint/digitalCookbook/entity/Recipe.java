package com.saint.digitalCookbook.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Top class of a recipe, contains RecipeBasicInfo, RecipeDetail and Ingredient
 * Has nothing to do with Database
 * @author Zhouyao Yu
 */
public class Recipe {
	RecipeBasicInfo recipeBasicInfo;
	RecipeDetail recipeDetail;
	List<Ingredient> ingredients;
	
	/**
	 * 1. Constructor
	 * @param recipeBasicInfo
	 * @param recipeDetail
	 * @param ingredients
	 */
	public Recipe(RecipeBasicInfo recipeBasicInfo, RecipeDetail recipeDetail, List<Ingredient> ingredients) {
		this.recipeBasicInfo = recipeBasicInfo;
		this.recipeDetail = recipeDetail;
		this.ingredients = ingredients;
	}	
	
	/**
	 * Used tp create an empty recipe
	 * @return
	 */
	public static Recipe create() {
		return new Recipe();
	}
	
	/**
	 * A private constructor to create an empty Recipe
	 */
	private Recipe() {
		this.recipeBasicInfo = RecipeBasicInfo.create();
		this.recipeDetail = RecipeDetail.create();
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	/**
	 * Used when create a new recipe
	 * Set a unified id for BasicInfo, RecipeDetail and Ingredients
	 * @param recipeId
	 */
	public void setRecipeId(String recipeId) {
		this.recipeBasicInfo.setRecipeId(recipeId);
		this.recipeDetail.setRecipeId(recipeId);
		for(Ingredient in : ingredients) {
			in.setRecipeId(recipeId);
		}
	}
	
	/**
	 * Method to set dislike of this recipe
	 */
	public void setDislike() {
		this.recipeBasicInfo.setDislike(true);
	}
	
	/**
	 * Method to cancel dislike of this recipe
	 */
	public void unsetDislike() {
		this.recipeBasicInfo.setDislike(false);
	}
	
	/**
	 * Method to subscribe this recipe
	 */
	public void subscribe() {
		this.recipeBasicInfo.setSubscribe(true);
	}
	
	/**
	 * Method to unsubscribe this recipe
	 */
	public void unSubscribe() {
		this.recipeBasicInfo.setSubscribe(false);
	}
	
	/**
	 * Method to accumulate clickTimes to this recipe, 
	 * will be invoke if user click the recipe
	 */
	public void addClickTimes() {
		this.recipeBasicInfo.setClickTimes(recipeBasicInfo.getClickTimes() + 1);
	}

	/**
	 * Method to clear click times,
	 * will be invoke if the recipe is disliked
	 */
	public void clearClickTimes() {
		this.recipeBasicInfo.setClickTimes(0);
	}

	/**
	 * Get tags as a String list
	 * @return tags as a list
	 */
	public List<String> getTags() {
		List<String> tags = new ArrayList<String>();
		String tagStr = recipeBasicInfo.getTag();
		while(tagStr.contains(",")) {
			int commaPos = tagStr.indexOf(",");
			tags.add(tagStr.substring(0, commaPos));
			tagStr = tagStr.substring(commaPos + 1, tagStr.length());
		}
		tags.add(tagStr);
		
		return tags;
	}
	
	
	
	// ----- Getter & Setter ----- Start -----//
	public RecipeBasicInfo getRecipeBasicInfo() {
		return recipeBasicInfo;
	}
	public void setRecipeBasicInfo(RecipeBasicInfo recipeBasicInfo) {
		this.recipeBasicInfo = recipeBasicInfo;
	}
	public RecipeDetail getRecipeDetail() {
		return recipeDetail;
	}
	public void setRecipeDetail(RecipeDetail recipeDetail) {
		this.recipeDetail = recipeDetail;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
