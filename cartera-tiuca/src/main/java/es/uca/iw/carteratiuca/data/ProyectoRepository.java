package es.uca.iw.carteratiuca.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID>, JpaSpecificationExecutor<User> {
    Proyecto findByTitulo(String titulo);
}
