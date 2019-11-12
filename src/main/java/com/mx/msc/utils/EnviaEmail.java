/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author sergiov
 */
public class EnviaEmail {

    public boolean enviaEmail(String remitente, String destinatario, String asunto, String mensaje) {
        boolean enviado = false;
        final String username = "prueba.itesz@gmail.com";
        final String password = "Sergio2019_ITESZ";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            enviado = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return enviado;
    }

}
