package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@PageTitle("Datos del usuario")
@Route(value = "userData", autoLayout = false)
@PermitAll
public class UserDataView {

}
