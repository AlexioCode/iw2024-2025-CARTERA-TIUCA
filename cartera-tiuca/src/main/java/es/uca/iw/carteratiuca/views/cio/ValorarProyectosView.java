package es.uca.iw.carteratiuca.views.cio;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.EmailService;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Valorar estratégicamente proyectos avalados")
@Route("valorar-estrategicamente-proyectos-avalados")
@Menu(order = 8, icon = "line-awesome/svg/mail-bulk-solid.svg")
@RolesAllowed("CIO")
public class ValorarProyectosView extends VerticalLayout {

    ProyectoService proyectoService;
    EmailService emailService;

    public ValorarProyectosView(ProyectoService proyectoService, EmailService emailService) {
        this.proyectoService = proyectoService;
        this.emailService = emailService;

        H3 h3 = new H3();
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        h3.setText("Proyectos que pueden ser valorados estratégicamente por el CIO");
        h3.setWidth("max-content");
        add(h3);

        List<Proyecto> proyectosAvaladosSinValorarCIO = proyectoService.getProyectosAvaladosSinValorarCIO();
        Grid<Proyecto> proyectosGrid = new Grid<>();
        proyectosGrid.setItems(proyectosAvaladosSinValorarCIO);

        proyectosGrid.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonApto = new Button("Apto", e -> {
                proyecto.setEstadoValoracionCIO(EstadosAvalacionValoracion.SI);
                proyecto.setGradoAvance(proyecto.getGradoAvance() + 25);
                proyectoService.update(proyecto);
                proyectosAvaladosSinValorarCIO.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoValoracionCIO(proyecto.getSolicitante(), "SI");
            });
            return botonApto;
        });

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonNoApto = new Button("No Apto", e -> {
                proyecto.setEstadoValoracionCIO(EstadosAvalacionValoracion.NO);
                proyecto.setGradoAvance(0);
                proyectoService.update(proyecto);
                proyectosAvaladosSinValorarCIO.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoValoracionCIO(proyecto.getSolicitante(), "NO");
            });
            botonNoApto.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            return botonNoApto;
        });

        add(proyectosGrid);
    }
}
