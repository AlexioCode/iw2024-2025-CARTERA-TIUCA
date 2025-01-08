package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String defaultMail;

    @Value("${server.port}")
    private int serverPort;

    MimeMessage message;
    MimeMessageHelper helper;

    String subject;
    String body;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;

        message = mailSender.createMimeMessage();
        helper = new MimeMessageHelper(message, "utf-8");
    }

    private String getServerUrl() {

        // Generate the server URL
        String serverUrl = "http://";
        serverUrl += InetAddress.getLoopbackAddress().getHostAddress();
        serverUrl += ":" + serverPort + "/";
        return serverUrl;

    }

    public boolean sendRegistrationEmail(User user) {

        subject = "Bienvenido a CarteraTIUCA";
        body = "Para activar tu cuenta de usuario, dirígete a la página " + getServerUrl()
                    + "activate e introduce tu correo y el siguiente código: "
                    + user.getRegisterCode();

        try {
            helper.setFrom(defaultMail);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean enviarCorreoAvalacion(User solicitante, String resultadoAvalacion) {

        if ("SI".equals(resultadoAvalacion)) {
            subject = "Su proyecto ha sido avalado";
            body = "El promotor ha avalado su propuesta de proyecto.";
        } else if ("NO".equals(resultadoAvalacion)) {
            subject = "Su proyecto no ha sido avalado";
            body = "El promotor ha decidido no avalar su propuesta de proyecto.";
        } else {
            // Si el resultado no es válido, no enviamos ningún correo.
            return false;
        }

        try {
            helper.setFrom(defaultMail);
            helper.setTo(solicitante.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
            return true;
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean enviarCorreoValoracionCIO(User solicitante, String valoracion) {

        if ("SI".equals(valoracion)) {
            subject = "Valoración positiva del CIO en su proyecto";
            body = "Su proyecto ha sido valorado positivamente por el CIO";
        } else if ("NO".equals(valoracion)) {
            subject = "Valoración negativa del CIO en su proyecto";
            body = "El CIO ha decidido que su proyecto queda descartado por motivos estratégicos";
        } else {
            // Si el resultado no es válido, no enviamos ningún correo.
            return false;
        }

        try {
            helper.setFrom(defaultMail);
            helper.setTo(solicitante.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
            return true;
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean enviarCorreoProyectoCreado(User promotor) {

        subject = "Nuevo proyecto por avalar";
        body = "Se le ha enviado para solicitud de proyecto para avalar";

        try {
            helper.setFrom(defaultMail);
            helper.setTo(promotor.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
            return true;
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean enviarCorreoProyectoAceptado(User solicitante, String decision) {

        subject = "¡ENHORABUENA, SU PROYECTO HA SIDO ACEPTADO!";
        body = "¡Enhorabuena! Su proyecto ha sido aceptado en la cartera de proyecto TI de la Universidad de Cádiz.";

        try {
            helper.setFrom(defaultMail);
            helper.setTo(solicitante.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            this.mailSender.send(message);
            return true;
        } catch (MailException | MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
