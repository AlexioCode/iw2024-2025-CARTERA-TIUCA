package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import com.vaadin.flow.component.details.Details;

@PageTitle("Mis proyectos")
@Route(value = "userProyects")
@PermitAll
public class UserProyectsView extends Composite<VerticalLayout> {

    private ProyectoService service;

    private String titulo;
    private String nombreCorto;
    private BigDecimal coste;
    private EstadoProyecto estado;
    private int gradoAvance = 0;

    public UserProyectsView(AuthenticatedUser user) {

        H3 h3 = new H3();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Mis proyectos");
        h3.setWidth("max-content");
        getContent().add(h3);
        Details details = new Details();


         //obtener la lista de proyectos de un usuario
        List<Proyecto> misProyectos = service.getProyectosBySolicitante(user.get().get());

        for(Proyecto proyecto : misProyectos) {
            titulo = proyecto.getTitulo();
            nombreCorto = proyecto.getNombreCorto();
            coste = proyecto.getCoste();
            estado = proyecto.getEstado();
            gradoAvance = proyecto.getGradoAvance();

            //llevará a otra vista donde se mostrarán los otros atributos de un proyectos
            Button buttonPrimary2 = new Button("Más Detalles");

            details.setSummaryText(titulo);


        }
    }
}
