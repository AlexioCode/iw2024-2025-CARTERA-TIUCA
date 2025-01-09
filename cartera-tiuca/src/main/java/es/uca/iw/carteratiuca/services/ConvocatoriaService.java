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
        List<Convocatoria> convocatoriasActivas = convocatoriaRepository.findAll();
        for (Convocatoria convocatoria : convocatoriasActivas) {
            if (!convocatoria.isActivo())
                convocatoriasActivas.remove(convocatoria);
        }
        return convocatoriasActivas;
    }

    public void create(Convocatoria convocatoria) {
        convocatoriaRepository.save(convocatoria);
    }

    public Convocatoria update(Convocatoria convocatoria) {
        return convocatoriaRepository.save(convocatoria);
    }

    public void delete(Convocatoria convocatoria) {

        //convocatoriaRepository.delete(convocatoria);
        convocatoria.setActivo(false);
        convocatoriaRepository.save(convocatoria);
    }

    @Cacheable("convocatoriaActual")
    public List<Convocatoria> getConvocatoriaActual() {
        LocalDate fechaActual = LocalDate.now();
        List<Convocatoria> convocatoriasActivas = convocatoriaRepository.findActiveConvocatorias(fechaActual);
        for (Convocatoria convocatoria : convocatoriasActivas) {
            if (!convocatoria.isActivo())
                convocatoriasActivas.remove(convocatoria);
        }
        return convocatoriasActivas;
    }
}
