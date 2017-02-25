package Project.Data;


import java.io.UnsupportedEncodingException;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailDB {


    public static void emailRecommendTrigger(String name, String senderMail,  String friendMail, String messageTxt){
        final String username = "jananinbad@gmail.com";
        final String password = "nbad1234";
        String cLink = "http://localhost:8084/Web/signup.jsp";
        String[] to = { friendMail };
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

        try {

            Message message = new MimeMessage(session);
             InternetAddress me = new InternetAddress(senderMail);
                try {
                    me.setPersonal(name);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                message.setFrom(me);
            for (int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }
            message.setSubject("New Recommendation");
            message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
                    + "\nYou have been recommended for "+name+".\n\n"
                    +"Message:  "+messageTxt+"\n\n"
                    +"Below is the link to register.\n\n"+cLink
                    + "\n\nRegards,\n" + name + "");

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    public static void emailContactTrigger(String name, String friendMail, String messageTxt){
        final String username = "jananinbad@gmail.com";
        final String password = "nbad1234";
        String[] to = { friendMail };
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

        try {

            Message message = new MimeMessage(session);
             InternetAddress me = new InternetAddress("jananinbad@gmail.com");
                try {
                    me.setPersonal(name);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                message.setFrom(me);
            for (int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }
            message.setSubject("New Contact");
            message.setText("Dear "+ friendMail.split("\\@")[0]  + ",\n" 
                    + "\nYou have been added as a contact for "+name+".\n\n"
                    +"Message:  "+messageTxt
                    + "\n\nRegards,\n" + name + "");

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    
}
