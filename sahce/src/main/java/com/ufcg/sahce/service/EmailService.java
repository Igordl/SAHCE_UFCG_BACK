package com.ufcg.sahce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = true)
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String assunto, String body) {

        System.out.println("Enviado Email...");

        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(toEmail);
        mensagem.setText(body);
        mensagem.setSubject(assunto);
        mailSender.send(mensagem);

        System.out.println("Email enviado...");

    }

}
