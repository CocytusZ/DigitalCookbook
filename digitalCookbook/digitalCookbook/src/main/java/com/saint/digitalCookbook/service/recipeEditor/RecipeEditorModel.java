package com.saint.digitalCookbook.service.recipeEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.manager.RecipeManager;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * model class for the app, contains recipe processing methods
 * @author LinHAN, Zhouyao Yu
 * @Refactor Zhouyao Yu
 */
public class RecipeEditorModel {
	private static RecipeManager manager;
	private Recipe recipe;
	private List<String> tagList = new ArrayList<String>();
	private static final String DEFAULT_IMAGE_PATH = "src/main/resources/image/system/DefaultImage.png";
	private static final String LOCAL_IMAGE_FOLDER_PATH = "src/main/resources/image/user/";

	/**
	 * 1. Constructor, used to create the model
	 * if the recipe exist in database, then get the complete info of it
	 * if the recipe dose not exist, create a new recipe for it
	 * @param recipeBasicInfo
	 */
	public RecipeEditorModel(RecipeBasicInfo recipeBasicInfo) {
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
	 * save the selected recipe
	 * @param recipe the current recipe
	 * @return true if save successfully
	 */
	public boolean saveEditRecipe(String name, String amount, String tag,String prepTime, String cookingTime, String cookSteps) {
		float serveAmount = Float.valueOf(amount);

		this.recipe.getRecipeBasicInfo().setName(name);
		this.recipe.getRecipeBasicInfo().setTag(tag);
		this.recipe.getRecipeBasicInfo().setpicture(getPicture());
		this.recipe.getRecipeDetail().setTimeForPre(prepTime);
		this.recipe.getRecipeDetail().setTimeForCook(cookingTime);
		this.recipe.getRecipeDetail().setCookSteps(cookSteps);

		List<Ingredient> ingredients = recipe.getIngredients();
		for(Ingredient ingredient : ingredients) {
			float unitAmount = ingredient.getAmount() / serveAmount;
			ingredient.setAmount(unitAmount);
		}
		recipe.setIngredients(ingredients);
		return manager.saveRecipe(recipe);
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
	 * Call when set the picture of recipe
	 * @param stage
	 */
	public void setPicture(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose the picture");
		String source = "";
		String dest = "";
		try {
			File file = fileChooser.showOpenDialog(stage);
			source = file.getPath();
			dest = LOCAL_IMAGE_FOLDER_PATH + getName() + "_" + file.getName();
			
			if(!getPicture().equals(DEFAULT_IMAGE_PATH)) {
				File expiredImage = new File(getPicture());
				expiredImage.delete();
			}

			copyFile(source, dest);
		} catch (Exception e) {
			System.out.println("Set Picture Failed");
		}

		recipe.getRecipeBasicInfo().setpicture(dest);

	}

	/**
	 * Used to copy picture to local folder
	 * @param source
	 * @param dest
	 */
	private void copyFile(String source, String dest) {
		try {
			InputStream in = new FileInputStream(source);
			byte[] b = new byte[in.available()];
			in.read(b);

			OutputStream out = new FileOutputStream(dest);
			out.write(b);

			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Copy File Failed");
		}
	}

	/**
	 * Called by view, get all ingredients
	 * @return
	 */
	public List<Ingredient> getIngredients(){
		for(int i = 0; i < recipe.getIngredients().size(); i++) {
			recipe.getIngredients().get(i).setIngredientId(String.valueOf(i));
		}
		return recipe.getIngredients();
	}

	/**
	 * called when add an ingredient
	 * @param name
	 * @param amount
	 * @param unit
	 */
	public void addIngredient(String name, float amount, String unit) {
		Ingredient ingredient = Ingredient.create();
		ingredient.setName(name);
		ingredient.setAmount(amount);
		ingredient.setUnit(unit);
		this.recipe.getIngredients().add(ingredient);

		for(int i = 0; i< recipe.getIngredients().size(); i++) {
			System.out.println(recipe.getIngredients().get(i));
		}
	}

	/**
	 * Called by view when confirm the change of ingredient
	 * @param index
	 * @param name
	 * @param amount
	 * @param unit
	 */
	public void changeIngredient(int index, String name, float amount, String unit) {
		recipe.getIngredients().get(index).setName(name);
		recipe.getIngredients().get(index).setAmount(amount);
		recipe.getIngredients().get(index).setUnit(unit);
	}

	/**
	 * Called by view, delete ingredient by index
	 * @param index
	 */
	public void deleteIngredient(int index) {
		this.recipe.getIngredients().remove(index);
	}

	/**
	 * Called by controller, delete all ingredients
	 */
	public void clearIngredients() {
		recipe.getIngredients().clear();
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
	 * Get tags as a String list
	 * @return
	 */
	public List<String> getTags() {
		tagList = recipe.getTags();
		return tagList;
	}

}
