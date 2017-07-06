package com.gramLabs.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* This Class will read the latest email and will fetch the registration code from it.*/

public class EmailFetcherAndReader extends MainClass {

	static String workingDir = System.getProperty("user.dir");
	static Properties properties = new Properties();
	static Folder inbox;
	static Store store;
	static Message message = null;
	
	public static void settingServerProperties(String email_add) throws MessagingException{
		properties.put("mail.imap.host", "imap.gmail.com");
		properties.put("mail.imap.port", "993");
		// Settings for SSL
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf("993"));
		Session emailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email_add, property.getProperty("acct_password"));
			}
		});
		// creating the POP3 store object and connecting with the pop server
		store = emailSession.getStore("imap");
		store.connect("pop.gmail.com", email_add, property.getProperty("acct_password"));
		inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
	}
	
	public static void clearEmailInbox(String email_add) throws MessagingException, IOException, InterruptedException {
		settingServerProperties(email_add);
		int count = 0;
		Boolean flag = true;
		for (int i = 0; i < 5; i++) {
			if (flag)
				try {
					count = inbox.getMessageCount();
					flag = false;
				} catch (Exception e) {
					flag = true;
					Thread.sleep(2000);
				}
		}
		for (int x = 1; x <= count; x++) {
			message = inbox.getMessage(x);
			message.setFlag(Flags.Flag.DELETED, true);
		}
		inbox.expunge();
		store.close();
	}

	public static void fetchEmail(String email_add) throws MessagingException, IOException, InterruptedException {
		File file = new File(workingDir + "\\src\\main\\resources\\Raw_Email.html");
		settingServerProperties(email_add);
		BufferedWriter bufferedWriter;
		// Fetching the latest mail contents
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
		Boolean flag = true;
		for (int i = 0; i < 10; i++) {
			if (flag)
				try {
					message = inbox.getMessage(inbox.getMessageCount());
					flag = false;
				} catch (Exception e) {
					flag = true;
					Thread.sleep(5000);
				}
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer writer = new FileWriter(file);
		bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write((String)message.getContent());
		bufferedWriter.close();
		message.setFlag(Flags.Flag.DELETED, true);
		inbox.expunge();
		store.close();
	}	

	public static void fetchVerificationCode(String email_add) throws IOException, MessagingException, InterruptedException {
		fetchEmail(email_add);
		// TODO It should be moved to the class level
		File file = new File(workingDir + "\\src\\main\\resources\\Raw_Email.html");
		for (int i = 0; i < 10; i++) {
			if (file.length() == 0) {
				fetchEmail(email_add);
			} else {
				break;
			}
		}
		Document doc = Jsoup.parse(file, "UTF-8");
		Element data = doc.select("h2").first();
		String registration_code = data.text();
		property.setProperty("reg_code", registration_code);
	}


	public static void fetchJoiningInvitationURL(String email_add) throws IOException, MessagingException, InterruptedException {
		fetchEmail(email_add);
		// TODO It should be moved to the class level
		File file = new File(workingDir + "\\src\\main\\resources\\Raw_Email.html");
		Document doc = Jsoup.parse(file, "UTF-8");
		Element data = doc.select("a").get(1);
		String joining_url = data.absUrl("href");
		property.setProperty("joining_url", joining_url);
	}
	
	public static void fetchMemberInvitationMailHeader(String email_id) throws IOException, MessagingException, InterruptedException {
		fetchEmail(email_id);
		// TODO It should be moved to the class level
		File file = new File(workingDir + "\\src\\main\\resources\\Raw_Email.html");
		for (int i = 0; i < 10; i++) {
			if (file.length() == 0) {
				fetchEmail(System.getProperty("existing_member"));
			} else {
				break;
			}
		}
		Document doc = Jsoup.parse(file, "UTF-8");
		String invitation_mail_header = doc.body().getElementsByTag("h1").text();
//		Element data = doc.select("h1").get(3);
//		String invitation_mail_header = data.text();
		property.setProperty("invitation_mail_header", invitation_mail_header);
	}
}
