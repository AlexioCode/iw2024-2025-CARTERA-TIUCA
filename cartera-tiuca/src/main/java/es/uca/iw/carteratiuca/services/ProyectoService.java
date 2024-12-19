package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {
    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> getProyectosBySolicitante(User solicitante) {
        return repository.findBySolicitante(solicitante);
    }

    public List<Proyecto> getProyectos() {
        return repository.findAll();
    }
}
