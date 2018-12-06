package com.test.java8;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Java8tester {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("ABC", "XYZ");
		list.forEach(System.out::println);

		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);

		// Get the current date and time
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Current DateTime: " + currentTime);
	}

}
