package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import com.vaadin.flow.component.accordion.Accordion;

import java.math.BigDecimal;
import java.util.List;

import com.vaadin.flow.component.details.Details;

@PageTitle("Mis proyectos")
@Route(value = "userProjects")
@PermitAll
public class UserProjectsView extends Composite<VerticalLayout> {

    private ProyectoService service;

    private String titulo;
    private String nombreCorto;
    private BigDecimal coste;
    private EstadoProyecto estado;
    private int gradoAvance = 0;

    public UserProjectsView(AuthenticatedUser user) {

        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Mis proyectos");
        h3.setWidth("max-content");
        getContent().add(h3);
        Details details = new Details();
        Accordion accordion = new Accordion();

         //obtener la lista de proyectos de un usuario
        List<Proyecto> misProyectos = service.getProyectosBySolicitante(user.get().get());

        for(Proyecto proyecto : misProyectos) {
            Span nombreCorto = new Span(proyecto.getNombreCorto());
            Span coste = new Span(proyecto.getCoste().toString());
            Span estado = new Span(proyecto.getEstado().toString());
            Span gradoAvance = new Span(proyecto.getGradoAvance().toString());
            VerticalLayout infoProyecto = new VerticalLayout(nombreCorto, coste, estado, gradoAvance);
            //TO DO, AÑADIR FUNCIONALIDAD: llevará a otra vista donde se mostrarán los otros atributos de el proyecto actual
            Button botonDetalles = new Button("Más Detalles");
            infoProyecto.add(botonDetalles);
            accordion.add(proyecto.getTitulo(), infoProyecto);


            details.setSummaryText(titulo);


        }
    }
}
