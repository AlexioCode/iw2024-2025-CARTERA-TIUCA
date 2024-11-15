package es.uca.iw.carteratiuca.views.enviarsolicitud;

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
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Enviar Solicitud")
@Route("enviar-solicitud")
@Menu(order = 1, icon = "line-awesome/svg/mail-bulk-solid.svg")
@PermitAll
public class EnviarSolicitudView extends Composite<VerticalLayout> {

    public EnviarSolicitudView() {
        H1 tituloPrincipalPagina = new H1();
        FormLayout formLayout2Col = new FormLayout();
        TextField textFieldTituloProyecto = new TextField();
        TextField textFieldNombreCorto = new TextField();
        MemoryBuffer bufferParaMemoria = new MemoryBuffer();
        Upload uploadCargarMemoria = new Upload(bufferParaMemoria);
        Hr separadorPrincipal = new Hr();
        H2 tituloSeccionSolicitante = new H2();
        FormLayout formLayout3Col = new FormLayout();
        TextField textFieldNombreSolicitante = new TextField();
        EmailField emailFieldEmailSolicitante = new EmailField();
        TextField textFieldUnidadSolicitante = new TextField();
        Paragraph paragraphInfoSolicitante = new Paragraph();
        Hr separadorInfoSolicitante = new Hr();
        H2 tituloSeccionPromotor = new H2();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox cmbPromotor = new ComboBox();
        ComboBox cmbImportanciaPromotor = new ComboBox();
        Hr hr5 = new Hr();
        H2 h23 = new H2();
        FormLayout formLayout2Col3 = new FormLayout();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        H2 h24 = new H2();
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        FormLayout formLayout2Col4 = new FormLayout();
        TextField textField7 = new TextField();
        DateTimePicker dateTimePicker = new DateTimePicker();
        TextField textField8 = new TextField();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        tituloPrincipalPagina.setText("Formulario de solicitud");
        tituloPrincipalPagina.setWidth("max-content");
        formLayout2Col.setWidth("100%");
        textFieldTituloProyecto.setLabel("Título del Proyecto");
        textFieldTituloProyecto.setWidth("min-content");
        textFieldNombreCorto.setLabel("Nombre Corto");
        textFieldNombreCorto.setWidth("min-content");
        uploadCargarMemoria.setWidth("min-content");
        tituloSeccionSolicitante.setText("Información del Solicitante");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tituloSeccionSolicitante);
        tituloSeccionSolicitante.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textFieldNombreSolicitante.setLabel("Nombre Completo");
        textFieldNombreSolicitante.setWidth("min-content");
        emailFieldEmailSolicitante.setLabel("Email");
        emailFieldEmailSolicitante.setWidth("min-content");
        textFieldUnidadSolicitante.setLabel("Unidad");
        textFieldUnidadSolicitante.setWidth("min-content");
        paragraphInfoSolicitante.setText("Tendrá la condición de solicitante el responsable de cualquier área, unidad o centro");
        paragraphInfoSolicitante.setWidth("100%");
        paragraphInfoSolicitante.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        tituloSeccionPromotor.setText("Información del Promotor");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tituloSeccionPromotor);
        tituloSeccionPromotor.setWidth("max-content");
        formLayout2Col2.setWidth("100%");
        cmbPromotor.setLabel("Promotor");
        cmbPromotor.setWidth("min-content");
        setComboBoxSampleData(cmbPromotor);
        cmbImportanciaPromotor.setLabel("Importancia (0 - 5)");
        cmbImportanciaPromotor.setWidth("min-content");
        setComboBoxSampleData(cmbImportanciaPromotor);
        h23.setText("Información de los Interesados");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h23);
        h23.setWidth("max-content");
        formLayout2Col3.setWidth("100%");
        textField5.setLabel("Interesados");
        textField5.setWidth("min-content");
        textField6.setLabel("Finanziación Aportada");
        textField6.setWidth("min-content");
        h24.setText("Justificación del Proyecto");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h24);
        h24.setWidth("max-content");
        checkboxGroup.setLabel("Alineamiento con objetivos estratégicos");
        checkboxGroup.setWidth("100%");
        checkboxGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        formLayout2Col4.setWidth("100%");
        textField7.setLabel("Alcance");
        textField7.setWidth("100%");
        dateTimePicker.setLabel("Fecha requerida para la puesta en marcha de la solución TI");
        dateTimePicker.setWidth("100%");
        textField8.setLabel("Normativa de la Aplicación");
        textField8.setWidth("100%");
        getContent().add(tituloPrincipalPagina);
        getContent().add(formLayout2Col);
        formLayout2Col.add(textFieldTituloProyecto);
        formLayout2Col.add(textFieldNombreCorto);
        formLayout2Col.add(uploadCargarMemoria);
        getContent().add(separadorPrincipal);
        getContent().add(tituloSeccionSolicitante);
        getContent().add(formLayout3Col);
        formLayout3Col.add(textFieldNombreSolicitante);
        formLayout3Col.add(emailFieldEmailSolicitante);
        formLayout3Col.add(textFieldUnidadSolicitante);
        formLayout3Col.add(paragraphInfoSolicitante);
        getContent().add(separadorInfoSolicitante);
        getContent().add(tituloSeccionPromotor);
        getContent().add(formLayout2Col2);
        formLayout2Col2.add(cmbPromotor);
        formLayout2Col2.add(cmbImportanciaPromotor);
        getContent().add(hr5);
        getContent().add(h23);
        getContent().add(formLayout2Col3);
        formLayout2Col3.add(textField5);
        formLayout2Col3.add(textField6);
        getContent().add(h24);
        getContent().add(checkboxGroup);
        getContent().add(formLayout2Col4);
        formLayout2Col4.add(textField7);
        formLayout2Col4.add(dateTimePicker);
        getContent().add(textField8);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }


    /*
    TODO: Cambiar las opciones dentro del combobox
    * */
    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
