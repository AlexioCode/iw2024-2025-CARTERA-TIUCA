package es.uca.iw.carteratiuca.views.projects;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import java.util.List;


@PermitAll
@Route("Detalle")
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
        List<Proyecto> allProjects = service.getProyectos();

        
    }

}
