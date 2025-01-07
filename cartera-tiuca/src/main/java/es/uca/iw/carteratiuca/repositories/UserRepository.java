package es.uca.iw.carteratiuca.repositories;


import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findByActiveIsTrue();

    List<User> findByRoles(Role role);
}
