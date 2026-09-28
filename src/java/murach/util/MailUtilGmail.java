package murach.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        // 1 - Cấu hình gửi mail qua Brevo SMTP Relay
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp-relay.brevo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Timeout tránh treo Web
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");

        // 2 - Tạo Mail Session
        Session session = Session.getInstance(props);
        session.setDebug(true);

        // 3 - Tạo nội dung Mail hỗ trợ UTF-8 (tiếng Việt)
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=UTF-8");
        } else {
            message.setText(body);
        }

        // 4 - Địa chỉ gửi/nhận
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 5 - Kết nối với Brevo bằng SMTP Key của bạn
        Transport transport = session.getTransport("smtp");
        transport.connect(
            "smtp-relay.brevo.com", 
            "vanduy7526@gmail.com", 
            "xsmtpsib-b92b19927955cae7c9768bb89cc1594662bf3c3d45b440cc1e5eff26395b1c6a-cckHp8Cb021pBYso"
        );
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}