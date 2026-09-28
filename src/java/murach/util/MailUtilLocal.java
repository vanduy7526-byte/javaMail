package murach.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilLocal {
    public static void sendMail(String to, String from, String subject, 
            String body, boolean bodyIsHTML)
            throws MessagingException {
    
    // GET A MAIL SESSION
    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.host", "localhost");
    props.put("mail.smtp.port", 25);
    Session session = Session.getDefaultInstance(props);
    session.setDebug(true);
    
    // CREATE A MESSAGE
    Message message = new MimeMessage(session);
    message.setSubject(subject);
    if (bodyIsHTML)
        message.setContent(body, "text/html");
    else
        message.setText(body);
    
    // ADDRESS THE MESSAGE
    Address fromAddress = new InternetAddress(from);
    Address toAddress = new InternetAddress(to);
    message.setFrom(fromAddress);
    message.setRecipient(Message.RecipientType.TO, toAddress);
    
    
    // SEND THE MESSAGE
    Transport.send(message);
    }
}