package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Convocatoria;
import es.uca.iw.carteratiuca.repositories.ConvocatoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvocatoriaService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public ConvocatoriaService(ConvocatoriaRepository repository) {
        this.convocatoriaRepository = repository;
    }

    public List<Convocatoria> getConvocatorias() {
        return convocatoriaRepository.findAll();
    }

    public Convocatoria create(Convocatoria convocatoria) {
        
    }

}
