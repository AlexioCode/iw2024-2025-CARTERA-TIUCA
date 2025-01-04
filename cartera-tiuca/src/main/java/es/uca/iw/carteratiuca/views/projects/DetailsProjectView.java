package es.uca.iw.carteratiuca.views.projects;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;


@PageTitle("Mis proyectos")
@Route("proyecto-detalle")
@PermitAll
public class DetailsProjectView extends Composite<VerticalLayout> {

    private ProyectoService service;

    public DetailsProjectView(ProyectoService service) {

    }

    public void editProject(Proyecto proyecto) {
        getContent().add(new H1(proyecto.getTitulo()));
        getContent().add(new H3("Nombre corto: " + proyecto.getNombreCorto()));
        getContent().add(new H3("Coste: " + proyecto.getCoste()));
        getContent().add(new H3("Estado: " + proyecto.getEstado()));
        getContent().add(new H3("Grado de avance: " + proyecto.getGradoAvance()));
        getContent().add(new H3("Avalado: " + proyecto.getEstadoAvalacion()));
        getContent().add(new H3("Interesados: " + proyecto.getInteresados()));
        getContent().add(new H3("Número de empleados: " + proyecto.getNumEmpleados()));
        getContent().add(new H3("Importancia del promotor: " + proyecto.getImportanciaPromotor()));
        getContent().add(new H3("Financiación de los interesados: " + proyecto.getFinanciacionInteresado()));
        getContent().add(new H3("Memoria del proyecto: "));
        getContent().add(new H3("Especificaciones Técnicas: "));
        getContent().add(new H3("Presupuesto: "));


        Button backButton = new Button("Volver", event -> {

            UI.getCurrent().getPage().getHistory().back(); // Obtiene la vista anterior
        });

        // Añadir el botón a la vista
        getContent().add(backButton);
    }

}
