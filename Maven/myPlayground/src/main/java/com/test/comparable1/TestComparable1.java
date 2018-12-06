package com.test.comparable1;

import java.util.ArrayList;
import java.util.Collections;

public class TestComparable1 {

	public static void main(String[] args) {
		Shoe shoe1 = new Shoe(3,"heel");
		Shoe shoe2 = new Shoe(2,"heel");
		Shoe shoe3 = new Shoe(2,"heel");
		Shoe shoe4 = new Shoe(2,"able");
		Shoe shoe5 = new Shoe(4,"heel");
		
		/*System.out.println(shoe1.compareTo(shoe2));
		System.out.println(shoe2.compareTo(shoe3));
		System.out.println(shoe3.compareTo(shoe4));
		System.out.println(shoe4.compareTo(shoe5));*/
		
		ArrayList<Shoe> list = new ArrayList<>();
		list.add(shoe1);
		list.add(shoe2);
		list.add(shoe3);
		list.add(shoe4);
		list.add(shoe5);
		
		for (Shoe a : list) {
			System.out.println(a.getSize() + a.getDescription());
		}
		
		Collections.sort(list);
		System.out.println("After Sorting");
		
		for (Shoe a : list) {
			System.out.println(a.getSize() + a.getDescription());
		}
	}

}
