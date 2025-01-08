package es.uca.iw.carteratiuca.views.otp;

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
import es.uca.iw.carteratiuca.views.projects.DetailsProjectView;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Valoracion Técnica")
@Route("valoracion-estrategica")
@Menu(icon = "line-awesome/svg/mail-bulk-solid.svg")
@RolesAllowed("OTP")
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

        List<Proyecto> proyectosAvaladosSinValorarOTP = proyectoService.getProyectosAvaladosSinValorarCIO();
        Grid<Proyecto> proyectosGrid = new Grid<>();
        proyectosGrid.setItems(proyectosAvaladosSinValorarOTP);

        proyectosGrid.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);
        proyectosGrid.addColumn(Proyecto::getNombreCorto).setHeader("Nombre Corto").setSortable(true);


        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Detalles", event -> {
                UI.getCurrent().navigate(ValoTecnicaEspecificaView.class).
                        ifPresent(detalle -> detalle.editarValoracionTecnica(proyecto, proyectoService));
            });
            return botonDetalles;
        });

    }
}
