package es.uca.iw.carteratiuca.repositories;


import es.uca.iw.carteratiuca.entities.EstadosAvalacionValoracion;
import es.uca.iw.carteratiuca.entities.EstadoProyecto;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID>, JpaSpecificationExecutor<Proyecto> {
    Proyecto findByTitulo(String titulo);

    List<Proyecto> findBySolicitante(User solicitante);

    List<Proyecto> findByEstado(EstadoProyecto estado);

    List<Proyecto> findByPromotor(User user);

    List<Proyecto> findByPromotorAndEstadoAvalacion(User promotor, EstadosAvalacionValoracion estadoAvalacion);

    List<Proyecto> findByEstadoAvalacionAndEstadoValoracionCIO(EstadosAvalacionValoracion estadoAvalacion, EstadosAvalacionValoracion estadoValoracionCIO);

    List<Proyecto> findByEstadoValoracionCIOAndEstadoValoracionOTP(EstadosAvalacionValoracion estadoValoracionCIO, EstadosAvalacionValoracion estadoValoracionOTP);
}
