package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.services.UserService;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Todos los usuarios")
@Route("admin-usersmanage")
@Menu(order = 5, icon = "line-awesome/svg/folder-open.svg")
@RolesAllowed("ADMIN")
public class AdminUserManage extends Composite<VerticalLayout> {

    private final UserService service;

    public AdminUserManage(UserService service) {
        this.service = service;

        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Proyectos del sistema");
        h3.setWidth("max-content");
        getContent().add(h3);

        //recibir todos los usuarios
        List<User> allUsers = service.getActiveUsers();

        
    }

}
