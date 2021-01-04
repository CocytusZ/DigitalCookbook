package com.saint.digitalCookbook.service.recipeBrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.manager.RecipeManager;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;

/**
 * model class for the app, contains recipe processing methods
 * @author LinHAN, Zhouyao Yu
 * @Refactor Zhouyao Yu
 */
public class RecipeBrowserModel {
	public  static final int NAME_SLOT = 0;
	public static final int AMOUNT_SLOT = 1;
	private static final String DEFAULT_IMAGE_PATH = "src/main/resources/image/system/DefaultImage.png";
	
	private static RecipeManager manager;
	private Recipe recipe;
	
	/**
	 * constructor, to construct a object with recipe basic info
	 * @param recipeBasicInfo basic info of the recipe
	 */
	public RecipeBrowserModel(RecipeBasicInfo recipeBasicInfo) {
		try {
			manager = new RecipeManagerImpliment();
		
			if (recipeBasicInfo.getRecipeId().equals("")) {
				recipe = Recipe.create();	
			} else {
				recipe = manager.searchCompleteRecipeById(recipeBasicInfo.getRecipeId());
			}
		} catch (Exception e) {
			System.out.println("Create recipe editor model failed");
		}
	}
	
	
	/**
	 * return the url of picture
	 * @return the file path if image exist, or the default image path
	 */
	public String getPicture() {
		File file = new File(recipe.getRecipeBasicInfo().getpicture());
		if(file.exists()) {
			return file.getPath();
		} else {
			return DEFAULT_IMAGE_PATH;
		}
	}
	
	/**
	 * Called by view, get all ingredients
	 * @return
	 */
	public List<Ingredient> getIngredients(){
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		int id = 0;
		for (Ingredient in : recipe.getIngredients()) {
			Ingredient tmp = in.clone();
			tmp.setIngredientId(String.valueOf(++id));
			ingredientList.add(tmp);
		}
		return ingredientList;
	}
	
	/**
	 * show the name of the recipe
	 * @return the recipe name
	 */
	public String getName() {
		return this.recipe.getRecipeBasicInfo().getName();
	}
	
	/**
	 * show the preparation time of the recipe
	 * @return the preparation time
	 */
	public String getPrepTime() {
		return this.recipe.getRecipeDetail().getTimeForPre();
	}
	
	/**
	 * show the cooking time of the recipe
	 * @return the cooking time
	 */
	public String getCookTime() {
		return this.recipe.getRecipeDetail().getTimeForCook();
	}

	/**
	 * show the preparation steps
	 * @return the preparation steps
	 */
	public String getCookStep() {
		String step = recipe.getRecipeDetail().getCookSteps();
		return step;
	}

	/**
	 * @return the recipe
	 */
	public Recipe getRecipe() {
		return recipe;
	}
	
	/**
	 * Get tags as a String list
	 * @return
	 */
	public List<String> getTags() {
		return recipe.getTags();
	}

	/**
	 * Delete the current recipe 
	 */
	public void deleteRecipe() {
		manager.deleteRecipe(recipe);
	}
}
