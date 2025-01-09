package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserActivationTest {

    @MockBean
    private UserService userService;
    private User user;

    @Test
    public void shouldActivateUser() {
        //given a registered user
        user = new User();
        user.setUsername("prueba");
        user.setEmail("prueba@gmail.com");
        user.setPassword("pruebaprueba");
        user.setUnit("prueba");

        userService.registerUser(user);

        //when calling the method activateUser
        boolean activation = userService.activateUser(user.getEmail(), user.getRegisterCode());

        //then the user is active in the website

        assertThat(user.isActive()).isTrue();
    }

    @BeforeEach
    void setup(UserService userService) {
        this.userService = userService;
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
