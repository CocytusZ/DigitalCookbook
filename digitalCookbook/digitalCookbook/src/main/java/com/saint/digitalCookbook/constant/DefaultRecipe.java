package com.saint.digitalCookbook.constant;

import java.util.LinkedList;
import java.util.List;

import com.saint.digitalCookbook.entity.Ingredient;
import com.saint.digitalCookbook.entity.Recipe;
import com.saint.digitalCookbook.entity.RecipeBasicInfo;
import com.saint.digitalCookbook.entity.RecipeDetail;
import com.saint.digitalCookbook.manager.RecipeManager;
import com.saint.digitalCookbook.manager.managerImpliment.RecipeManagerImpliment;
import com.saint.digitalCookbook.ui.tutorial.homepage.HomePageTutorialView;

public class DefaultRecipe {
	private static Recipe recipe;
	private static RecipeManager manager;
	
	/**
	 * add default recipes to database if it is null
	 */
	public static void CreateRecipe() {
		try {
			manager = new RecipeManagerImpliment();
		} catch (Exception e) {
			System.out.println("Create manager failed");
		}
		List<RecipeBasicInfo> exsitRecipes =  manager.searchRecipeByName("");
		if (exsitRecipes.isEmpty()) {
		
			HomePageTutorialView.activateTutorial();
			addRecipes();
		} 
		
	}
	
	public static void addRecipes() {
		//1
		RecipeBasicInfo recipeBasicInfo = new RecipeBasicInfo("", "Egg with Scallion", RecipeTagEnum.EGG_DISH.toString(), "src/main/resources/image/user/default/Egg_with_Scallion.jpg", false, false, 0);
		String cookSteps = "1.Beat the eggs in a bowl, add salt, add the scallion and mix well.\n2.Add oil to the pan, pour in the egg mixture and cook for 2 minutes on each side.";
		RecipeDetail recipeDetail = new RecipeDetail("", "5 minutes", "5 minutes", cookSteps);
		List<Ingredient> ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "Egg", 2, null));
		ingredients.add(new Ingredient("", "", "Scallion", 5, "gram"));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//2
		recipeBasicInfo = new RecipeBasicInfo("", "Chicken Wings", RecipeTagEnum.MEAT_DISH.toString(), "src/main/resources/image/user/default/Chicken_Wings.jpg", false, false, 0);
		cookSteps = "1.Heat oil in a hot pan, add chicken wings and fry until lightly browned on both sides.\n2.Add soy sauce, rock sugar and wine.\n3.Turn the heat to medium-low and continue for 20 minutes, until the sauce is almost dry.";
		recipeDetail = new RecipeDetail("", "5 minutes", "25 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "Chinken Wings", 6, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//3
		recipeBasicInfo = new RecipeBasicInfo("", "French Fries", RecipeTagEnum.SNACK_DISH.toString(), "src/main/resources/image/user/default/French_Fries.jpg", false, false, 0);
		cookSteps = "1.Wash and peel the potatoes; cut into medium-sized strips, boil for 2-3 minutes until the fries are half cooked.\n2.Add oil, salt and black pepper; mix well.\n3.Bake in preheated oven on medium heat at 230 degrees for about 13 minutes.";
		recipeDetail = new RecipeDetail("", "5 minutes", "15 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "Potato", 1, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//4
		recipeBasicInfo = new RecipeBasicInfo("", "Tomatoes with Eggs", RecipeTagEnum.EGG_DISH.toString(), "src/main/resources/image/user/default/Tomatoes_with_Eggs.jpg", false, false, 0);
		cookSteps = "1.Peel and slice the tomatoes, beat the eggs with some salt and pour it over the tomatoes.\n2.Place the mixed tomato egg mixture in a rice cooker and steam to cook.";
		recipeDetail = new RecipeDetail("", "5 minutes", "10 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "Tomato", 2, null));		
		ingredients.add(new Ingredient("", "", "Egg", 2, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//5
		recipeBasicInfo = new RecipeBasicInfo("", "Egg Custard", RecipeTagEnum.EGG_DISH.toString(), "src/main/resources/image/user/default/Egg_Custard.jpg", false, false, 0);
		cookSteps = "1.Beat the eggs in a bowl, add salt and mix well.\n2.Steam with cold water for 8-10 minutes after the water boiling.";
		recipeDetail = new RecipeDetail("", "3 minutes", "10 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "Eggs", 2, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//6
		recipeBasicInfo = new RecipeBasicInfo("", "Pomegranate Juice", RecipeTagEnum.JUICE_DISH.toString(), "src/main/resources/image/user/default/Pomegranate_Juice.jpg", false, false, 0);
		cookSteps = "1.Peel the pomegranate into seeds.\n2.Put the peeled pomegranate seeds in the juicer.";
		recipeDetail = new RecipeDetail("", "10 minutes", "3 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "pomegranate", 2, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//7
		recipeBasicInfo = new RecipeBasicInfo("", "Grilled Toast with Cheese", RecipeTagEnum.TOAST_DISH.toString(), "src/main/resources/image/user/default/Grilled_Toast_with_Cheese.jpg", false, false, 0);
		cookSteps = "1.Spread the cheese evenly over the toast.\n2.Place the baking sheet in the middle of the oven at 170 degrees and grill for 10 minutes.";
		recipeDetail = new RecipeDetail("", "2 minutes", "10 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "", "toast", 2, "Slice"));
		ingredients.add(new Ingredient("", "", "cheese", 2, "Slice"));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//8
		recipeBasicInfo = new RecipeBasicInfo("", "Shrimp Sandwich", RecipeTagEnum.TOAST_DISH.toString(), "src/main/resources/image/user/default/Shrimp_Sandwich.jpg", false, false, 0);
		cookSteps = "1.Lay out the ingredients in order: toast, ham, tomatoes, toast, shrimp, ham, toast.\n2.Heating for one minute in the microwave";
		recipeDetail = new RecipeDetail("", "5 minutes", "5 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "10", "shrimp", 4, null));
		ingredients.add(new Ingredient("", "11", "toast", 3, "Slice"));
		ingredients.add(new Ingredient("", "12", "ham", 2, "Slice"));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//9
		recipeBasicInfo = new RecipeBasicInfo("", "Fried Rice with Eggs", RecipeTagEnum.MAIN_DISH.toString(), "src/main/resources/image/user/default/Fried_Rice_with_Eggs.jpg", false, false, 0);
		cookSteps = "1.Add the egg and salt to the rice and mix well.\n2.Heat oil in a wok, add the rice and heat until the rice smells fragrant and the grains are separated.";
		recipeDetail = new RecipeDetail("", "10 minutes", "10 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "13", "rice", 300, "Gram"));
		ingredients.add(new Ingredient("", "14", "egg", 3, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
		
		//10
		recipeBasicInfo = new RecipeBasicInfo("", "Mixed Potato Chips", RecipeTagEnum.SNACK_DISH.toString(), "src/main/resources/image/user/default/Mixed_PotatoChips.jpg", false, false, 0);
		cookSteps = "1.Peel the potatoes, cut them into slices and soak them in water for 15 minutes.\n2.Remove the potato slices, place them in the hot water's cooking pot and heat.\n3.Prepare the sauce: add the minced garlic, soy sauce, vinegar, sugar, salt and chili oil and mix well.\n4.Put the blanched potato slices in a dishpour in the bowl juices, pour the pepper oil and mix well.";
		recipeDetail = new RecipeDetail("", "15 minutes", "10 minutes", cookSteps);
		ingredients = new LinkedList<Ingredient>();
		ingredients.add(new Ingredient("", "15", "potatoes", 2, null));
		recipe = new Recipe(recipeBasicInfo, recipeDetail, ingredients);
		manager.saveRecipe(recipe);
	}

}
