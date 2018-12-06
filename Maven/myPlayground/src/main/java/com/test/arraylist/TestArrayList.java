package com.test.arraylist;

import java.util.ArrayList;

public class TestArrayList {

	public static void main(String[] args) {
		String abc = "hello";
		ArrayList<String> list = new ArrayList<>();
		list.add(abc);
		
		list.forEach(System.out::println);
		
		abc = "Hi";
		
		list.forEach(System.out::println);
	}

}
