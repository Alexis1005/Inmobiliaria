<<<<<<< HEAD
package com.mycompany.inmobiliaria.resources.config;

import jakarta.mail.*;
import jakarta.mail.internet.*;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inmobiliaria.resources.config;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.*;
import javax.mail.internet.*;
>>>>>>> a345f68 (ni idea)
import java.util.Properties;

public class EmailService {

<<<<<<< HEAD
    public static void enviarCorreo(String nombre, String telefono, String correo, String mensaje, int idPropiedad, String urlPropiedad) {
        // Configuración de las propiedades del servidor de correo
        String host = "smtp.gmail.com";
        final String user = "vespaalexis@gmail.com";  // Tu correo electrónico
        final String pass = "osmr utgj kipd wdxp";      // Tu contraseña de la cuenta de correo
=======
    public static void enviarCorreo(String nombre, String telefono, String correo, String mensaje) {
        // Configuración de las propiedades del servidor de correo
        String host = "smtp.gmail.com";
        final String user = "rag1973@gmail.com";  // Tu correo electrónico
        final String pass = "ysmf ppsh zljm nopy ";      // Tu contraseña de la cuenta de correo
>>>>>>> a345f68 (ni idea)
//iwxnxmjgcdrhyxt
        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Crear la sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Crear el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));  // Dirección de correo del remitente
<<<<<<< HEAD
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("vespaalexis@gmail.com"));  // Dirección del destinatario (puede ser fija o variable)
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(correo));

            message.setSubject("Nuevo mensaje de contacto");  // Asunto del correo
            message.setText("Nombre: " + nombre + "\n" +
                    "Teléfono: " + telefono + "\n" + 
                    "Correo: " + correo + "\n" +
                    "Mensaje: " + mensaje + "\n" +
                    "Haz click aquí para ver la propiedad: " + urlPropiedad);  // Cuerpo del correo
=======
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("andreskorell@gmail.com"));  // Dirección del destinatario (puede ser fija o variable)
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(correo));

            message.setSubject("Nuevo mensaje de contacto");  // Asunto del correo
            message.setText("Nombre: " + nombre + "\nTeléfono: " + telefono + "\nCorreo: " + correo + "\nMensaje: " + mensaje);  // Cuerpo del correo
>>>>>>> a345f68 (ni idea)

            // Enviar el correo
            Transport.send(message);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
    e.printStackTrace();  // Mostrar detalles del error
}
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> a345f68 (ni idea)
