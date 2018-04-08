package org.ritvik.quick.myquick;

import java.io.IOException;

/**
 * Standalone Jackson Example
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("System Property " + System.getProperty("user_path"));
		System.out.println("Environment Variable " + System.getenv("user_path"));
		
		JsonPojo reqPojo = new JsonPojo();
		reqPojo.setFirstName(args[0]);
		reqPojo.setLastName(args[1]);
		String resJson = JSONUtilities.toJson(reqPojo);
		System.out.println(resJson);
		
		JsonPojo resPojo = (JsonPojo) JSONUtilities.fromJson(resJson, JsonPojo.class);
		System.out.println(resPojo.getFirstName());
	}
}