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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Ventana Principal")
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
        h1.setText("HEADER DE LA VENTANA PRINCIPAL");
        layoutRow.setAlignSelf(FlexComponent.Alignment.START, h1);
        h1.setWidth("max-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
        textLarge.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum vulputate, diam at suscipit rutrum, ex nulla ultricies enim, dapibus auctor ligula arcu ut leo. Vestibulum efficitur porttitor ex, vitae malesuada diam tincidunt viverra. Morbi ut viverra est, quis luctus orci. Nam molestie neque vitae erat tincidunt, ut dignissim augue imperdiet. Vestibulum non felis ullamcorper, dictum nisi nec, venenatis dui. Pellentesque mi metus, condimentum sed mi eu, tempor sagittis lacus. Nulla eget risus egestas urna condimentum dapibus. Duis tincidunt massa id nulla feugiat, vel lacinia massa laoreet. Suspendisse ac pharetra turpis, id ornare ex.Donec auctor ornare nisl eleifend vulputate. Proin blandit magna et semper euismod. Cras pretium placerat sodales. Integer id velit diam. Duis condimentum ultrices enim, sed pellentesque mi. Phasellus malesuada laoreet neque, in sodales metus tincidunt sit amet. Nulla vel neque quam. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi sed metus convallis, consequat tortor non, blandit ligula. Donec interdum metus ac nisl rhoncus aliquet.Nunc auctor in enim ac congue. Nullam mauris lacus, iaculis eu nunc elementum, pulvinar efficitur ante. Suspendisse egestas finibus semper. Suspendisse sit amet eros ut metus consectetur aliquet at et quam. Quisque vehicula laoreet vestibulum. Donec non libero non lorem scelerisque ultrices at eget felis. Aenean vitae consectetur odio. Etiam scelerisque ut mi egestas consequat. Maecenas fringilla magna eget sodales ornare. Etiam sit amet neque massa. Nullam at diam ut ipsum facilisis aliquet. Nulla a magna nisi.Morbi tempor, metus a vestibulum ultrices, nisi diam dapibus mi, ac fermentum turpis quam sed odio. Aliquam erat volutpat. Integer at lacinia diam. Curabitur non vehicula augue. Suspendisse vitae porta nunc. Quisque tempus vehicula odio. Praesent scelerisque erat vel tristique commodo. Aliquam erat volutpat.Quisque non tellus metus. Nunc mollis justo id dui fermentum, nec pellentesque nulla dictum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Quisque eget condimentum ante. Aliquam quis molestie risus. Quisque ac libero maximus, cursus arcu sit amet, facilisis metus. Vivamus ultrices scelerisque odio, eget porta lectus faucibus ac. Ut molestie sit amet ante ut blandit. Nulla rhoncus posuere porttitor.");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(textLarge);
    }
}
