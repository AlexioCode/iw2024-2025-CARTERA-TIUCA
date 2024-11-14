package es.uca.iw.carteratiuca.views.login2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Login2")
@Route("login2")
@Menu(order = 3, icon = "line-awesome/svg/user-lock-solid.svg")
@AnonymousAllowed
public class Login2View extends Composite<VerticalLayout> {

    public Login2View() {
        LoginForm loginForm = new LoginForm();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, loginForm);
        // loginForm.setWidth("min-content");
        getContent().add(loginForm);
    }
}
