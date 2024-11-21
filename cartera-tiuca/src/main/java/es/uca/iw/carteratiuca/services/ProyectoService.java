package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.data.Proyecto;
import es.uca.iw.carteratiuca.data.ProyectoRepository;
import es.uca.iw.carteratiuca.data.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProyectoService {
    private final ProyectoRepository repository;
    public ProyectoService(ProyectoRepository repository) { this.repository = repository; }

}
