package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.repositories.ConvocatoriaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConvocatoriaService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public ConvocatoriaService(ConvocatoriaRepository repository) {
        this.convocatoriaRepository = repository;
    }

    @Cacheable("convocatorias")
    public List<Convocatoria> getConvocatorias() {
        return convocatoriaRepository.findAll();
    }

    public void create(Convocatoria convocatoria) {
        convocatoriaRepository.save(convocatoria);
    }

    public Convocatoria update(Convocatoria convocatoria) {
        return convocatoriaRepository.save(convocatoria);
    }

    public void delete(Convocatoria convocatoria) {
        convocatoriaRepository.delete(convocatoria);
    }

    @Cacheable("convocatoriaActual")
    public List<Convocatoria> getConvocatoriaActual() {
        LocalDate fechaActual = LocalDate.now();
        return convocatoriaRepository.findActiveConvocatorias(fechaActual);
    }
}
