package br.com.alura.adopet.api.service;


import br.com.alura.adopet.api.exception.EmailUnsedException;

public interface EmailService {

    
   void sendEmail(String to, String subject, String text) throws EmailUnsedException;

}
