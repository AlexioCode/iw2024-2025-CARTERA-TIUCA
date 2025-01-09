package es.uca.iw.carteratiuca.servicitoRest;

import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ConsumirServicioWeb {
    private static final Logger log =
            LoggerFactory.getLogger(ConsumirServicioWeb.class);

    private final UserService userService;

    public ConsumirServicioWeb(UserService userService) {
        this.userService = userService;
    }

    public void ejecutar(RestTemplate restTemplate) throws Exception {
        Json json = restTemplate.getForObject(
                "https://e608f590-1a0b-43c5-b363-e5a883961765.mock.pstmn.io/sponsors", Json.class
        );
        log.info(json.toString());
        int contador = 0;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (Promotor currentPromotor : json.getData()) {
            User nuevoPromotor = new User();
            nuevoPromotor.setId(UUID.randomUUID());
            nuevoPromotor.setUsername(currentPromotor.getNombre());
            nuevoPromotor.setCargo(currentPromotor.getCargo());
            nuevoPromotor.setActive(true);
            nuevoPromotor.setEmail("GeneradorDeEmail" + contador + "@uwu.com");
            nuevoPromotor.setPassword("12345678");
            nuevoPromotor.addRole(Role.PROMOTOR);
            nuevoPromotor.setUnit("unidad" + contador);
            userService.registerUser(nuevoPromotor);
            contador++;
        }
    }
}
