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
import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.EmailService;
import es.uca.iw.carteratiuca.services.ProyectoService;
import es.uca.iw.carteratiuca.views.projects.DetailsProjectView;
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
        // Calcular valoración CIO de forma automática para cada proyecto
        for(Proyecto proyecto : proyectosAvaladosSinValorarCIO)
            proyectoService.calcularValoracionCIO(proyecto);

        Grid<Proyecto> proyectosGrid = new Grid<>();
        proyectosGrid.setItems(proyectosAvaladosSinValorarCIO);

        proyectosGrid.addColumn(Proyecto::getTitulo).setHeader("Titulo").setSortable(true);

        proyectosGrid.addColumn(Proyecto::getValoracionCIO)
                .setHeader("Valoración automática CIO")
                .setSortable(true)
                .setAutoWidth(true)
                .setKey("valoracionCIO");

        // Establecer orden predeterminado
        proyectosGrid.sort(
                GridSortOrder.asc(proyectosGrid.getColumnByKey("valoracionCIO"))
                        .build()
        );

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isActualizarOferta)
        ).setHeader("¿Actualiza la oferta formativa?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isAltaCalidad)
        ).setHeader("¿Alta calidad en oferta formativa?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isAumentarInvestigacion)
        ).setHeader("¿Aumenta investigación?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isConsolidarGobiernoSostenible)
        ).setHeader("¿Consolida gobierno sostenible?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isConseguirTransparencia)
        ).setHeader("¿Transparencia?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isGenerarValorCompartido)
        ).setHeader("¿Valor compartido con comunidad?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                evaluarFuncionBoolean(proyecto, JustificacionProyecto::isReforzarPapelUCA)
        ).setHeader("¿Refuerza papel de la UCA?").setSortable(true).setWidth("300px");

        proyectosGrid.addColumn(proyecto ->
                obtenerValorDeJustificacion(proyecto, JustificacionProyecto::getAlcance)
        ).setHeader("Alcance").setSortable(true).setAutoWidth(true);

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonApto = new Button("Apto", e -> {
                proyecto.setEstadoValoracionCIO(EstadosAvalacionValoracion.SI);
                proyecto.setGradoAvance(proyecto.getGradoAvance() + 25);
                proyectoService.update(proyecto);
                proyectosAvaladosSinValorarCIO.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoValoracionCIO(proyecto.getSolicitante(), "SI");
            });
            botonApto.setSizeFull();
            return botonApto;
        });

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonNoApto = new Button("No Apto", e -> {
                proyecto.setEstadoValoracionCIO(EstadosAvalacionValoracion.NO);
                proyecto.setEstado(EstadoProyecto.DENEGADO);
                proyecto.setGradoAvance(0);
                proyectoService.update(proyecto);
                proyectosAvaladosSinValorarCIO.remove(proyecto);
                proyectosGrid.getDataProvider().refreshAll();
                emailService.enviarCorreoValoracionCIO(proyecto.getSolicitante(), "NO");
            });
            botonNoApto.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            botonNoApto.setSizeFull();
            return botonNoApto;
        });

        proyectosGrid.addComponentColumn(proyecto -> {
            Button botonDetalles = new Button("Detalles", event -> {
                UI.getCurrent().navigate(DetailsProjectView.class).
                        ifPresent(detalle -> detalle.editProject(proyecto));
            });
            return botonDetalles;
        });

        add(proyectosGrid);
    }

    // Función para mostrar Sí o No, dependiendo del valor
    private String evaluarFuncionBoolean(Proyecto proyecto, java.util.function.Predicate<JustificacionProyecto> funcion) {
        JustificacionProyecto justificacion = proyecto.getJustificacion();
        return justificacion != null && funcion.test(justificacion) ? "Sí" : "No";
    }

    private String obtenerValorDeJustificacion(Proyecto proyecto, java.util.function.Function<JustificacionProyecto, String> funcion) {
        JustificacionProyecto justificacion = proyecto.getJustificacion();
        return justificacion != null ? funcion.apply(justificacion) : "No disponible";
    }
}
