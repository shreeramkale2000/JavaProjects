package org.ritvik.quick.myquick;

import java.io.IOException;
import java.util.Date;

import org.ritvik.utils.date.Formatter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Environment Variable " + System.getenv("envrn"));
		System.out.println("User Path " + System.getProperty("user_path"));
		
		JsonPojo pojo = new JsonPojo();
		pojo.setFirstName(args[0]);
		pojo.setLastName(args[1]);
		System.out.println(JSONUtilities.toJson(pojo));
		
		Formatter format = new Formatter();
		System.out.println(format.getFormattedDate("dd-MM-yy", new Date()));
	}
}