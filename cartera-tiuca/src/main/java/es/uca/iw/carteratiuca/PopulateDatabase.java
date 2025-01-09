package es.uca.iw.carteratiuca;

import com.github.javafaker.Faker;
import es.uca.iw.carteratiuca.entities.*;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import es.uca.iw.carteratiuca.services.ProyectoService;
import es.uca.iw.carteratiuca.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class PopulateDatabase implements CommandLineRunner {

    ProyectoService proyectoService;
    UserService userService;
    ConvocatoriaService convocatoriaService;
    Random rand = new Random();

    public PopulateDatabase(ProyectoService proyectoService, UserService userService, ConvocatoriaService convocatoriaService) {
        this.proyectoService = proyectoService;
        this.userService = userService;
        this.convocatoriaService = convocatoriaService;
    }

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker();

        if(userService.count() <= 3)
        {
            for (int i = 0; i < 10; i++)
            {
                User newUser = new User();
                newUser.setUsername(faker.name().username());
                newUser.setEmail(faker.internet().emailAddress());
                newUser.setUnit("Unidad Falsa");
                newUser.setCargo("Cargo Falso");
                newUser.setPassword("12345678");
                newUser.setRegisterCode(faker.lorem().characters());
                newUser.addRole(Role.USER);
                if (rand.nextBoolean())
                    newUser.addRole(Role.OTP);
                if(rand.nextBoolean())
                    newUser.addRole(Role.PROMOTOR);
                userService.save(newUser);
            }
        }

        if(proyectoService.count() <= 2)
        {
            List<User> usuarios = userService.getAllUser();
            List<User> promotores = userService.getByRole(Role.PROMOTOR);
            for (int i = 0; i < 20; i++)
            {
                Proyecto newProyecto = new Proyecto();
                JustificacionProyecto justificacion = new JustificacionProyecto();
                justificacion.setActualizarOferta(rand.nextBoolean());
                justificacion.setAltaCalidad(rand.nextBoolean());
                justificacion.setAumentarInvestigacion(rand.nextBoolean());
                justificacion.setConseguirTransparencia(rand.nextBoolean());
                justificacion.setGenerarValorCompartido(rand.nextBoolean());
                justificacion.setConsolidarGobiernoSostenible(rand.nextBoolean());
                justificacion.setReforzarPapelUCA(rand.nextBoolean());
                justificacion.setAlcance(faker.lorem().characters());
                newProyecto.setId(UUID.randomUUID());
                newProyecto.setJustificacion(justificacion);
                newProyecto.setConvocatoria(convocatoriaService.getConvocatoriaActual().get(0));
                newProyecto.setSolicitante(usuarios.get(rand.nextInt(usuarios.size())));
                newProyecto.setPromotor(promotores.get(rand.nextInt(promotores.size())));
                newProyecto.setTitulo(faker.lorem().characters());
                newProyecto.setNombreCorto(faker.lorem().characters());
                newProyecto.setNumEmpleados(0);
                newProyecto.setImportanciaPromotor(rand.nextInt(5) + 1);
                newProyecto.setInteresados(faker.lorem().characters());
                newProyecto.setFinanciacionInteresado(new BigDecimal(rand.nextFloat()).multiply(new BigDecimal(500)));
                newProyecto.setEstado(EstadoProyecto.REGISTRADO);
                newProyecto.setEstadoAvalacion(EstadosAvalacionValoracion.PORDETERMINAR);
                newProyecto.setEstadoValoracionCIO(EstadosAvalacionValoracion.PORDETERMINAR);
                newProyecto.setEstadoValoracionOTP(EstadosAvalacionValoracion.PORDETERMINAR);
                byte[] bytesRandom = new byte[1024 * 5];
                rand.nextBytes(bytesRandom);
                newProyecto.setMemoria(bytesRandom);
                proyectoService.registerProyecto(newProyecto);
            }
        }

    }
}
