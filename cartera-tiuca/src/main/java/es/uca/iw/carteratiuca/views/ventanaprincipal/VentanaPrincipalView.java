package es.uca.iw.carteratiuca.views.ventanaprincipal;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import es.uca.iw.carteratiuca.views.login.LoginView;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/home-solid.svg")
@AnonymousAllowed
public class VentanaPrincipalView extends Composite<VerticalLayout> {

    public VentanaPrincipalView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Paragraph textLarge = new Paragraph();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h1.setText("BIENVENIDO A CARTERATIUCA");
        layoutRow.setAlignSelf(FlexComponent.Alignment.START, h1);
        h1.setWidth("max-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
        textLarge.setText("Bienvenido al Sistema de gestión de la cartera anual de proyectos TI de la UCA");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(textLarge);

        // RouterLink loginLink = new RouterLink("Login", LoginView.class);
    }
}
