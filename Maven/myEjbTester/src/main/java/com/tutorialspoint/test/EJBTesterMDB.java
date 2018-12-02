package com.tutorialspoint.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;

public class EJBTesterMDB {

	BufferedReader brConsoleReader = null;
	Properties props;
	InitialContext context;
	{
		props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream("jndi.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			context = new InitialContext(props);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}

	static String toLookup;
	
	public static void main(String[] args) {
		toLookup = "/myEjbModule/LibraryPersistentBean!com.tutorialspoint.stateless.LibraryPersistentBeanRemote";

		EJBTesterMDB ejbTester = new EJBTesterMDB();

		ejbTester.testMessageBeanEjb();
	}

	private void showGUI() {
		System.out.println("**********************");
		System.out.println("Welcome to Book Store");
		System.out.println("**********************");
		System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	}

	private void testMessageBeanEjb() {

		try {
			int choice = 1;
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			QueueConnection connection = factory.createQueueConnection("jmsuser","jmsuser@123");
			QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) context.lookup("queue/BookQueue");
			QueueSender sender = session.createSender(queue);

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
					ObjectMessage objectMessage = session.createObjectMessage(book);
					sender.send(objectMessage);
					
					/*TextMessage textMessage = session.createTextMessage(bookName);
					sender.send(textMessage);*/
				} else if (choice == 2) {
					break;
				}
			}

			LibraryPersistentBeanRemote libraryBean = (LibraryPersistentBeanRemote) context.lookup(toLookup);

			List<Book> booksList = libraryBean.getBooks();

			System.out.println("Book(s) entered so far: " + booksList.size());
			int i = 0;
			for (Book book : booksList) {
				System.out.println((i + 1) + ". " + book.getName());
				i++;
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