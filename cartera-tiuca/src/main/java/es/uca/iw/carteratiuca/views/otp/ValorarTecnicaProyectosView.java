package es.uca.iw.carteratiuca.views.otp;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

@PageTitle("Valoracion Técnica")
@Route("valoracion-estrategica")
@Menu(icon = "line-awesome/svg/mail-bulk-solid.svg")
@PermitAll
public class ValorarTecnicaProyectosView  extends Composite<VerticalLayout> {
    ProyectoService proyectoService;

    public ValorarTecnicaProyectosView(ProyectoService proyectoService) {
    this.proyectoService = proyectoService;


        H3 h3 = new H3();
        h3.setWidth("100%");
        getStyle().set("flex-grow", "1");
        h3.setText("Proyectos que pueden ser valorados técnicamente por el OTP");
        h3.setWidth("max-content");
        getContent().add(h3);

    }
}
