package es.uca.iw.carteratiuca.views.convocatoria;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Editar convocatoria")
@Route("editar-convocatoria")
@RolesAllowed("ADMIN")
public class ConvocatoriaDataView extends VerticalLayout {

    private final DatePicker fechaInicial;
    private final DatePicker fechaFinal;
    private final Button submitButton;
    private final BeanValidationBinder<User> binder;
    ConvocatoriaService service;

    public ConvocatoriaDataView(ConvocatoriaService service) {
        DatePicker fechaInicial = new DatePicker("Nueva fecha de inicio:");
        DatePicker fechaFinal = new DatePicker("Nueva fecha final:");

        add(fechaInicial);
        add(fechaFinal);

        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;

        submitButton = new Button("Guardar cambios");
        submitButton.setId("submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);


    }

    private void onSubmitBotonClick(Convocatoria convocatoria) {

    }
}
