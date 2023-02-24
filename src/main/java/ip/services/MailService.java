package ip.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	private Properties props = new Properties();

	private static final String SENDER_PROPS = "ip.services.sender";
	private static final String MAIL_PROPS = "mail.properties";
	
	private static final String SENDER_NAME = "CustomerServiceIP";
	private static final String TITLE = "Response";
		
	private static String senderMail = "";
	private static String senderPass = "";
	
	private static MailService mailService;
	
	
	private MailService() {
		loadConfiguration();
		readServerCredentials();
	}
	
	public static MailService getMailService() {
		if(mailService == null) 
			mailService = new MailService();
		return mailService;
	}
	
	private void readServerCredentials() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle(SENDER_PROPS);
		senderMail = bundle.getString("senderMail");
		senderPass = bundle.getString("senderPass");
	}
	
	private Properties loadConfiguration() {
		try {
			props.load(getClass().getResourceAsStream(MAIL_PROPS));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public boolean sendMail(String receiver, String content) {
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, senderPass);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderMail, SENDER_NAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			message.setSubject(TITLE);
			message.setText(content);
			
			Transport.send(message);
			
			return true;
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
			
			return false;
		}
		
	}
	
}
