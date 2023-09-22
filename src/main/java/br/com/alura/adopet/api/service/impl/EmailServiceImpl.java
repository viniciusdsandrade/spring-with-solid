package br.com.alura.adopet.api.service.impl;

import br.com.alura.adopet.api.exception.EmailUnsedException;
import br.com.alura.adopet.api.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    
    private final JavaMailSender emailSender;
    
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    
    @Override
    public void sendEmail(String to, String subject, String text) throws EmailUnsedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
