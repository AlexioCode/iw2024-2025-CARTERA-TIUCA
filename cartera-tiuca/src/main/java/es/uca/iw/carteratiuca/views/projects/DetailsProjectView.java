package es.uca.iw.carteratiuca.views.projects;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
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

        JustificacionProyecto justificacion = proyecto.getJustificacion();
        getContent().add(new H3("● Justificación del proyecto"));
        getContent().add(new H4("Alcance: " + justificacion.getAlcance()));
        getContent().add(new H4("Fecha requerida de puesta en marcha: " + justificacion.getFechaPuestaEnMarcha()));
        getContent().add(new H4("Normativa de aplicación: " + justificacion.getNormativa()));

        getContent().add(new H3(" • Alineación estratégica del proyecto"));
        if (justificacion.isActualizarOferta())
            getContent().add(new H5("Innova, rediseña y actualiza nuestra oferta formativa para adaptarlas a las " +
                    "necesidades sociales y económicas de nuestro entorno."));
        if (justificacion.isAltaCalidad())
            getContent().add(new H5("Consigue los niveles más altos de calidad en nuestra oferta formativa propia " +
                    "y reglada."));
        if (justificacion.isAumentarInvestigacion())
            getContent().add(new H5("Aumenta nuestro posicionamiento en investigación y transfiere de forma relevante " +
                    "y útil nuestra investigación a nuestro tejido social y productivo."));
        if (justificacion.isConsolidarGobiernoSostenible())
            getContent().add(new H5("Consolida un modelo de gobierno sostenible y socialmente responsable."));
        if (justificacion.isConseguirTransparencia())
            getContent().add(new H5("Consigue que la transparencia sea un valor distintivo y relevante en la UCA."));
        if (justificacion.isGenerarValorCompartido())
            getContent().add(new H5("Genera Valor compartido con la Comunidad Universitaria."));
        if (justificacion.isReforzarPapelUCA())
            getContent().add(new H5("Refuerza la importancia del papel de la UCA en la socidedad."));


        Button backButton = new Button("Volver", event -> {

            UI.getCurrent().getPage().getHistory().back(); // Obtiene la vista anterior
        });

        // Añadir el botón a la vista
        getContent().add(backButton);
    }

}
