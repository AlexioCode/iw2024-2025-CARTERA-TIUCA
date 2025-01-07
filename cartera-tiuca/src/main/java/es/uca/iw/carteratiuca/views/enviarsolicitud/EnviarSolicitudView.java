package es.uca.iw.carteratiuca.views.enviarsolicitud;

import java.io.IOException;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import es.uca.iw.carteratiuca.services.ProyectoService;
import jakarta.annotation.security.PermitAll;

import java.math.BigDecimal;
import java.util.Map;

@PageTitle("Enviar Solicitud")
@Route("enviar-solicitud")
@Menu(order = 2, icon = "line-awesome/svg/mail-bulk-solid.svg")
@PermitAll
public class EnviarSolicitudView extends Composite<VerticalLayout> {

    private final ProyectoService proyectoService;

    private final BeanValidationBinder<Proyecto> binderProyecto;
    private final BeanValidationBinder<JustificacionProyecto> binderJustificacion;

    private final H4 status;

    byte[] bytesParaMemoria;
    byte[] bytesParaEspTecnicas;
    byte[] bytesParaPresupuesto;

    public EnviarSolicitudView(ProyectoService service, AuthenticatedUser user){
        this.proyectoService = service;

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
        uploadCargarMemoria.addSucceededListener(event ->{
            try{
                bytesParaMemoria = bufferParaMemoria.getInputStream().readAllBytes();
            }
            catch (IOException e){
                System.out.println("Fallo leyendo bytes");
            }
        });
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

        //no hace falta las opciones de solicitante

        //Seccion Promotor
        H2 h2SeccionPromotor = new H2();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox cmbPromotor = new ComboBox();
        ComboBox<Integer> cmbImportanciaPromotor = new ComboBox();
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
        //setComboBoxSampleData(cmbPromotor);
        cmbImportanciaPromotor.setLabel("Importancia (0 - 5)");
        cmbImportanciaPromotor.setWidth("min-content");
        cmbImportanciaPromotor.setItems(1, 2, 3, 4, 5);

        getContent().add(h2SeccionPromotor);
        getContent().add(formLayout2Col2);
        //formLayout2Col2.add(cmbPromotor);
        formLayout2Col2.add(cmbImportanciaPromotor);
        formLayout2Col2.add(pInfoPromotor);
        formLayout2Col2.add(pInfoImportancia);
        getContent().add(separadorInfoPromotor);

        //Seccion Interesados
        H2 h2InfoInteresados = new H2();
        FormLayout formLayout2Col3 = new FormLayout();
        TextField tfInteresados = new TextField();
        BigDecimalField tfFinanciacion = new BigDecimalField();
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
        CheckboxGroup<String> chkObjetivosEstrategicos = new CheckboxGroup();
        FormLayout formLayout2Col4 = new FormLayout();
        TextField tfAlcance = new TextField();
        DatePicker datePicker = new DatePicker();
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

        Map<String, String> opciones = Map.of(
                "actualizarOferta","Innovar, rediseñar y actualizar nuestra oferta formativa para adaptarla a las necesidades sociales y económicas de nuestro etorno.",
                "altaCalidad","Conseguir los niveles más altos de calidad en nuestra oferta formativa propia y reglada.",
                "aumentarInvestigacion","Aumentar significativamente nuestro posicionamiento en investigación y transferir de forma relevante y" +
                        " útil nuestra investigación a nuestro tejido social y productivo.",
                "conseguirTransparencia","Conseguir que la transparencia sea un valor distintivo y relevante en la UCA.",
                "generarValorCompartido", "Generar valor compartido con la Comunidad Universitaria.",
                "consolidarGobiernoSostenible","Consolidar un modelo de gobierno sostenible y socialmente responsable.",
                "reforzarPapelUCA","Reforzar la importancia del papel de la UCA en la sociedad."
        );

        chkObjetivosEstrategicos.setItems(opciones.keySet());
        chkObjetivosEstrategicos.setRenderer(new TextRenderer<>(opciones::get));
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
        datePicker.setLabel("Fecha requerida para la puesta en marcha de la solución TI");
        datePicker.setWidth("100%");
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
        formLayout2Col4.add(datePicker);
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
        uploadEspecificacionTecnica.addSucceededListener(event ->{
            try{
                bytesParaEspTecnicas = bufferParaEspecificacionTecnica.getInputStream().readAllBytes();
            }
            catch (IOException e){
                System.out.println("Fallo leyendo bytes");
            }
        });
        uploadEspecificacionTecnica.setWidth("max-content");
        formLayout2Col5.add(uploadEspecificacionTecnica);

        MemoryBuffer bufferParaPresupuesto = new MemoryBuffer();
        Upload uploadPresupuesto = new Upload(bufferParaPresupuesto);
        uploadPresupuesto.addSucceededListener(event ->{
            try{
                bytesParaPresupuesto = bufferParaPresupuesto.getInputStream().readAllBytes();
            }
            catch (IOException e){
                System.out.println("Fallo leyendo bytes");
            }
        });
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


        //binder para guardar un proyecto en la BD

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        binderProyecto = new BeanValidationBinder<>(Proyecto.class);
        binderProyecto.bind(tfTituloProyecto, "titulo");
        binderProyecto.bind(tfNombreCorto, "nombreCorto");
        binderProyecto.bind(tfInteresados, "interesados");
        binderProyecto.bind(cmbImportanciaPromotor, "importanciaPromotor");
        //binderProyecto.bind(cmbPromotor, "promotor");
        binderProyecto.bind(tfFinanciacion, "financiacionInteresado");

        JustificacionProyecto nuevaJustifiacion = new JustificacionProyecto();
        binderJustificacion = new BeanValidationBinder<>(JustificacionProyecto.class);
        chkObjetivosEstrategicos.addSelectionListener(event ->{
            nuevaJustifiacion.setActualizarOferta(event.getValue().contains("actualizarOferta"));
            nuevaJustifiacion.setAltaCalidad(event.getValue().contains("altaCalidad"));
            nuevaJustifiacion.setAumentarInvestigacion(event.getValue().contains("aumentarInvestigacion"));
            nuevaJustifiacion.setConseguirTransparencia(event.getValue().contains("conseguirTransparencia"));
            nuevaJustifiacion.setGenerarValorCompartido(event.getValue().contains("generarValorCompartido"));
            nuevaJustifiacion.setConsolidarGobiernoSostenible(event.getValue().contains("consolidarGobiernoSostenible"));
            nuevaJustifiacion.setReforzarPapelUCA(event.getValue().contains("reforzarPapelUCA"));
        });
        binderJustificacion.bind(tfAlcance, "alcance");
        binderJustificacion.bind(datePicker, "fechaPuestaEnMarcha");
        binderJustificacion.bind(tfNormativaApp, "normativa");

        //Sección Enviar
        H2 h2Enviar = new H2();
        h2Enviar.setText("");
        getContent().add(h2Enviar);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2Enviar);
        Button botonEnviar = new Button("Enviar");
        botonEnviar.addClickListener(e -> onRegisterButtonClick(user, nuevaJustifiacion));
        getContent().add(botonEnviar);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, botonEnviar);

    }

    /* /*
     TODO: Cambiar las opciones dentro del combobox
     *
     private void setComboBoxSampleData(ComboBox comboBox) {
         List<SampleItem> sampleItems = new ArrayList<>();
         sampleItems.add(new SampleItem("first", "First", null));
         sampleItems.add(new SampleItem("second", "Second", null));
         sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
         sampleItems.add(new SampleItem("fourth", "Fourth", null));
         comboBox.setItems(sampleItems);
         comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
     }
         */
    public void onRegisterButtonClick(AuthenticatedUser user, JustificacionProyecto nuevaJustificacion) {
        Proyecto nuevoProyecto = new Proyecto();
        try{
            binderJustificacion.writeBean(nuevaJustificacion);
            nuevoProyecto.setJustificacion(nuevaJustificacion);
            nuevoProyecto.setSolicitante(user.get().get());
            nuevoProyecto.setCoste(new BigDecimal(0));
            nuevoProyecto.setNumEmpleados(5);
            nuevoProyecto.setEstado(EstadoProyecto.REGISTRADO);
            nuevoProyecto.setMemoria(bytesParaMemoria);
            nuevoProyecto.setEstadoAvalacion(EstadosAvalacionValoracion.NO);
            /*TODO: BORRAR DATO DE EJEMPO*/
            nuevoProyecto.setPromotor("Ejemplo");
            binderProyecto.writeBean(nuevoProyecto);
            proyectoService.registerProyecto(nuevoProyecto);
            Notification notification = new Notification().show("Proyecto registrado correctamente");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            binderProyecto.getFields().forEach(f-> f.clear());
            binderJustificacion.getFields().forEach(f-> f.clear());
        }
        catch(ValidationException e)
        {
            Notification notification = new Notification().show("Por favor, revise los datos introducidos.");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

}