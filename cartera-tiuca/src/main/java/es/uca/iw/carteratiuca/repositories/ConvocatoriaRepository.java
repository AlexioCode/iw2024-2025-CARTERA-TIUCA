package es.uca.iw.carteratiuca.repositories;

import es.uca.iw.carteratiuca.entities.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, UUID>, JpaSpecificationExecutor<Convocatoria> {
    @Query("SELECT c FROM Convocatoria c WHERE c.fecha_inicial <= :fechaActual AND c.fecha_final >= :fechaActual")
    List<Convocatoria> findActiveConvocatorias(LocalDate fechaActual);

}
