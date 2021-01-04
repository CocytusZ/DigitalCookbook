package com.saint.digitalCookbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saint.digitalCookbook.entity.RecipeBasicInfo;

/**
 * Mybatis interface for RecipeBasicInfo table
 * @author Zhouyao Yu
 */
public interface RecipeBasicInfoMapper {
	List<RecipeBasicInfo> selectRecipeBasicInfoById(@Param("recipeId")String recipeId);
	void insertRecipeBasicInfo(RecipeBasicInfo recipeBasicInfo);
	void updateRecipeBasicInfo(RecipeBasicInfo recipeBasicInfo);
	void deleteRecipeBasicInfo(RecipeBasicInfo recipeBasicInfo);

	List<RecipeBasicInfo> selectRecipeBasicInfosByName(@Param("name")String vagueName);
	List<RecipeBasicInfo> selectRecipeBasicInfosByTag(@Param("tag")String tag);
	List<RecipeBasicInfo> selectRecipeBasicInfosByRecommend(@Param("amount")int amount);
	List<RecipeBasicInfo> selectRecipeBasicInfosByDislike();
	List<RecipeBasicInfo> selectRecipeBasicInfosBySubscribe();
	String getRecipeBasicInfosMaxId();
	int countRecipeBasicInfos();
	
	//Use for test
	void deleteAllRecipeBasicInfo();
}
