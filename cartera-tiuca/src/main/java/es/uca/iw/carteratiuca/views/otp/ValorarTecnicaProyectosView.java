package es.uca.iw.carteratiuca.views.otp;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.JustificacionProyectoService;
import es.uca.iw.carteratiuca.services.ProyectoService;
import es.uca.iw.carteratiuca.views.projects.DetailsProjectView;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Valoracion Técnica")
@Route("valoracion-tecnica")
@Menu(order = 13, icon = "line-awesome/svg/mail-bulk-solid.svg")
@RolesAllowed("OTP")
public class ValorarTecnicaProyectosView  extends Composite<VerticalLayout> {
    ProyectoService proyectoService;
    JustificacionProyectoService justificacionProyectoService;

    public ValorarTecnicaProyectosView(ProyectoService proyectoService, JustificacionProyectoService justificacionProyectoService) {
    this.proyectoService = proyectoService;
    this.justificacionProyectoService = justificacionProyectoService;
        H3 h3 = new H3();
        h3.setWidth("100%");
        getStyle().set("flex-grow", "1");
        h3.setText("Proyectos que pueden ser valorados técnicamente por el OTP");
        h3.setWidth("max-content");
        getContent().add(h3);

        List<Proyecto> proyectosAvaladosSinValorarOTP = proyectoService.getProyectosAvaladosSinValorarOTP();
        Grid<Proyecto> proyectosGrid = new Grid<>();
        proyectosGrid.setItems(proyectosAvaladosSinValorarOTP);

        proyectosGrid.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);
        proyectosGrid.addColumn(Proyecto::getNombreCorto).setHeader("Nombre Corto").setSortable(true);
        proyectosGrid.addColumn(Proyecto::getEstadoValoracionOTP).setHeader("Valoración dada")
                .setSortable(true)
                .setRenderer(new ComponentRenderer<>(item ->{
                    Span span = new Span(item.getEstadoValoracionOTP().name());
                    if (item.getEstadoValoracionOTP() == EstadosAvalacionValoracion.SI)
                    {
                        span.getStyle().set("color", "green"); // Texto verde
                    } else if (item.getEstadoValoracionOTP() == EstadosAvalacionValoracion.NO) {
                        span.getStyle().set("color", "red"); // Texto rojo
                    }
                    return span;
                }
                )
                );


        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Valorar", event -> {
                UI.getCurrent().navigate(ValoTecnicaEspecificaView.class).
                        ifPresent(detalle -> detalle.editarValoracionTecnica(proyecto, proyectoService));
            });
            return botonDetalles;
        });

        getContent().add(proyectosGrid);

    }
}
