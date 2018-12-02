package com.tutorialspoint.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.stateless.LibrarySessionBeanRemote;

@SuppressWarnings("unchecked")
public class EJBTesterStateless {
	BufferedReader brConsoleReader = null;
	Properties props;
	InitialContext ctx;
	{
		props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream("jndi.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			ctx = new InitialContext(props);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	static String toLookup;
	
	public static void main(String[] args) {
		toLookup = "/myEjbModule/LibrarySessionBean!com.tutorialspoint.stateless.LibrarySessionBeanRemote";
		EJBTesterStateless ejbTester = new EJBTesterStateless();
		ejbTester.testStatelessEjb();
	}

	private void showGUI() {
		System.out.println("**********************");
		System.out.println("Welcome to Book Store");
		System.out.println("**********************");
		System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	}

	private void testStatelessEjb() {
		try {
			int choice = 1;
			LibrarySessionBeanRemote libraryBean = (LibrarySessionBeanRemote) ctx.lookup(toLookup);
			while (choice != 2) {
				String bookName;
				showGUI();
				String strChoice = brConsoleReader.readLine();
				choice = Integer.parseInt(strChoice);
				if (choice == 1) {
					System.out.print("Enter book name: ");
					bookName = brConsoleReader.readLine();
					libraryBean.addBook(bookName);
					System.out.print("Book Added \n");
				} else if (choice == 2) {
					break;
				}
			}
			List<String> booksList = libraryBean.getBooks();
			System.out.println("Book(s) entered so far: " + booksList.size());
			for (int i = 0; i < booksList.size(); ++i) {
				System.out.println((i + 1) + ". " + booksList.get(i));
			}
			LibrarySessionBeanRemote libraryBean1 = (LibrarySessionBeanRemote) ctx.lookup(toLookup);
			List<String> booksList1 = libraryBean1.getBooks();
			System.out.println("***Using second lookup to get library stateless object***");
			System.out.println("Book(s) entered so far: " + booksList1.size());
			for (int i = 0; i < booksList1.size(); ++i) {
				System.out.println((i + 1) + ". " + booksList1.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (brConsoleReader != null) {
					brConsoleReader.close();
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}