package es.uca.iw.carteratiuca.views.register;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.io.Serial;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.services.UserService;

@PageTitle("Registrar usuario")
@Route(value = "register")
//@RouteAlias("")
@AnonymousAllowed
public class UserRegistrationView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserService service;

    private final H1 title;

    private final TextField username;
    private final EmailField email;
    private final TextField unit;
    private final PasswordField password;
    private final PasswordField password2;


    private final Button register;
    private final H4 status;

    private final BeanValidationBinder<User> binder;

    public UserRegistrationView(UserService service) {
        this.service = service;

        title = new H1("Registrar usuario");

        username = new TextField("Nombre de usuario");
        username.setId("username");

        email = new EmailField("Correo electrónico");
        email.setId("email");

        unit = new TextField("Tu unidad");
        unit.setId("unit");

        password = new PasswordField("Contraseña");
        password.setId("password");
        password.setMinLength(8);
        password.setErrorMessage("La contraseña debe tener al menos 8 caracteres.");

        password2 = new PasswordField("Repite la contraseña");
        password2.setId("password2");
        password2.setMinLength(8);
        password.setErrorMessage("La contraseña debe tener al menos 8 caracteres.");

        register = new Button("Registrarse");
        register.setId("register");

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        setMargin(true);

        add(title, username, email, unit, password, password2, register, status);

        register.addClickListener(e -> onRegisterButtonClick());

        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);

        binder.setBean(new User());
    }

    public void onRegisterButtonClick() {
        if (binder.validate().isOk() & password.getValue().equals(password2.getValue())) {
            if (service.registerUser(binder.getBean())) {
                status.setText("Genial. Revise su bandeja de correos, por favor.");
                status.setVisible(true);
                binder.setBean(new User());
                password2.setValue("");
            } else {
                Notification.show("Ese nombre de usuario ya está en uso");

            }
        } else {
            Notification.show("Revise los datos introducidos, por favor.");
        }

    }

}
