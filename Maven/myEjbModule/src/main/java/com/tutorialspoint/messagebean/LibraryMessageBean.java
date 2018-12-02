package com.tutorialspoint.messagebean;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistentBeanRemote;

@MessageDriven(name = "BookQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/BookQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class LibraryMessageBean implements MessageListener {

	@Resource
	private MessageDrivenContext mdctx;
	
	@EJB
	LibraryPersistentBeanRemote libraryBean;

	public LibraryMessageBean() {
	}

	public void onMessage(Message message) {
		//TextMessage textMessage = null;
		ObjectMessage objectMessage = null;
		Book book = null;
		try {
			/*textMessage = (TextMessage) message;
			System.out.println("Message Recieved - " + textMessage.getText());
			book = new Book();
			book.setName(textMessage.getText());*/
			
			objectMessage = (ObjectMessage) message;
			book = (Book) objectMessage.getObject();
			System.out.println(book);
			libraryBean.addBook(book);
		} catch (JMSException ex) {
			mdctx.setRollbackOnly();
			ex.printStackTrace();
		}
	}
}