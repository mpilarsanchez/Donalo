package com.DONALO.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailNotificacionServicio {
@Autowired(required=false)
private JavaMailSender mailSender;
    


//Async o Asincrono lo que hace es no esperar que se ejecute el mail
// Sino que lo ejecuta en un hilo paralelo (Hace que el usuario tenga una respuesta
// inmediate.
 @Async   
public void enviar(String cuerpo,String titulo,String mail) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(mail);
    mensaje.setFrom("noreply@donalo.com");
    mensaje.setSubject(titulo);
    mensaje.setText(cuerpo);
    
    
   mailSender.send(mensaje);
    
}   
 
 

 
 
 
 
 
 
 
 
 
}
