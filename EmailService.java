import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    public EmailService(){}

    public static void sendCustomerCreatedEmail(Customer customer){
        //Set the SMTP server and port
        String smtpHost = "smtp-relay.sendinblue.com";
        int smtpPort = 587;

        //Set the SMTP username and password
        String username = "amegotashyn@gmail.com";
        String password = "MQbFEcpZ3hnGHYWr";

        // Set the email message
        String to = customer.getEmail();
        String subject = "Customer Created";
        String body = String.format("<html><body><p>Good day %s,<br><br>A customer account for you was created in SS Colecao.</p></body></html>",customer.getFullName());

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

    public static void sendOrderCreatedEmail(Order order){
        //Set the SMTP server and port
        String smtpHost = "smtp-relay.sendinblue.com";
        int smtpPort = 587;

        //Set the SMTP username and password
        String username = "amegotashyn@gmail.com";
        String password = "MQbFEcpZ3hnGHYWr";

        // Set the email message
        String to = order.getCustomer().getEmail();
        String subject = "Order Created";
        String body = String.format("<html><body><p>Good day %s,<br><br>An order was submitted for you.<br> Details:<br> %s</p></body></html>",order.getCustomerName(), order.detailedString());

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
