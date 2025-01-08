package es.uca.iw.carteratiuca.servicitoRest;

import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class ConsumirServicioWeb {
    private static final Logger log =
            LoggerFactory.getLogger(ConsumirServicioWeb.class);

    private UserService userService;

    public ConsumirServicioWeb (UserService userService) { this.userService = userService; }

    public static void main(String[] args) {
        SpringApplication.run(ConsumirServicioWeb.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Json json = restTemplate.getForObject(
                    "https://e608f590-1a0b-43c5-b363-e5a883961765.mock.pstmn.io/sponsors", Json.class
            );
            log.info(json.toString());
            int contandor = 0;
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            for (Promotor currentPromotor : json.getData())
            {
                User nuevoPromotor = new User();
                nuevoPromotor.setId(UUID.randomUUID());
                nuevoPromotor.setUsername(currentPromotor.getNombre());
                nuevoPromotor.setCargo(currentPromotor.getCargo());
                nuevoPromotor.setActive(true);
                nuevoPromotor.setEmail("GeneradorDeEmail" + contandor + "@uwu.com" );
                nuevoPromotor.setPassword(passwordEncoder.encode("12345678"));
                nuevoPromotor.addRole(Role.PROMOTOR);
                nuevoPromotor.setUnit("unidad" + contandor);
                userService.registerUser(nuevoPromotor);
                contandor++;
            }
        };
    }
}
