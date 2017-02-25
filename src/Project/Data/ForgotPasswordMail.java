/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;


import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ForgotPasswordMail {  

   
    public static void emailRecommendTrigger(String name, String receiverMail, String tokenID, Object expirationDate){
        final String username = "jananinbad@gmail.com";
        final String password = "nbad1234";
        String fpLink = "http://localhost:8084/Web/UserController?action=resetpassword&value="+tokenID;
        String[] to = { receiverMail };
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
        });
        //Session session = Session.getInstance(props, null);

        try {

            Message message = new MimeMessage(session);
             InternetAddress me = new InternetAddress("researchers@uncc.edu");
                try {
                    me.setPersonal("Research Exchange Program");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                message.setFrom(me);
            for (int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }
            message.setSubject("Forgot Password Link");
            message.setText("Dear "+ name  + ",\n" 
                    + "\nPlease Click on the link below to reset the password. \n"+fpLink+
                                        "\n\nRegards,\n" + "Researchers Exachange Program ");

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
       
}

    
