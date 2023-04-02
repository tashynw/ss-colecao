import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    public EmailService(){}

    public static void sendTestEmail(){
        //Set the SMTP server and port
        String smtpHost = "smtp-relay.sendinblue.com";
        int smtpPort = 587;

        //Set the SMTP username and password
        String username = "amegotashyn@gmail.com";
        String password = "MQbFEcpZ3hnGHYWr";

        // Set the email message
        String to = "tashyn1@mailsac.com";
        String subject = "Test Email";
        String body = "<html><body><h1>This is a test email.</h1></body></html>";

        //Set email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");

        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@sscolecao.com", "SS Colecao"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            // Send the email message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
