package Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class Mail_Helper {

	static String password; //var pass server
	static String username; //variabile email server
	
	public static void send_mail_outlook(String to, String subject, String body) throws SendFailedException, MessagingException{
		

	    	       
	    String host = "outlook.office365.com";
	    String from=username;
	   
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props);
	    
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(from));
	    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
	    msg.setSubject(subject);
	    msg.setText(body);
	    
	    Transport.send(msg,username,password);
	       
	}
	
//-----------------------------------------------------------------------------------------------------------
	
	public static boolean setMail(String destinatario, String object, String msg ) throws SendFailedException, MessagingException {
		boolean invio=false;

		try{
		        String to=new String(destinatario);
		        String subject=new String(object);
		        String body=new String(msg);
		        send_mail_outlook( to, subject, body);
		        invio =true; 
		    
		}catch (MessagingException e) {
			invio = false;
		}
		return invio;
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static boolean checkMail(String mail) {
		boolean mailOk= true;
			  try {
			    InternetAddress emailAddr = new InternetAddress(mail);
			    emailAddr.validate();
			  } catch (AddressException e) {
			    mailOk = false;
			  }
			  return mailOk;
			}
	
//--------------------------------------------------------------------------------------------------
	
	public static String code(){
		final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int LENGHT = 8;
	    
		StringBuilder s = new StringBuilder(LENGHT);
		int max = ALPHABET.length(); 
        int min = 0; 
        int range = max - min + 1;
        for (int y =0; y<LENGHT; y++) {
        	int i = (int)(Math.random() * range) + min;		
        	s.append(ALPHABET.charAt(i));
        }
        return s.toString();
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static boolean confirMail(String destinatario) {
		boolean invio=false;
		String oggetto ="Conferma attivazione account RdF";
		String messaggio="La procedura di attivazione dell'account è andata a buon fine. \nOra puoi iniziare a giocare. ";
		try{
		        String to=new String(destinatario);
		        String subject=new String(oggetto);
		        String body=new String(messaggio);
		        send_mail_outlook( to, subject, body);
		        JOptionPane.showInternalMessageDialog(null, "Mail a "+ to +" è stata inviata correttamente");
		        invio =true;}
		catch (MessagingException e) {
		    JOptionPane.showInternalMessageDialog(null, "Mail non inviata: ");
			invio = false;
		}
		return invio;
	}

//--------------------------------------------------------------------------------------------------
	
		public static void setCredentials(String mail, String pswd) {
			username=mail;
			password=pswd;
		}
	
}

