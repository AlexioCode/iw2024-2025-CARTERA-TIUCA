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
        //Comun a la vista
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        //Seccion Principal
        H1 h1PrincipalPagina = new H1();
        FormLayout formLayout2Col = new FormLayout();
        TextField tfTituloProyecto = new TextField();
        TextField tfNombreCorto = new TextField();
        MemoryBuffer bufferParaMemoria = new MemoryBuffer();
        Upload uploadCargarMemoria = new Upload(bufferParaMemoria);
        Hr separadorPrincipal = new Hr();

        h1PrincipalPagina.setText("Formulario de solicitud");
        h1PrincipalPagina.setWidth("max-content");
        formLayout2Col.setWidth("100%");
        tfTituloProyecto.setLabel("Título del Proyecto");
        tfTituloProyecto.setWidth("min-content");
        tfTituloProyecto.setRequired(true);
        tfTituloProyecto.setErrorMessage("Campo requerido");
        tfNombreCorto.setLabel("Nombre Corto");
        tfNombreCorto.setWidth("min-content");
        uploadCargarMemoria.setWidth("min-content");

        getContent().add(h1PrincipalPagina);
        getContent().add(formLayout2Col);
        formLayout2Col.add(tfTituloProyecto);
        formLayout2Col.add(tfNombreCorto);
        formLayout2Col.add(uploadCargarMemoria);
        getContent().add(separadorPrincipal);

        //Seccion Solicitante
        H2 h2SeccionSolicitante = new H2();
        FormLayout formLayout3Col = new FormLayout();
        TextField tfNombreSolicitante = new TextField();
        EmailField emailFieldSolicitante = new EmailField();
        TextField tfUnidadSolicitante = new TextField();
        Paragraph pInfoSolicitante = new Paragraph();
        Hr separadorInfoSolicitante = new Hr();

        h2SeccionSolicitante.setText("Información del Solicitante");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2SeccionSolicitante);
        h2SeccionSolicitante.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        tfNombreSolicitante.setLabel("Nombre Completo");
        tfNombreSolicitante.setWidth("min-content");
        emailFieldSolicitante.setLabel("Email");
        emailFieldSolicitante.setWidth("min-content");
        tfUnidadSolicitante.setLabel("Unidad");
        tfUnidadSolicitante.setWidth("min-content");
        pInfoSolicitante.setText("Tendrá la condición de solicitante el responsable de cualquier área, unidad o centro");
        pInfoSolicitante.setWidth("100%");
        pInfoSolicitante.getStyle().set("font-size", "var(--lumo-font-size-xs)");

        getContent().add(h2SeccionSolicitante);
        getContent().add(formLayout3Col);
        formLayout3Col.add(tfNombreSolicitante);
        formLayout3Col.add(emailFieldSolicitante);
        formLayout3Col.add(tfUnidadSolicitante);
        formLayout3Col.add(pInfoSolicitante);
        getContent().add(separadorInfoSolicitante);

        //Seccion Promotor
        H2 h2SeccionPromotor = new H2();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox cmbPromotor = new ComboBox();
        ComboBox cmbImportanciaPromotor = new ComboBox();
        Hr separadorInfoPromotor = new Hr();

        h2SeccionPromotor.setText("Información del Promotor");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2SeccionPromotor);
        h2SeccionPromotor.setWidth("max-content");
        formLayout2Col2.setWidth("100%");
        cmbPromotor.setLabel("Promotor");
        cmbPromotor.setWidth("min-content");
        setComboBoxSampleData(cmbPromotor);
        cmbImportanciaPromotor.setLabel("Importancia (0 - 5)");
        cmbImportanciaPromotor.setWidth("min-content");
        setComboBoxSampleData(cmbImportanciaPromotor);

        getContent().add(h2SeccionPromotor);
        getContent().add(formLayout2Col2);
        formLayout2Col2.add(cmbPromotor);
        formLayout2Col2.add(cmbImportanciaPromotor);
        getContent().add(separadorInfoPromotor);

        //Seccion Interesados
        H2 h2InfoInteresados = new H2();
        FormLayout formLayout2Col3 = new FormLayout();
        TextField tfInteresados = new TextField();
        TextField tfFinanziacion = new TextField();

        h2InfoInteresados.setText("Información de los Interesados");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2InfoInteresados);
        h2InfoInteresados.setWidth("max-content");
        formLayout2Col3.setWidth("100%");
        tfInteresados.setLabel("Interesados");
        tfInteresados.setWidth("min-content");
        tfFinanziacion.setLabel("Finanziación Aportada");
        tfFinanziacion.setWidth("min-content");

        getContent().add(h2InfoInteresados);
        getContent().add(formLayout2Col3);
        formLayout2Col3.add(tfInteresados);
        formLayout2Col3.add(tfFinanziacion);

        //Seccion Justificacion
        H2 h2JustifProyecto = new H2();
        CheckboxGroup chkObjetivosEstrategicos = new CheckboxGroup();
        FormLayout formLayout2Col4 = new FormLayout();
        TextField tfAlcance = new TextField();
        DateTimePicker dateTimePicker = new DateTimePicker();
        TextField tfNormativaApp = new TextField();

        h2JustifProyecto.setText("Justificación del Proyecto");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2JustifProyecto);
        h2JustifProyecto.setWidth("max-content");
        chkObjetivosEstrategicos.setLabel("Alineamiento con objetivos estratégicos");
        chkObjetivosEstrategicos.setWidth("100%");
        chkObjetivosEstrategicos.setItems("Order ID", "Product Name", "Customer", "Status");
        chkObjetivosEstrategicos.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        formLayout2Col4.setWidth("100%");
        tfAlcance.setLabel("Alcance");
        tfAlcance.setWidth("100%");
        dateTimePicker.setLabel("Fecha requerida para la puesta en marcha de la solución TI");
        dateTimePicker.setWidth("100%");
        tfNormativaApp.setLabel("Normativa de la Aplicación");
        tfNormativaApp.setWidth("100%");

        getContent().add(h2JustifProyecto);
        getContent().add(chkObjetivosEstrategicos);
        getContent().add(formLayout2Col4);
        formLayout2Col4.add(tfAlcance);
        formLayout2Col4.add(dateTimePicker);
        getContent().add(tfNormativaApp);
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
