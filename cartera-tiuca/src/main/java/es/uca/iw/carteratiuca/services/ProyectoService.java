package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Proyecto> getProyectosSinArchivar() {
        List<Proyecto> proyectosNoArchivados = new ArrayList<Proyecto>();
        proyectosNoArchivados.addAll((repository.findByEstado(EstadoProyecto.ACEPTADO)));
        proyectosNoArchivados.addAll((repository.findByEstado(EstadoProyecto.DENEGADO)));
        proyectosNoArchivados.addAll((repository.findByEstado(EstadoProyecto.NOTERMINADO)));
        proyectosNoArchivados.addAll((repository.findByEstado(EstadoProyecto.REGISTRADO)));
        proyectosNoArchivados.addAll((repository.findByEstado(EstadoProyecto.POSPUESTO)));

        return proyectosNoArchivados;
    }
}
