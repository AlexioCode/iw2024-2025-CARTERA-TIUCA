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
import jakarta.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Enviar Solicitud")
@Route("enviar-solicitud")
@Menu(order = 2, icon = "line-awesome/svg/mail-bulk-solid.svg")
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

        Paragraph pInfoPromotor = new Paragraph();
        pInfoPromotor.setText("Encargado de apoyar y defender la necesidad estratégica " +
                "del proyecto que se solicita");
        pInfoPromotor.getStyle().set("font-size", "var(--lumo-font-size-xs)");

        Paragraph pInfoImportancia = new Paragraph();
        pInfoImportancia.setText("En caso de que un promotor avale varias propuestas, " +
                "este campo indicará el grado de prioridad entre ellas\n");
        pInfoImportancia.getStyle().set("font-size", "var(--lumo-font-size-xs)");

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
        formLayout2Col2.add(pInfoPromotor);
        formLayout2Col2.add(pInfoImportancia);
        getContent().add(separadorInfoPromotor);

        //Seccion Interesados
        H2 h2InfoInteresados = new H2();
        FormLayout formLayout2Col3 = new FormLayout();
        TextField tfInteresados = new TextField();
        TextField tfFinanciacion = new TextField();
        Hr separadorInfoInteresados = new Hr();

        Paragraph pInfoFinanciacion = new Paragraph();
        pInfoFinanciacion.setText("Financiación que puede ser aportada por los interesados " +
                "de cara a la ejecución del proyecto");
        pInfoFinanciacion.getStyle().set("font-size", "var(--lumo-font-size-xs)");

        Paragraph salto = new Paragraph();
        salto.setText("");

        h2InfoInteresados.setText("Información de los Interesados");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2InfoInteresados);
        h2InfoInteresados.setWidth("max-content");
        formLayout2Col3.setWidth("100%");
        tfInteresados.setLabel("Interesados");
        tfInteresados.setWidth("min-content");
        tfFinanciacion.setLabel("Financiación Aportada");
        tfFinanciacion.setWidth("min-content");

        getContent().add(h2InfoInteresados);
        getContent().add(formLayout2Col3);
        formLayout2Col3.add(tfInteresados);
        formLayout2Col3.add(tfFinanciacion);
        formLayout2Col3.add(salto);
        formLayout2Col3.add(pInfoFinanciacion);
        getContent().add(separadorInfoInteresados);

        //Seccion Justificacion
        H2 h2JustifProyecto = new H2();
        CheckboxGroup chkObjetivosEstrategicos = new CheckboxGroup();
        FormLayout formLayout2Col4 = new FormLayout();
        TextField tfAlcance = new TextField();
        DateTimePicker dateTimePicker = new DateTimePicker();
        TextField tfNormativaApp = new TextField();
        Paragraph pInfoAlineamiento = new Paragraph();
        Paragraph pInfoAlcance = new Paragraph();
        Paragraph pInfoFecha = new Paragraph();
        Paragraph pInfoNormativa = new Paragraph();
        Hr separadorInfoJustificacion = new Hr();


        h2JustifProyecto.setText("Justificación del Proyecto");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2JustifProyecto);
        h2JustifProyecto.setWidth("max-content");
        chkObjetivosEstrategicos.setLabel("Alineamiento con objetivos estratégicos");
        chkObjetivosEstrategicos.setWidth("100%");
        chkObjetivosEstrategicos.setItems("Innovar, rediseñar y actualizar nuestra oferta formativa para adaptarla " +
                        "a las necesidades sociales y económicas de nuestro etorno.",
                "Conseguir los niveles más altos de calidad en nuestra oferta formativa propa y reglada.",
                "Aumentar significativamente nuestro posicionamiento en investigación y transferir de forma relevante y" +
                        " útil nuestra investigación a nuestro tejido social y productivo.",
                "Consolidar un modelo de gobierno sostenible y socialmente responsable.",
                "Conseguir que la transparencia sea un valor distintivo y relevante en la UCA.",
                "Generar valor compartido con la Comunidad Universitaria.",
                "Reforzar la importancia del papel de la UCA en la sociedad.");
        pInfoAlineamiento.setText("Su solicitud debe estar alineada con, al menos, uno de los anteriores objetivos estratégicos\n");
        pInfoAlineamiento.setWidth("100%");
        pInfoAlineamiento.getStyle().set("font-size", "var(--lumo-font-size-xs)");

        chkObjetivosEstrategicos.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        formLayout2Col4.setWidth("100%");
        tfAlcance.setLabel("Alcance");
        tfAlcance.setWidth("100%");
        pInfoAlcance.setText("Total de personas de las diferentes unidades, áreas, centros, departamentos o campus que se " +
                "beneficiarían de la implantación del proyecto");
        pInfoAlcance.setWidth("100%");
        pInfoAlcance.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        dateTimePicker.setLabel("Fecha requerida para la puesta en marcha de la solución TI");
        dateTimePicker.setWidth("100%");
        pInfoFecha.setText("Solo rellenar la fecha límite para la puesta en marcha en el caso de que su motivación sea" +
                " por obligado cumplimiento de normativa.");
        pInfoFecha.setWidth("100%");
        pInfoFecha.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        tfNormativaApp.setLabel("Normativa de la Aplicación");
        tfNormativaApp.setWidth("100%");

        getContent().add(h2JustifProyecto);
        getContent().add(chkObjetivosEstrategicos);
        getContent().add(pInfoAlineamiento);
        getContent().add(formLayout2Col4);
        formLayout2Col4.add(tfAlcance);
        formLayout2Col4.add(dateTimePicker);
        formLayout2Col4.add(pInfoAlcance);
        formLayout2Col4.add(pInfoFecha);
        getContent().add(tfNormativaApp);
        getContent().add(separadorInfoJustificacion);

        //Sección Documentación Adicional
        H2 h2DocumentacionAdicional = new H2();
        h2DocumentacionAdicional.setText("Documentación Adicional");
        h2DocumentacionAdicional.setWidth("max-content");
        getContent().add(h2DocumentacionAdicional);

        FormLayout formLayout2Col5 = new FormLayout();
        formLayout2Col5.setWidth("100%");
        formLayout2Col5.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2));

        H5 tituloSpecs = new H5();
        tituloSpecs.setText("Especificaciones Técnicas:");
        tituloSpecs.getStyle().set("font-size", "lumo-size-xs");
        formLayout2Col5.add(tituloSpecs);

        H5 tituloPresupuesto = new H5("Presupuesto(s)");
        tituloPresupuesto.getStyle().set("font-size", "lumo-size-xs");
        formLayout2Col5.add(tituloPresupuesto);

        MemoryBuffer bufferParaEspecificacionTecnica = new MemoryBuffer();
        Upload uploadEspecificacionTecnica = new Upload(bufferParaEspecificacionTecnica);
        uploadEspecificacionTecnica.setWidth("max-content");
        formLayout2Col5.add(uploadEspecificacionTecnica);

        MemoryBuffer bufferParaPresupuesto = new MemoryBuffer();
        Upload uploadPresupuesto = new Upload(bufferParaPresupuesto);
        uploadPresupuesto.setWidth("max-content");
        formLayout2Col5.add(uploadPresupuesto);

        Paragraph pInfoSpecs = new Paragraph();
        pInfoSpecs.setText("Documentos con las especificaciones tecnológicas");
        pInfoSpecs.getStyle().set("font-size", "lumo-size-sx");
        formLayout2Col5.add(pInfoSpecs);

        Paragraph pInfoPresupuesto = new Paragraph();
        pInfoPresupuesto.setText("Presupuesto(s) de la(s) solución(es) aportada(s)");
        pInfoPresupuesto.getStyle().set("font-size", "lumo-size-xs");
        formLayout2Col5.add(pInfoPresupuesto);

        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2DocumentacionAdicional);

        getContent().add(formLayout2Col5);


        //Sección Enviar
        H2 h2Enviar = new H2();
        h2Enviar.setText("");
        getContent().add(h2Enviar);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2Enviar);
        Button botonEnviar = new Button("Enviar");
        getContent().add(botonEnviar);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, botonEnviar);

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