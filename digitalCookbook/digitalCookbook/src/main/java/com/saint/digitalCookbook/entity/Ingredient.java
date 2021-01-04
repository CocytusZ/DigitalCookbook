package com.saint.digitalCookbook.entity;

import com.saint.digitalCookbook.constant.UnitEnum;

/**
 * Class to store ingredient info 
 * It is one of attributes of Recipe Class
 * It is corresponding to the Ingredient table in MySQL
 * @author Zhouyao Yu
 */
public class Ingredient implements Cloneable{

	private String recipeId;
	private String ingredientId;
	private String name;
	private float amount;
	private String unit;

	/**
	 * 1. Constructor for mybatis
	 * @param recipeId
	 * @param ingredientId
	 * @param name
	 * @param amount
	 * @param unit
	 */
	public Ingredient(String recipeId, String ingredientId, String name, float amount, String unit) {
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}	
	
	public static Ingredient create() {
		return new Ingredient("", "", "", 1, "");
	}

	// ------ Getter & Setter for Mybatis ------ Start ----- //
	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	// ------ Getter & Setter for Mybatis ------ Start ----- //

	public void setUnit(UnitEnum unit) {
		this.unit = unit.toString();
	}

	@Override
	public String toString() {
		return "Ingredient [recipeId=" + recipeId + ", ingredientId=" + ingredientId + ", name=" + name + ", amount="
				+ amount + ", unit=" + unit + "]";
	}
	
	/**
	 * Used to clone a new list of ingredients
	 */
	@Override
	public Ingredient clone() {
		Ingredient dest = null;
		try {
			dest = (Ingredient) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone Ingredient failed");
			e.printStackTrace();
		}
		return dest;
	}
}
