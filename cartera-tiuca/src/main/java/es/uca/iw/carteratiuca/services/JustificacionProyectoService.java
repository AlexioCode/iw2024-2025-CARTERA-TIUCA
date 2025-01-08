package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import es.uca.iw.carteratiuca.repositories.JustificacionProyectoRepository;
import org.springframework.stereotype.Service;

@Service
public class JustificacionProyectoService {
    private final JustificacionProyectoRepository justificacionProyectoRepository;

    public JustificacionProyectoService(JustificacionProyectoRepository justificacionProyectoRepository) { this.justificacionProyectoRepository = justificacionProyectoRepository; }

}
