package es.uca.iw.carteratiuca.views.convocatoria;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Crear convocatoria")
@Route("crear-convocatoria")
@RolesAllowed("ADMIN")
public class CreateConvocatoriaView extends VerticalLayout {

    private final ConvocatoriaService service;
    private final H1 title;
    private final TextField nombre;
    private final DatePicker fechaInicial;
    private final DatePicker fechaFinal;
    private final Button submitButton;

    private final BeanValidationBinder<Convocatoria> binder;


    public CreateConvocatoriaView(ConvocatoriaService service) {
        this.service = service;

        title = new H1("Datos de una convocatoria:");
        nombre = new TextField("Nombre");
        nombre.setId("nombre");

        fechaInicial = new DatePicker("Fecha de inicio:");
        fechaInicial.setId("fechaInicial");

        fechaFinal = new DatePicker("Fecha fin:");
        fechaFinal.setId("fechaFinal");

        submitButton = new Button("Guardar cambios");
        submitButton.setId("Crear");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        add(title, nombre, fechaInicial, fechaFinal);

        binder = new BeanValidationBinder<>(Convocatoria.class);
        binder.bindInstanceFields(this);

        //PONER EL LISTENER CON EL ONSUBMITBUTTONCLICK QUE LLEVE A LA FUNCION QUE LLAMA A CREATE DE CONVOCATORIASERVICE

    }
}
