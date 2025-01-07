package es.uca.iw.carteratiuca.views.convocatoria;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Todas las convocatorias")
@Route("admin-convocatoria-manage")
@Menu(order = 5, icon = "line-awesome/svg/folder-open.svg")
@RolesAllowed("ADMIN")

public class AdminConvocatoriaManageView extends Composite<VerticalLayout> {

    private final ConvocatoriaService service;
    private final Grid<Convocatoria> projectGrid;
    private final ConvocatoriaService convocatoriaService;
    private final List<Convocatoria> convocatorias;

    public AdminConvocatoriaManageView(ConvocatoriaService service, ConvocatoriaService convocatoriaService) {
        this.service = service;

        projectGrid = new Grid<>();
        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Convocatorias");
        h3.setWidth("max-content");
        getContent().add(h3);

        getContent().getStyle().set("flex-grow", "1");
        projectGrid.addColumn(Convocatoria::getNombre).setHeader("Nombre").setSortable(true);
        projectGrid.addColumn(Convocatoria::getFecha_inicial).setHeader("Fecha Inicial").setSortable(true);
        projectGrid.addColumn(Convocatoria::getFecha_final).setHeader("Fecha Final").setSortable(true);

        //boton para ir a modificar datos
        projectGrid.addComponentColumn(convocatoria -> {
            Button botonModificar = new Button("Modificar", event -> {
                UI.getCurrent().navigate(ConvocatoriaDataView.class).
                        ifPresent(detalle -> detalle.adminConvocatoriaDataView(convocatoria));
            });
            return botonModificar;
        });

        //boton para eliminar una convocatoria
        projectGrid.addComponentColumn(convocatoria -> {
            Button botonEliminar = new Button("Eliminar", event -> onBotonEliminarClick(convocatoria));
            botonEliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            return botonEliminar;
        });

        convocatorias = convocatoriaService.getConvocatorias();
        projectGrid.setItems(convocatorias);
        getContent().add(projectGrid);

        //boton para crear una convocatoria
        Button botonCrear = new Button("Crear Convocatoria", event -> {
            UI.getCurrent().navigate(CreateConvocatoriaView.class);
        });
        getContent().add(botonCrear);
        this.convocatoriaService = convocatoriaService;
    }

    public void onBotonEliminarClick(Convocatoria convocatoria) {
        // Mostrar un modal de confirmación
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Eliminar convocatoria");
        dialog.setText("¿Está seguro de que desea eliminar la convocatoria?");

        dialog.setRejectable(true);
        dialog.setRejectText("Atrás");

        dialog.setConfirmText("Eliminar");

        dialog.addConfirmListener(e -> {
            service.delete(convocatoria);
            convocatorias.remove(convocatoria);
            projectGrid.getDataProvider().refreshAll();
            Notification.show("Convocatoria eliminada con éxito.");
        });

        dialog.open();
    }
}
