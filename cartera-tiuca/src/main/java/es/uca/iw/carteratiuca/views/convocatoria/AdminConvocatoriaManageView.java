package es.uca.iw.carteratiuca.views.convocatoria;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Todas las convocatorias")
@Route("admin-convocatoria-manage")
@Menu(order = 5, icon = "line-awesome/svg/folder-open.svg")
@RolesAllowed("ADMIN")

public class AdminConvocatoriaManageView extends Composite<VerticalLayout> {

    private final ConvocatoriaService service;

    public AdminConvocatoriaManageView(ConvocatoriaService service) {
        this.service = service;

        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Convocatorias");
        h3.setWidth("max-content");
        getContent().add(h3);

        Grid<Convocatoria> projectGrid = new Grid<>();
        getContent().getStyle().set("flex-grow", "1");
        projectGrid.addColumn(Convocatoria::getNombre).setHeader("Nombre").setSortable(true);
        projectGrid.addColumn(Convocatoria::getFecha_inicial).setHeader("Fecha Inicial").setSortable(true);
        projectGrid.addColumn(Convocatoria::getFecha_final).setHeader("Fecha Final").setSortable(true);

        //boton para ir a modificar datos
        projectGrid.addComponentColumn(convocatoria -> {
            Button botonModificar = new Button("Modificar", event -> {
                UI.getCurrent().navigate(ConvocatoriaDataView.class);
            });
            return botonModificar;
        });


        setGridData(projectGrid);
        getContent().add(projectGrid);

        //boton para crear una convocatoria
        Button botonCrear = new Button("Crear Convocatoria", event -> {
            UI.getCurrent().navigate(CreateConvocatoriaView.class);
        });
        getContent().add(botonCrear);
    }


    private void setGridData(Grid grid) {
        grid.setItems(service.getConvocatorias());
    }
}
