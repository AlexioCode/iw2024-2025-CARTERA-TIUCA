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
        H1 h1 = new H1();
        Icon icon = new Icon();
        Hr hr = new Hr();
        Hr hr2 = new Hr();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        Button buttonPrimary = new Button();
        Hr hr3 = new Hr();
        H2 h2 = new H2();
        FormLayout formLayout3Col = new FormLayout();
        TextField textField3 = new TextField();
        EmailField emailField = new EmailField();
        TextField textField4 = new TextField();
        Paragraph textSmall = new Paragraph();
        Hr hr4 = new Hr();
        H2 h22 = new H2();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
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
        h1.setText("Formulario de solicitud");
        h1.setWidth("max-content");
        icon.setIcon("lumo:user");
        formLayout2Col.setWidth("100%");
        textField.setLabel("Título del Proyecto");
        textField.setWidth("min-content");
        textField2.setLabel("Nombre Corto");
        textField2.setWidth("min-content");
        buttonPrimary.setText("Cargar Memoria");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        h2.setText("Información del Solicitante");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        textField3.setLabel("Nombre Completo");
        textField3.setWidth("min-content");
        emailField.setLabel("Email");
        emailField.setWidth("min-content");
        textField4.setLabel("Unidad");
        textField4.setWidth("min-content");
        textSmall.setText("Tendrá la condición de solicitante el responsable de cualquier área, unidad o centro");
        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        h22.setText("Información del Promotor");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h22);
        h22.setWidth("max-content");
        formLayout2Col2.setWidth("100%");
        comboBox.setLabel("Promotor");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setLabel("Importancia (0 - 5)");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
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
        getContent().add(h1);
        getContent().add(icon);
        getContent().add(hr);
        getContent().add(hr2);
        getContent().add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(buttonPrimary);
        getContent().add(hr3);
        getContent().add(h2);
        getContent().add(formLayout3Col);
        formLayout3Col.add(textField3);
        formLayout3Col.add(emailField);
        formLayout3Col.add(textField4);
        formLayout3Col.add(textSmall);
        getContent().add(hr4);
        getContent().add(h22);
        getContent().add(formLayout2Col2);
        formLayout2Col2.add(comboBox);
        formLayout2Col2.add(comboBox2);
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
