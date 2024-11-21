package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("Mis proyectos")
@Route(value = "userProyects")
@PermitAll
public class UserProyectsView extends VerticalLayout {
}
