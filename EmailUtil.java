/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {
    
    private static final String Email_Remetente = "edsonmigjr@gmail.com";
    private static final String Senha_app = "sdbyiizgrxejnfek";
    
    public static void EnviarCodigo (String emailDestinatario, String codigo) throws MessagingException {
        
        System.out.println("Tentando enviar email para: " + emailDestinatario);
        System.out.println("Codigo: " + codigo);
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session sessaoEmail = Session.getInstance(props, new Authenticator() {
        
            protected PasswordAuthentication getPasswordAuthentication() {
                
                return new PasswordAuthentication(Email_Remetente, Senha_app);
            }
        });
        
        Message mensagem = new MimeMessage(sessaoEmail);
        mensagem.setFrom(new InternetAddress(Email_Remetente));
        mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
        mensagem.setSubject("Código de Recuperação de Senha");
        mensagem.setText("Seu código de recuperação é: " + codigo + "\n\nEste código expira em 15 minutos.");
        
        Transport.send(mensagem);
    }
}