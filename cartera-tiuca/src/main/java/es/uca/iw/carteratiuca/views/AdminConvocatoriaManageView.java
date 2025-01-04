package es.uca.iw.carteratiuca.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.carteratiuca.services.ConvocatoriaService;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Todas las convocatorias")
@Route("admin-convocatoria-manage")
@Menu(order = 5, icon = "line-awesome/svg/folder-open.svg")
@RolesAllowed("ADMIN")

public class AdminConvocatoriaManageView extends Composite<VerticalLayout> {

    private final ConvocatoriaService service;

    public AdminConvocatoriaManageView(ConvocatoriaService service) {
        this.service = service;

        
    }

}
