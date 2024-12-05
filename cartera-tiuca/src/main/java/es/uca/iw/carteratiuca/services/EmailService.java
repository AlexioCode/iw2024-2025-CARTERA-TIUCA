package es.uca.iw.carteratiuca.services;

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

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private String getServerUrl() {

        // Generate the server URL
        String serverUrl = "http://";
        serverUrl += InetAddress.getLoopbackAddress().getHostAddress();
        serverUrl += ":" + serverPort + "/";
        return serverUrl;

    }

    public boolean sendRegistrationEmail(User user) {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        String subject = "Bienvenido a CarteraTIUCA";
        String body = "Para activar tu cuenta de usuario, dirígete a la página " + getServerUrl()
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

}
