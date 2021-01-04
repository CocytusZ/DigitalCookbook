package com.saint.digitalCookbook.entity;

/**
 * The top class for recipe
 * contains detailed information of a recipe
 * It is an attribute of Recipe CLASS
 * It is corresponding to the RecipeDetail table in MySQL
 * @author Zhouyao Yu
 */
public class RecipeDetail {
	private String recipeId;
	private String timeForPre;
	private String timeForCook;
	private String cookSteps;	
	
	/**
	 * 1. Constructor for MyBatis
	 * @param recipeId
	 * @param timeForPre
	 * @param timeForCook
	 * @param cookSteps
	 */
	public RecipeDetail(String recipeId, String timeForPre, String timeForCook, String cookSteps) {
		this.recipeId = recipeId;
		this.timeForPre = timeForPre;
		this.timeForCook = timeForCook;
		this.cookSteps = cookSteps;
	}
	
	/**
	 * Have to use it for creating a new recipe because of fucking silly Mybatis
	 * @param recipeId
	 */
	public static RecipeDetail create() {
		return new RecipeDetail("", "", "", "");
	}

	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getTimeForPre() {
		return timeForPre;
	}

	public void setTimeForPre(String timeForPre) {
		this.timeForPre = timeForPre;
	}

	public String getTimeForCook() {
		return timeForCook;
	}

	public void setTimeForCook(String timeForCook) {
		this.timeForCook = timeForCook;
	}

	public String getCookSteps() {
		return cookSteps;
	}

	public void setCookSteps(String cookSteps) {
		this.cookSteps = cookSteps;
	}

	@Override
	public String toString() {
		return "RecipeDetail [recipeId=" + recipeId + ", timeForPre=" + timeForPre + ", timeForCook=" + timeForCook
				+ ", cookSteps=" + cookSteps + "]";
	}

}
