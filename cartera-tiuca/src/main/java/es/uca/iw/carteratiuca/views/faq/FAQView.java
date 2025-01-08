package es.uca.iw.carteratiuca.views.faq;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("FAQ")
@Route(value = "preguntas-frecuentes")
@Menu(order = 16, icon = "line-awesome/svg/book-solid.svg")
@AnonymousAllowed
public class FAQView extends VerticalLayout {
    public FAQView() {

        String pregunta1 = "¿Qué es CarteraTIUCA?";
        String pregunta2 = "¿Quién puede usar esta plataforma?";
        String pregunta3 = "¿Cómo contacto con soporte?";
        String pregunta4 = "¿Cómo puedo agregar un proyecto?";
        Span respuesta1 = new Span("CarteraTIUCA es una plataforma diseñada para facilitar la gestión de proyectos estratégicos.");
        Span respuesta2 = new Span("La plataforma está diseñada para ser utilizada por promotores de proyectos, CIOs y otros usuarios autorizados.");
        Span respuesta3 = new Span("Puedes contactar con soporte enviando un correo a soporte@carteratiuca.es.");
        Span respuesta4 = new Span("Accede a la sección \"Enviar Solicitud\" y simplemente rellena el formulario.");

        Details details1 = new Details(pregunta1, respuesta1);
        Details details2 = new Details(pregunta2, respuesta2);
        Details details3 = new Details(pregunta3, respuesta3);
        Details details4 = new Details(pregunta4, respuesta4);

        add(details1, details2, details3, details4);

    }
}
