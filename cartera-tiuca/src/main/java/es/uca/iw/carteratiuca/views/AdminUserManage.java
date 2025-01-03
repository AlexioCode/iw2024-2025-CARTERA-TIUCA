package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.UserService;
import es.uca.iw.carteratiuca.views.userdata.UserDataView;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;
import java.util.UUID;

@PageTitle("Todos los usuarios")
@Route("admin-users-manage")
@Menu(order = 5, icon = "line-awesome/svg/folder-open.svg")
@RolesAllowed("ADMIN")
public class AdminUserManage extends Composite<VerticalLayout> {

    private final UserService service;

    public AdminUserManage(UserService service, AuthenticatedUser currentUser) {
        this.service = service;

        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Usuarios del sistema");
        h3.setWidth("max-content");
        getContent().add(h3);

        //recibir todos los usuarios
        List<User> allUsers = service.getActiveUsers();

        Grid<User> projectGrid = new Grid<>();
        getContent().getStyle().set("flex-grow", "1");
        projectGrid.addColumn(User::getUsername).setHeader("Nombre usuario").setSortable(true);
        projectGrid.addColumn(User::getEmail).setHeader("Email").setSortable(true);
        projectGrid.addColumn(User::getRoles).setHeader("Roles").setSortable(true);
        projectGrid.addColumn(User::getUnit).setHeader("Unidad").setSortable(true);

        //PONER BOTONES DE MODIFICAR Y ELIMINAR USUARIO

        projectGrid.addComponentColumn(usuario -> {
            Button botonModificar = new Button("Modificar", event -> {
                UI.getCurrent().navigate(UserDataView.class).
                        ifPresent(user -> user.adminUserDataView(usuario));
            });
            return botonModificar;
        });

        projectGrid.addComponentColumn(usuario -> {
            Button botonEliminar = new Button("Eliminar", event -> onBotonEliminarClick(usuario, currentUser));
            return botonEliminar;
        });

        setGridData(projectGrid);
        getContent().add(projectGrid);
    }

    public void onBotonEliminarClick(User usuario, AuthenticatedUser currentUser) {
        // Mostrar un modal de confirmación
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Eliminar cuenta");
        dialog.setText("¿Está seguro de que desea eliminar la cuenta y todos los proyectos asociados?");

        dialog.setRejectable(true);
        dialog.setRejectText("Atrás");

        dialog.setConfirmText("Eliminar cuenta");

        dialog.addConfirmListener(e -> {
            UUID userToDeleteId = usuario.getId();
            UUID currentUserId = currentUser.get().get().getId();
            service.delete(usuario.getId());
            Notification.show("Cuenta eliminada con éxito.");
            // Si el usuario eliminado es el mismo que el usuario actual, cerrar sesión
            if (currentUserId.equals(userToDeleteId))
                currentUser.logout();
        });

        dialog.open();
    }

    private void setGridData(Grid grid) {
        grid.setItems(service.getActiveUsers());
    }

}


