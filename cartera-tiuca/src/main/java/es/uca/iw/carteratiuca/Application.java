package es.uca.iw.carteratiuca;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import es.uca.iw.carteratiuca.servicitoRest.ConsumirServicioWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = "cartera-tiuca")
public class Application implements AppShellConfigurator, CommandLineRunner {

    @Autowired
    ConsumirServicioWeb consumirServicioWeb;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consumirServicioWeb.ejecutar(new RestTemplate());
    }
}
