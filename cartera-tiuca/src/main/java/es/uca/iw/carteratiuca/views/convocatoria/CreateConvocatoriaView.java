package es.uca.iw.carteratiuca.views.convocatoria;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
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
    private final DatePicker fecha_inicial;
    private final DatePicker fecha_final;
    private final Button submitButton;

    private final BeanValidationBinder<Convocatoria> binder;


    public CreateConvocatoriaView(ConvocatoriaService service) {
        this.service = service;

        title = new H1("Datos de una convocatoria:");
        nombre = new TextField("Nombre");
        nombre.setId("nombre");

        fecha_inicial = new DatePicker("Fecha de inicio:");
        fecha_inicial.setId("fechaInicial");

        fecha_final = new DatePicker("Fecha fin:");
        fecha_final.setId("fechaFinal");

        submitButton = new Button("Guardar cambios");
        submitButton.setId("Crear");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        add(title, nombre, fecha_inicial, fecha_final);

        binder = new BeanValidationBinder<>(Convocatoria.class);
        binder.bind(nombre, "nombre");
        binder.bind(fecha_inicial, "fecha_inicial");
        binder.bind(fecha_final, "fecha_final");

        submitButton.addClickListener(e -> onBotonCrearClick());
        add(submitButton);

        Button backButton = new Button("Volver", event -> {

            UI.getCurrent().getPage().getHistory().back(); // Obtiene la vista anterior
        });

        // Añadir el botón a la vista
        add(backButton);
    }

    private void onBotonCrearClick() {
        if (binder.validate().isOk()) {
            Convocatoria newConvocatoria = new Convocatoria();
            newConvocatoria.setNombre(nombre.getValue());
            newConvocatoria.setFecha_inicial(fecha_inicial.getValue());
            newConvocatoria.setFecha_final(fecha_final.getValue());
            service.create(newConvocatoria);
            Notification.show("Convocatoria creada con éxito.");
        } else {
            Notification.show("Por favor, revise los datos introducidos.");
        }
    }
}
