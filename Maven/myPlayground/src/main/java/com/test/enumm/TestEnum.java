package com.test.enumm;

public class TestEnum {

	public static void main(String[] args) {
		//ItemType type3 = new ItemType("Project");

		ItemType type2 = ItemType.valueOf("PROJECT_SPECIFIC");

		ItemType type4 = ItemType.values()[0];

		ItemType type1 = ItemType.DEPARTMENTAL;
		
		System.out.println(type1);
		System.out.println(type4);
		System.out.println(type2);
	}

}