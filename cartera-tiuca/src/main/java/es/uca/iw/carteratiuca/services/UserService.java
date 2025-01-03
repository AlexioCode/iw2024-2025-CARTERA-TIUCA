package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final ProyectoService proyectoService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository repository, ProyectoService proyectoService,
                       PasswordEncoder passwordEncoder, EmailService emailService) {
        this.repository = repository;
        this.proyectoService = proyectoService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional // Ejecución como transacción (de forma atómica)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    getAuthorities(user.get()));
        }
    }

    public Optional<User> get(UUID id) {
        return repository.findById(id);
    }

    public User update(User user) {
        // Si la contraseña no está vacía, cifrarla antes de guardar
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return repository.save(user);
    }

    public void delete(UUID id) {
        // Antes de eliminar al usuario, si tiene proyectos, eliminar sus proyectos y justificaciones
        List<Proyecto> listaProyectosUsuario = proyectoService.getProyectosBySolicitante(get(id).get());
        if (!listaProyectosUsuario.isEmpty()) {
            for (Proyecto proyecto : listaProyectosUsuario)
                proyectoService.delete(proyecto.getId()); // Función aún por implementar
        }
        repository.deleteById(id);
    }

    public int count() {
        return (int) repository.count();
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    public boolean registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
        user.addRole(Role.USER);

        try {
            repository.save(user);
            emailService.sendRegistrationEmail(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean activateUser(String email, String registerCode) {
        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent() && user.get().getRegisterCode().equals(registerCode)) {
            user.get().setActive(true);
            repository.save(user.get());
            return true;

        } else {
            return false;
        }
    }

    public List<User> getActiveUsers() {
        return repository.findByActiveIsTrue();
    }
}
