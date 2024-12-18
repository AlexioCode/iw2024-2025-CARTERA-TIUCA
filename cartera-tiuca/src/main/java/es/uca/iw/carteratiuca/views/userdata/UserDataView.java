package es.uca.iw.carteratiuca.views.userdata;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.UserService;
import jakarta.annotation.security.PermitAll;

@PageTitle("Datos de usuario")
@Route(value = "user-data")
@PermitAll
public class UserDataView extends VerticalLayout {

    private final UserService service;

    private final H1 title;
    private final TextField username;
    private final EmailField email;
    private final TextField unit;
    private final PasswordField password;
    private final PasswordField password2;
    private final Button submitButton;
    private final Button discardChangesButton;

    private final BeanValidationBinder<User> binder;

    public UserDataView(AuthenticatedUser user, UserService service) {
        this.service = service;

        title = new H1("Datos de usuario");

        username = new TextField("Nombre de usuario");
        username.setId("username");

        email = new EmailField("Email");
        email.setId("email");

        unit = new TextField("Unidad");
        unit.setId("unit");

        password = new PasswordField("Contraseña");
        password.setId("password");
        password.setMinLength(8);
        password.setErrorMessage("La contraseña debe tener al menos 8 caracteres.");

        password2 = new PasswordField("Repite la contraseña");
        password2.setId("password2");
        password2.setMinLength(8);
        password.setErrorMessage("La contraseña debe tener al menos 8 caracteres.");

        submitButton = new Button("Guardar cambios");
        submitButton.setId("submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        discardChangesButton = new Button("Cancelar");
        discardChangesButton.setId("cancel");
        discardChangesButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Vinculación de campos del formulario
        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
        binder.removeBinding(password);

        // Cargar datos del usuario autenticado
        User currentUser = user.get().get();
        binder.setBean(currentUser);

        // Layout para los botones
        HorizontalLayout buttonsLayout = new HorizontalLayout(discardChangesButton, submitButton);

        add(title, username, email, unit, password, password2, buttonsLayout);

        // Listeners para los botones
        submitButton.addClickListener(e -> onSubmitButtonClick());
        discardChangesButton.addClickListener(e -> onDiscardChangesButtonClick());
    }

    private void onSubmitButtonClick() {
        // TO DO
    }

    private void onDiscardChangesButtonClick() {
        // TO DO
    }
}
