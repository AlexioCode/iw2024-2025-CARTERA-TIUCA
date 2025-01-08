package es.uca.iw.carteratiuca.views.otp;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.services.ProyectoService;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.component.button.Button;


@PageTitle("Valoracion Técnica")
@Route("valoracion-tecnica-proyecto")
@RolesAllowed("OTP")
public class ValoTecnicaEspecificaView extends Composite<VerticalLayout> {

    private BeanValidationBinder <Proyecto> binder;

    public ValoTecnicaEspecificaView() {}

    public void editarValoracionTecnica (Proyecto proyecto, ProyectoService proyectoService) {
        FormLayout layout = new FormLayout();
        binder = new BeanValidationBinder<>(Proyecto.class);
        TextField titulo = new TextField("Titulo");
        titulo.setReadOnly(true);
        TextField nombreCorto = new TextField("Nombre Corto");
        nombreCorto.setReadOnly(true);
        BigDecimalField coste = new BigDecimalField("Coste");
        IntegerField numEmpleados = new IntegerField("Numero de Empleados");
        BigDecimalField financiacionInteresado = new BigDecimalField("Financiacion de los Interesados");
        financiacionInteresado.setReadOnly(true);
        ComboBox<EstadosAvalacionValoracion> estadoValoracionOTP = new ComboBox<>("Estado Valoracion OTP");
        estadoValoracionOTP.setItems(EstadosAvalacionValoracion.values());
        ComboBox importanciaPromotor = new ComboBox<>("Importancia del promotor");
        importanciaPromotor.setReadOnly(true);
        importanciaPromotor.setItems(1, 2, 3, 4, 5);
        proyecto.setValoracionOTP(proyectoService.valoracionOTP(proyecto));
        IntegerField valoracionOTP = new IntegerField("Valoracion OTP (Valoracion Recomendada)");
        layout.add(titulo, nombreCorto, coste, numEmpleados, estadoValoracionOTP, importanciaPromotor, valoracionOTP);
        binder.bind(titulo, "titulo");
        binder.bind(nombreCorto, "nombreCorto");
        binder.bind(coste, "coste");
        binder.bind(numEmpleados, "numEmpleados");
        binder.bind(financiacionInteresado, "financiacionInteresado");
        binder.bind(estadoValoracionOTP, "estadoValoracionOTP");
        binder.bind(importanciaPromotor, "importanciaPromotor");
        binder.bind(valoracionOTP, "valoracionOTP");
        binder.setBean(proyecto);

        Button enviar = new Button("Enviar");
        enviar.addClickListener(e -> onPressButtonEnviar(proyectoService));
        layout.add(enviar);
        getContent().add(layout);
    }

    public void onPressButtonEnviar (ProyectoService proyectoService)
    {
        if (!binder.isValid()) {
            Notification notification = new Notification("Error, revise los campos por favor ");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }
        proyectoService.update(binder.getBean());
        Notification notification = new Notification().show("Valoracion correcta");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}
