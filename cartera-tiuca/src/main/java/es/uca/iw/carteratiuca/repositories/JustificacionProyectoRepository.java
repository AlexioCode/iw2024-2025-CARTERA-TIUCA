package es.uca.iw.carteratiuca.repositories;

import es.uca.iw.carteratiuca.entities.JustificacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JustificacionProyectoRepository extends JpaRepository<JustificacionProyecto, UUID>, JpaSpecificationExecutor<JustificacionProyecto> {

}
