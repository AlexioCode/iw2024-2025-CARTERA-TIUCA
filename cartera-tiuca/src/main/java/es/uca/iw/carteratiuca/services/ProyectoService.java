package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.*;
import es.uca.iw.carteratiuca.repositories.JustificacionProyectoRepository;
import es.uca.iw.carteratiuca.repositories.ProyectoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;

    private final JustificacionProyectoRepository justificacionProyectoRepository;

    public ProyectoService(ProyectoRepository repository, JustificacionProyectoRepository justificacionProyectoRepository) {
        this.proyectoRepository = repository;
        this.justificacionProyectoRepository = justificacionProyectoRepository;
    }

    public List<Proyecto> getProyectosBySolicitante(User solicitante) {
        return proyectoRepository.findBySolicitante(solicitante);
    }

    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    public List<Proyecto> getProyectosSinArchivar() {
        List<Proyecto> proyectosNoArchivados = new ArrayList<Proyecto>();
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.ACEPTADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.DENEGADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.NOTERMINADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.REGISTRADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.POSPUESTO)));

        return proyectosNoArchivados;
    }

    public boolean registerProyecto(Proyecto proyecto) {
        try {
            proyecto.getMemoria();

            proyectoRepository.save(proyecto);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }


    public void update(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public void delete(UUID id) {
        Proyecto proyecto = proyectoRepository.findById(id).get();
        UUID justificacionId = proyecto.getJustificacion().getId();

        proyectoRepository.deleteById(id);
        justificacionProyectoRepository.deleteById(justificacionId);
    }

    public List<Proyecto> getProyectosDePromotor(User user) {
        return proyectoRepository.findByPromotor(user);
    }

    public List<Proyecto> getProyectosDePromotorPendientesDeAvalar(User user) {
        return proyectoRepository.findByPromotorAndEstadoAvalacion(user, EstadoAvalacionProyecto.PORDETERMINAR);
    }
}
