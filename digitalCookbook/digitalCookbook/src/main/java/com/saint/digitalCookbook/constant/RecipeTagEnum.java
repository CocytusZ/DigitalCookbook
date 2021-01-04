package com.saint.digitalCookbook.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store all types of Recipe Tags
 * @author Zhouyao Yu
 *
 */
public enum RecipeTagEnum {

	/**
	 * Field of Recipe tags
	 */
	MEAT_DISH(0, "Meat"),
	VEGETAVLE_DISH(1, "Vegetable"),
	MAIN_DISH(2, "Main dish"),
	EGG_DISH(3, "Egg"),
	SNACK_DISH(4, "Snack"),
	JUICE_DISH(5, "Juice"),
	TOAST_DISH(6, "Toast");
	
	int seq;
	String name;
	/**
	 * 1. Constructor
	 * @param seq
	 */
	private RecipeTagEnum(int seq, String name) {
		this.seq = seq;
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	public static List<String> toList(){
		List<String> list = new ArrayList<String>();
		for (RecipeTagEnum tag : RecipeTagEnum.values()) {
			list.add(tag.toString());
		}
		return list;
	}
	
	public static boolean contains(String tagStr) {
		boolean ret = false;
		for (RecipeTagEnum tag : RecipeTagEnum.values()) {
			if (tag.toString().equals(tagStr)) {
				ret = true;
			}
		}
		return ret;
	}
}
