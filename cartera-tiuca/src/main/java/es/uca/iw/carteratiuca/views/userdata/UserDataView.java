package es.uca.iw.carteratiuca.views.userdata;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.UserService;
import jakarta.annotation.security.PermitAll;

import java.io.IOException;
import java.io.InputStream;

@PageTitle("Datos de usuario")
@Route(value = "user-data")
@Menu(order = 3, icon = "line-awesome/svg/book-solid.svg")
@PermitAll
public class UserDataView extends VerticalLayout {

    private final UserService service;

    User userToModify;
    private final H1 title;
    private final TextField username;
    private final EmailField email;
    private final TextField unit;
    private final PasswordField password;
    private final PasswordField password2;
    private final Button discardChangesButton;
    private final Button submitButton;
    private final Button deleteAccountButton;

    private final BeanValidationBinder<User> binder;

    public UserDataView(AuthenticatedUser currentUser, UserService userService) {
        this.service = userService;

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

        discardChangesButton = new Button("Cancelar");
        discardChangesButton.setId("cancel");
        discardChangesButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_ERROR);

        submitButton = new Button("Guardar cambios");
        submitButton.setId("submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        deleteAccountButton = new Button("Eliminar cuenta");
        deleteAccountButton.setId("delete");
        deleteAccountButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_WARNING);

        // Vinculación de campos del formulario
        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
        binder.removeBinding(password); // Excluyo la contraseña para que no se autocomplete

        // Cargar datos del usuario autenticado
        userToModify = currentUser.get().get();
        binder.setBean(userToModify);


        add(title, username, email, unit, password, password2);

        // Subir foto de perfil
        MemoryBuffer buffer = new MemoryBuffer();
        Upload uploadProfilePicture = new Upload(buffer);
        uploadProfilePicture.addSucceededListener(event -> {
            try {
                // Read the data from the buffer.
                InputStream fileData = buffer.getInputStream();
                userToModify.setProfilePicture(fileData.readAllBytes());
            } catch(IOException e) { Notification.show("Error al subir la foto de perfil"); }
        });
        uploadProfilePicture.setWidth("max-content");
        add(uploadProfilePicture);


        // Layout para los botones
        HorizontalLayout buttonsLayout = new HorizontalLayout(discardChangesButton, submitButton, deleteAccountButton);
        add(buttonsLayout);

        // Listeners para los botones
        submitButton.addClickListener(e -> onSubmitButtonClick(userToModify));
        discardChangesButton.addClickListener(e -> onDiscardChangesButtonClick(userToModify));
        deleteAccountButton.addClickListener(e -> onDeleteAccountButton(userToModify));
    }

    private void onSubmitButtonClick(User user) {
        // Validar los datos introducidos por el usuario
        if (binder.validate().isOk() && password.getValue().equals(password2.getValue())) {
            // Actualizar la contraseña del usuario si no está vacía
            if (!password.isEmpty())
                user.setPassword(password.getValue());

            // Guardar los cambios en la base de datos
            User updatedUser = service.update(user);

            Notification.show("Datos de usuario actualizados con éxito.");
        } else {
            Notification.show("Por favor, revise los datos introducidos.");
        }
    }

    private void onDiscardChangesButtonClick(User user) {
        // Poner todos los campos en blanco
        username.clear();
        email.clear();
        unit.clear();
        password.clear();
        password2.clear();

        Notification.show("Cambios descartados.");
    }

    public void onDeleteAccountButton(User user) {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Eliminar cuenta");
        dialog.setText("¿Está seguro de que desea eliminar la cuenta y todos los proyectos asociados?");

        dialog.setRejectable(true);
        dialog.setRejectText("Atrás");

        dialog.setConfirmText("Eliminar cuenta");
        dialog.addConfirmListener(e -> {
            service.delete(user.getId()); // Lógica para eliminar el usuario
            Notification.show("Cuenta eliminada con éxito.");
        });
        dialog.open();
    }

    public void adminUserDataView(User user) {
        binder.setBean(user);
        userToModify = user;
    }
}
