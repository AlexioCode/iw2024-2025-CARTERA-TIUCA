package es.uca.iw.carteratiuca.views.cio;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@PageTitle("Priorización final de proyectos valorados")
@Route("priorizacion-proyectos-final")
@Menu(order = 1, icon = "line-awesome/svg/mail-bulk-solid.svg")
@RolesAllowed("CIO")
public class PriorizarProyectosView extends VerticalLayout {

    ProyectoService proyectoService;

    public PriorizarProyectosView(ProyectoService proyectoService) {
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

        add(proyectosGrid);
    }
}
