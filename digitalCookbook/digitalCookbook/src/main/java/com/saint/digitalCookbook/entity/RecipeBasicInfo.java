package com.saint.digitalCookbook.entity;

/**
 * Class to store Basic information of recipes in convenience of search and show recipe.
 * It is a component of Recipe CLASS
 * It is corresponding to the RecipeBasicInfo table in MySQL
 * @author Zhouyao Yu
 */
public class RecipeBasicInfo {
	
    private String recipeId;
    private String name;
    private String tag;
    private String picture;
    
    // Fields used to DISLIKE, SUBSCRIBE and RECOMMEND RECIPE
	private boolean isDislike;
	private boolean isSubscribe;
	private int clickTimes;

	/**
	 * 1. Constructor for mybatis
	 * @param id
	 * @param name
	 * @param tag
	 * @param picture
	 * @param isDislike
	 * @param isSubscribe
	 * @param clickTimes
	 */
	public RecipeBasicInfo(String recipeId, String name, String tag, String picture, boolean isDislike, boolean isSubscribe, int clickTimes) {
		this.recipeId = recipeId;
		this.name = name;
		this.tag = tag;
		this.picture = picture;
		this.isDislike = isDislike;
		this.isSubscribe = isSubscribe;
		this.clickTimes = clickTimes;
	}
	
	/**
	 * Used to create a new recipe, because of the fucking silly Mybatis
	 */
	public static RecipeBasicInfo create() {
		return new  RecipeBasicInfo("", "", "", "", false, false, 0);
	}

	// ----- Getter & setter ----- Start -----//  
	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String id) {
		this.recipeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getpicture() {
		return picture;
	}

	public void setpicture(String picture) {
		this.picture = picture;
	}

	public boolean isDislike() {
		return isDislike;
	}

	public void setDislike(boolean isDislike) {
		this.isDislike = isDislike;
	}

	public boolean isSubscribe() {
		return isSubscribe;
	}

	public void setSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public int getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(int clickTimes) {
		this.clickTimes = clickTimes;
	}

	@Override
	public String toString() {
		return "RecipeBasicInfo [recipeId=" + recipeId + ", name=" + name + ", tag=" + tag + ", picture=" + picture
				+ ", isDislike=" + isDislike + ", isSubscribe=" + isSubscribe + ", clickTimes=" + clickTimes + "]";
	}
	
	// ----- Getter & setter ----- End -----//  
	
	
	
    

}
