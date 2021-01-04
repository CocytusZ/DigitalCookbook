package com.saint.digitalCookbook.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to list all units used in recipes
 * @author Zhouyao Yu
 *
 */
public enum UnitEnum {
    /**
     * Field of unit used in recipes
     */
	NONE(""),
	GRAM("gram"),
    KILOGRAM("kilogram"),
    LITER("liter"),
    MILLILITER("milliliter"),
	SLICE("slice");
    
	/**
	 * Field of name 
	 */
	String name;
	
	/**
	 * 1. constructor
	 * @param name
	 */
	private UnitEnum(String name) {
		this.name = name;
	}
    
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Return 
	 * @return
	 */
	public static List<String> toList(){
		List<String> list = new ArrayList<String>();
		for (UnitEnum unit : UnitEnum.values()) {
			list.add(unit.toString());
		}
		return list;
	}
}
