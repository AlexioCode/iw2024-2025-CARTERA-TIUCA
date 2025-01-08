package es.uca.iw.carteratiuca.views.otp;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("Valoracion Técnica")
@Route("valoracion-estrategica-proyecto")
@RolesAllowed("OTP")
public class ValoTecnicaEspecificaView extends Composite<VerticalLayout> {
    public ValoTecnicaEspecificaView() {}

    public void editarValoracionTecnica (Proyecto proyecto, ProyectoService proyectoService) {
        FormLayout layout = new FormLayout();
    }
}
