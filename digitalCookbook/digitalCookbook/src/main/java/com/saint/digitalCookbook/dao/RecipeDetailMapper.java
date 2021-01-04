package com.saint.digitalCookbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saint.digitalCookbook.entity.RecipeDetail;

/**
 * Mybatis interface for RecipeDetail table
 * @author Zhouyao Yu
 */
public interface RecipeDetailMapper {
	List<RecipeDetail> selectRecipeDetail(@Param("recipeId")String recipeId);
	void insertRecipeDetail(RecipeDetail recipeDetail);
	void updateRecipeDetail(RecipeDetail recipeDetail);
	void deleteRecipeDetail(RecipeDetail recipeDetail);
	// Use for test
	void deleteAllRecipeDetails();
}
