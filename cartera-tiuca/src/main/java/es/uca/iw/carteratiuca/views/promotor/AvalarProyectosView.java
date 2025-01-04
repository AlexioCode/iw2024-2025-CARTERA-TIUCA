package es.uca.iw.carteratiuca.views.promotor;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadoAvalacionProyecto;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.ProyectoService;
import es.uca.iw.carteratiuca.views.projects.DetailsProjectView;
import es.uca.iw.carteratiuca.views.userdata.UserDataView;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.awt.*;
import java.util.List;

@PageTitle("Avalar proyectos")
@Route(value = "avalar-proyectos")
@Menu(order = 4, icon = "line-awesome/svg/book-solid.svg")
@RolesAllowed("PROMOTOR")
public class AvalarProyectosView extends VerticalLayout {

    ProyectoService proyectoService;

    public AvalarProyectosView(AuthenticatedUser currentUser, ProyectoService proyectoService) {
        this.proyectoService = proyectoService;

        H3 h3 = new H3();
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        h3.setText("Proyectos que pueden ser avalados");
        h3.setWidth("max-content");
        add(h3);

        List<Proyecto> proyectosPorAvalar = proyectoService.getProyectosDePromotorPendientesDeAvalar(currentUser.get().get());
        Grid<Proyecto> gridProyectos = new Grid<>();
        gridProyectos.setItems(proyectosPorAvalar);

        gridProyectos.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);
        gridProyectos.addColumn(Proyecto::getNombreCorto).setHeader("Nombre Corto").setSortable(true);
        gridProyectos.addColumn(Proyecto::getSolicitante).setHeader("Solicitante").setSortable(true);
        gridProyectos.addColumn(Proyecto::getInteresados).setHeader("Interesados").setSortable(true);

        // Añadir botones Avalar y No avalar
        gridProyectos.addComponentColumn(proyecto -> {
            Button botonAvalar = new Button("Avalar", e -> {
                // Proyecto se establece como avalado
                proyecto.setEstadoAvalacion(EstadoAvalacionProyecto.SI);
                proyecto.setGradoAvance(proyecto.getGradoAvance() + 25);
                proyectoService.update(proyecto);
                proyectosPorAvalar.remove(proyecto);
                gridProyectos.getDataProvider().refreshAll();
            });
            return botonAvalar;
        });

        gridProyectos.addComponentColumn(proyecto -> {
            Button botonNoAvalar = new Button("No Avalar", e -> {
                // Proyecto se establece como no avalado y denegado
                proyecto.setEstadoAvalacion(EstadoAvalacionProyecto.NO);
                proyecto.setEstado(EstadoProyecto.DENEGADO);
                proyecto.setGradoAvance(0);

                proyectoService.update(proyecto);
                proyectosPorAvalar.remove(proyecto);
                gridProyectos.getDataProvider().refreshAll();
            });
            botonNoAvalar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            return botonNoAvalar;
        });

        gridProyectos.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Detalles", event -> {
                UI.getCurrent().navigate(DetailsProjectView.class).
                        ifPresent(detalle -> detalle.editProject(proyecto));
            });
            return botonDetalles;
        });

        add(gridProyectos);
    }
}
