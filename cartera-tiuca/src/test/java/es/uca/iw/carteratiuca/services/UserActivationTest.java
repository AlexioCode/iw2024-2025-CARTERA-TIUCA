package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserActivationTest {

    @Mock
    private UserService userService;  // El servicio mockeado
    private User user;

    @Test
    public void shouldActivateUser() {
        user = new User();
        user.setUsername("prueba");
        user.setEmail("prueba@gmail.com");
        user.setPassword("pruebaprueba");
        user.setUnit("prueba");
        user.addRole(Role.USER);

        // Cuando registramos el usuario (simulado en el mock)
        userService.registerUser(user);

        // Activamos al usuario
        boolean activation = userService.activateUser(user.getEmail(), user.getRegisterCode());

        // Verificamos que el usuario esté activo
        assertThat(activation).isTrue();

    }


    /*
    @Test
    public void shouldNotActivateUnregisteredUser() {
        // Creamos un usuario no registrado
        User unregisteredUser = new User();
        unregisteredUser.setEmail("pruebita@gmail.com");

        // Simulamos que la activación fallará
        when(userService.activateUser(unregisteredUser.getEmail(), unregisteredUser.getRegisterCode())).thenReturn(false);

        // Verificamos que la activación falle
        boolean activation = userService.activateUser(unregisteredUser.getEmail(), unregisteredUser.getRegisterCode());
        assertThat(activation).isFalse();
    }
    */

}

