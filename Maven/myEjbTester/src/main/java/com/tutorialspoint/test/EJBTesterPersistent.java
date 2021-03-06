package com.tutorialspoint.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;

public class EJBTesterPersistent {

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
      brConsoleReader = 
      new BufferedReader(new InputStreamReader(System.in));
   }
   
   static String toLookup;
   
   public static void main(String[] args) {
	   toLookup = "/myEjbModule/LibraryPersistentBean!com.tutorialspoint.stateless.LibraryPersistentBeanRemote";
	   
	   EJBTesterPersistent ejbTester = new EJBTesterPersistent();

      ejbTester.testEntityEjb();
   }
   
   private void showGUI(){
      System.out.println("**********************");
      System.out.println("Welcome to Book Store");
      System.out.println("**********************");
      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
   }
   
   private void testEntityEjb(){

      try {
         int choice = 1; 

         LibraryPersistentBeanRemote libraryBean = (LibraryPersistentBeanRemote)ctx.lookup(toLookup);

         while (choice != 2) {
            String bookName;
            showGUI();
            String strChoice = brConsoleReader.readLine();
            choice = Integer.parseInt(strChoice);
            if (choice == 1) {
               System.out.print("Enter book name: ");
               bookName = brConsoleReader.readLine();
               Book book = new Book();
               book.setName(bookName);
               libraryBean.addBook(book);          
            } else if (choice == 2) {
               break;
            }
         }

         List<Book> booksList = libraryBean.getBooks();

         System.out.println("Book(s) entered so far: " + booksList.size());
         int i = 0;
         for (Book book:booksList) {
            System.out.println((i+1)+". " + book.getName());
            i++;
         }           
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }finally {
         try {
            if(brConsoleReader !=null){
               brConsoleReader.close();
            }
         } catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
      }
   }
}