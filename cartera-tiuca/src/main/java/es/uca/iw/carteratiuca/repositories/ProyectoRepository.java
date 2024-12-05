package es.uca.iw.carteratiuca.repositories;


import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID>, JpaSpecificationExecutor<Proyecto> {
    Proyecto findByTitulo(String titulo);
    List<Proyecto> findBySolicitante(User solicitante); // or UUID solicitante;
}
