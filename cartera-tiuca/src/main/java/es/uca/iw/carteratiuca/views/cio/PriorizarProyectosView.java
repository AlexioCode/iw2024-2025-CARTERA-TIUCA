package es.uca.iw.carteratiuca.views.cio;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.EmailService;
import es.uca.iw.carteratiuca.services.ProyectoService;
import es.uca.iw.carteratiuca.views.projects.DetailsProjectView;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Priorización final de proyectos valorados")
@Route("priorizacion-proyectos-final")
@Menu(order = 1, icon = "line-awesome/svg/mail-bulk-solid.svg")
@RolesAllowed("CIO")
public class PriorizarProyectosView extends VerticalLayout {

    ProyectoService proyectoService;
    EmailService emailService;

    public PriorizarProyectosView(ProyectoService proyectoService, EmailService emailService) {
        this.proyectoService = proyectoService;

        H3 h3 = new H3();
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        h3.setText("Proyectos que pueden ser valorados estratégicamente por el CIO");
        h3.setWidth("max-content");
        add(h3);

        List<Proyecto> proyectosAvaladosValoradosPorCIOyOTP = proyectoService.getProyectosValoradosPorCIOyOTP();
        Grid<Proyecto> proyectosGrid = new Grid<>();
        proyectosGrid.setItems(proyectosAvaladosValoradosPorCIOyOTP);

        proyectosGrid.addColumn(Proyecto::getTitulo)
                .setHeader("Titulo")
                .setSortable(true)
                .setAutoWidth(true);

        proyectosGrid.addColumn(Proyecto::getValoracionFinal)
                .setHeader("Valoración Final")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("valoracionFinal");

        // Establecer orden predeterminado
        proyectosGrid.sort(
                GridSortOrder.asc(proyectosGrid.getColumnByKey("valoracionFinal"))
                        .build()
        );

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonAñadirAcartera = new Button("Añadir a cartera", e -> {
                proyecto.setEstado(EstadoProyecto.ACEPTADO);
                proyecto.setGradoAvance(proyecto.getGradoAvance() + 25);
                proyectoService.update(proyecto);
                proyectosAvaladosValoradosPorCIOyOTP.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoProyectoAceptado(proyecto.getSolicitante(), "SI");
            });
            botonAñadirAcartera.setSizeFull();
            return botonAñadirAcartera;
        });

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonNoAñadirCartera = new Button("No añadir cartera", e -> {
                proyecto.setEstado(EstadoProyecto.DENEGADO);
                proyecto.setGradoAvance(0);
                proyectoService.update(proyecto);
                proyectosAvaladosValoradosPorCIOyOTP.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoProyectoAceptado(proyecto.getSolicitante(), "NO");
            });
            botonNoAñadirCartera.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            botonNoAñadirCartera.setSizeFull();
            return botonNoAñadirCartera;
        });

        // Botón detalles de proyecto
        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Detalles", event -> {
                UI.getCurrent().navigate(DetailsProjectView.class).
                        ifPresent(detalle -> detalle.editProject(proyecto));
            });
            return botonDetalles;
        });

        add(proyectosGrid);
    }
}
