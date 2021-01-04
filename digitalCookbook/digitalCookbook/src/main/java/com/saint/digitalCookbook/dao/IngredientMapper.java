package com.saint.digitalCookbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.saint.digitalCookbook.entity.Ingredient;

/**
 * Mybatis interface for Ingredient table
 * @author Zhouyao Yu
 */
public interface IngredientMapper {
	Ingredient selectIngredient(@Param("ingredientId")String ingredientId);
	void insertIngredient(Ingredient ingredient);
	void updateIngredient(Ingredient ingredient);  
	void deleteIngredient(Ingredient ingredient);
	
	List<Ingredient> selectIngredientsByRecipe(@Param("recipeId")String recipeId);
	int countIngredientsByRecipe(@Param("recipeId")String recipeId);
	void deleteIngredientsByRecipe(@Param("recipeId")String recipeId);
	
	// use for test
	void deleteAllIngredient();
}
