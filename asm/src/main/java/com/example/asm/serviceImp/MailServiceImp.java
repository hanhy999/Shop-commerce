package com.example.asm.serviceImp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.asm.service.MailService;

@Service
public class MailServiceImp implements MailService {

    // @Autowired
    // private JavaMailSender javaMailSender;

    // public boolean sendEmail(String to, String subject, String content) {
    //     try {
    //         SimpleMailMessage msg = new SimpleMailMessage();
    //         MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    //         MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    //         helper.setSubject(subject);
    //         helper.setFrom("tabletkindfire@gmail.com");
    //         helper.setTo(to);

    //         boolean html = true;
    //         helper.setText(content, html);

    //         javaMailSender.send(msg);
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         System.err.println("error send mail " + e.getMessage());
    //         e.printStackTrace();
    //         return false;
    //     }
    //     return true;
    // }

    // custom sendmail

    private static final String senderEmail = "yukilamanh9999@gmail.com";// change with your sender email
    private static final String senderPassword = "lyhsbexjwcbtzmln";// change with your sender password

    public void sendAsHtml(String to, String title, String html) throws MessagingException {
        System.out.println("Sending email to " + to);

        Session session = createSession();

        // create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        // sending message
        Transport.send(message);
        System.out.println("Done");
    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        // Đăng nhập vào email
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        return session;
    }
}
