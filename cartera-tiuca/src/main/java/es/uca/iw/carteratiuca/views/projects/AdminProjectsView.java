package es.uca.iw.carteratiuca.views.projects;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PageTitle("Todos los proyectos")
@Route("all-projects")
@Menu(order = 1, icon = "line-awesome/svg/folder-open.svg")
@PermitAll
public class AdminProjectsView extends Composite<VerticalLayout> {

    private final ProyectoService service;

    public AdminProjectsView(ProyectoService servicioProyecto) {
        service = servicioProyecto;
        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Proyectos del sistema");
        h3.setWidth("max-content");
        getContent().add(h3);


        //recibir todos los proyectos
        List<Proyecto> allProjects = service.getProyectosSinArchivar();

        Grid<Proyecto> projectGrid = new Grid<>();
        getContent().getStyle().set("flex-grow", "1");
        projectGrid.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);
        projectGrid.addColumn(Proyecto::getNombreCorto).setHeader("Nombre Corto").setSortable(true);
        projectGrid.addColumn(Proyecto::getCoste).setHeader("Coste").setSortable(true);
        projectGrid.addColumn(Proyecto::getEstado).setHeader("Estado").setSortable(true);
        projectGrid.addColumn(Proyecto::getGradoAvance).setHeader("Grado Avance").setSortable(true);

        projectGrid.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Detalles", event -> {
                UI.getCurrent().navigate(DetailsProjectView.class).
                        ifPresent(detalle -> detalle.editProject(proyecto));
            });
            return botonDetalles;
        });


        setGridData(projectGrid);
        getContent().add(projectGrid);


    }

    private void setGridData(Grid grid) {
        grid.setItems(service.getProyectos());
    }

}
