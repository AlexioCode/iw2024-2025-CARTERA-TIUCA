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


@PageTitle("Editar convocatoria")
@Route("editar-convocatoria")
@RolesAllowed("ADMIN")
public class ConvocatoriaDataView extends VerticalLayout {

    private final ConvocatoriaService service;
    private final H1 title;
    private final TextField nombre;
    private final DatePicker fecha_inicial;
    private final DatePicker fecha_final;
    private final Button submitButton;
    private final BeanValidationBinder<Convocatoria> binder;

    public ConvocatoriaDataView(ConvocatoriaService service) {
        this.service = service;

        title = new H1("Datos de una convocatoria");

        nombre = new TextField("Nuevo nombre:");
        nombre.setId("nombre");

        fecha_inicial = new DatePicker("Nueva fecha de inicio:");
        fecha_inicial.setId("fechaInicial");

        fecha_final = new DatePicker("Nueva fecha final:");
        fecha_final.setId("fechaFinal");

        add(nombre);
        add(fecha_inicial);
        add(fecha_final);

        submitButton = new Button("Guardar cambios");
        submitButton.setId("submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        binder = new BeanValidationBinder<>(Convocatoria.class);
        binder.bind(nombre, "nombre");
        binder.bind(fecha_inicial, "fecha_inicial");
        binder.bind(fecha_final, "fecha_final");

        add(title, nombre, fecha_inicial, fecha_final);

    }

    // Metodo para cuando un admin modifica los datos de una convocatoria
    public void adminConvocatoriaDataView(Convocatoria convocatoria) {
        binder.setBean(convocatoria);
        submitButton.addClickListener(e -> onSubmitBotonClick(convocatoria));
        add(submitButton);

        Button backButton = new Button("Volver", event -> {

            UI.getCurrent().getPage().getHistory().back(); // Obtiene la vista anterior
        });

        // Añadir el botón a la vista
        add(backButton);

    }

    private void onSubmitBotonClick(Convocatoria convocatoria) {
        if (binder.validate().isOk() && fecha_final.getValue() != null && fecha_inicial.getValue() != null
                && fecha_final.getValue().isAfter(fecha_inicial.getValue())) {
            service.update(convocatoria);
            Notification.show("Datos actualizados con éxito.");
        } else
            Notification.show("Error al actualizar.");
    }

}
