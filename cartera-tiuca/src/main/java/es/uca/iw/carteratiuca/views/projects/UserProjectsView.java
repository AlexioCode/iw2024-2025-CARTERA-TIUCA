package es.uca.iw.carteratiuca.views.projects;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PageTitle("Mis proyectos")
@Route(value = "user-projects")
@PermitAll
public class UserProjectsView extends Composite<VerticalLayout> {

    private final ProyectoService service;

    public UserProjectsView(AuthenticatedUser user, ProyectoService servicioProyecto) {
        service = servicioProyecto;
        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Mis proyectos");
        h3.setWidth("max-content");
        getContent().add(h3);

        // Obtener la lista de proyectos de un usuario
        List<Proyecto> misProyectos = service.getProyectosBySolicitante(user.get().get());

        for (Proyecto proyecto : misProyectos) {

            VerticalLayout infoProyecto = new VerticalLayout();
            HorizontalLayout horNombreCorto = new HorizontalLayout();

            H5 h5nombreCorto = new H5("Nombre Corto: ");
            Span nombreCorto = new Span(proyecto.getNombreCorto());
            horNombreCorto.add(h5nombreCorto, nombreCorto);
            infoProyecto.add(horNombreCorto);

            H5 h5coste = new H5("Coste: ");
            Span coste = new Span(proyecto.getCoste().toString());
            HorizontalLayout horCoste = new HorizontalLayout(h5coste, coste);
            infoProyecto.add(horCoste);

            H5 h5estado = new H5("Estado: ");
            Span estado = new Span(proyecto.getEstado().toString());
            HorizontalLayout horEstado = new HorizontalLayout(h5estado, estado);
            infoProyecto.add(horEstado);

            H5 h5gradoAvance = new H5("Grado de avance: ");
            Span gradoAvance = new Span(proyecto.getGradoAvance().toString() + "%");
            HorizontalLayout horGradoAvance = new HorizontalLayout(h5gradoAvance, gradoAvance);
            infoProyecto.add(horGradoAvance);

            Button botonDetalles = new Button("Más Detalles", event -> {
                UI.getCurrent().navigate(DetailsProjectView.class).
                        ifPresent(detalle -> detalle.editProject(proyecto));
            });

            infoProyecto.add(botonDetalles);

            Details details = new Details(proyecto.getTitulo(), infoProyecto);

            getContent().add(details);
        }

    }
}
