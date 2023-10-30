package com.example.asm.service;

import javax.mail.MessagingException;

public interface MailService {

    // boolean sendEmail(String to, String subject, String content);

    void sendAsHtml(String to, String title, String html) throws MessagingException;
    
}
