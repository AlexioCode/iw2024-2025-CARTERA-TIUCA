package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserActivationTest {

    private User user;
    private UserService userService;

    @Test
    public void shouldActivateUser() {
        //given a registered user
        User user = new User();
        userService.registerUser(user);

        //when calling the method activateUser
        boolean activation = userService.activateUser(user.getEmail(), user.getRegisterCode());

        //then the user is active in the website

        assertThat(user.isActive()).isTrue();
    }

    @Test
    public void shouldNotActivateUnregisteredUser() {
        //given an unregistered user
        User user = new User();
        user.setEmail("pruebita@gmail.com");

        //when calling the method activateUser
        boolean activation = userService.activateUser(user.getEmail(), user.getRegisterCode());

        //then the user isn't active in the website
        assertThat(activation).isFalse();
    }

}
