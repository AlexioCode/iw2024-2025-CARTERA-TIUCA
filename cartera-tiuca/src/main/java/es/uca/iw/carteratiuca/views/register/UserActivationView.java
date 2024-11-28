package es.uca.iw.carteratiuca.views.register;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;
import es.uca.iw.carteratiuca.services.UserService;
import es.uca.iw.carteratiuca.views.UserProjectsView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serial;

@PageTitle("Activación de usuario")
@Route(value = "activate")
@Component // Required for unit testing
@Scope("prototype") // Required for IT testing
//@RouteAlias("")
@AnonymousAllowed
public class UserActivationView extends VerticalLayout {
    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserService service;
    private final H1 title;
    private final TextField email;
    private final TextField secretCode;
    private final Button activate;
    private final H4 status;

    public UserActivationView(UserService service) {
        this.service = service;

        title = new H1("Activar usuario");
        email = new TextField("Correo electrónico");
        email.setId("email");

        secretCode = new TextField("Código secreto de activación");
        secretCode.setId("secretCode");

        status = new H4();
        status.setId("status");

        activate = new Button("Activar cuenta");
        activate.setId("activate");

        status.setVisible(false);

        setMargin(true);

        add(title, new HorizontalLayout(email, secretCode), activate, status);

        activate.addClickListener(e -> onActivateButtonClick());
    }

    /**
     * Handler
     */
    public void onActivateButtonClick() {

        status.setVisible(true);

        if (service.activateUser(email.getValue(), secretCode.getValue())) {
            status.setText("Tu cuenta ha sido activada con éxito");
            add(new RouterLink("Log in", UserProjectsView.class));


        } else {
            status.setText("No se ha podido activar el usuario");
        }

    }

    public void setEmail(String email) {
        this.email.setValue(email);

    }

    public void setSecretCode(String secretCode) {
        this.secretCode.setValue(secretCode);
    }

    public String getStatus() {
        return status.getText();
    }

}
