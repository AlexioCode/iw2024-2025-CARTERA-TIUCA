package es.uca.iw.carteratiuca.repositories;

import es.uca.iw.carteratiuca.entities.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, UUID>, JpaSpecificationExecutor<Convocatoria> {
}
