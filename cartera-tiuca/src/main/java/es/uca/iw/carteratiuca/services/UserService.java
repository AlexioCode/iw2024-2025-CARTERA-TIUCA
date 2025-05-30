package es.uca.iw.carteratiuca.services;

import com.vaadin.flow.component.notification.Notification;
import es.uca.iw.carteratiuca.entities.Proyecto;
import es.uca.iw.carteratiuca.entities.Role;
import es.uca.iw.carteratiuca.entities.User;
import es.uca.iw.carteratiuca.repositories.UserRepository;
import es.uca.iw.carteratiuca.security.AuthenticatedUser;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProyectoService proyectoService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private AuthenticatedUser authenticatedUser;

    @Autowired
    public UserService(UserRepository repository, ProyectoService proyectoService,
                       PasswordEncoder passwordEncoder, EmailService emailService,
                       AuthenticatedUser authenticatedUser) {
        this.userRepository = repository;
        this.proyectoService = proyectoService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.authenticatedUser = authenticatedUser;
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // Ejecución como transacción (de forma atómica)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    getAuthorities(user.get()));
        }
    }

    public Optional<User> get(UUID id) {
        return userRepository.findById(id);
    }

    public User update(User user) {
        // Si la contraseña no está vacía, cifrarla antes de guardar
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        // Atributos para la auditoría de tablas
        user.setModified_by(authenticatedUser.get().get().getUsername());
        user.setModified_date(LocalDate.now());

        try {
            userRepository.save(user);
        } catch (OptimisticLockException oe) {
            Notification.show("No ha podido modificarse el usuario. Reintentando...");
            try {
                Thread.sleep(2000);  // Pausa de 2 segundos antes de intentar nuevamente
            } catch (InterruptedException ie) {}
        }
        return user;
    }

    @Transactional
    public void delete(UUID id) {
        User user = userRepository.findById(id).get();

        // Antes de eliminar a un solicitante, si tiene proyectos, eliminar sus proyectos y justificaciones
        if(user.getRoles().contains(Role.USER)) {
            List<Proyecto> listaProyectosUsuario = proyectoService.getProyectosBySolicitante(user);
            for (Proyecto proyecto : listaProyectosUsuario)
                proyectoService.delete(proyecto.getId());
        }

        // Antes de eliminar a un promotor, eliminar los proyectos de los que es promotor
        if(user.getRoles().contains(Role.PROMOTOR)) {
            List<Proyecto> proyectosDePromotor = proyectoService.getProyectosDePromotor(user);
            for(Proyecto proyecto : proyectosDePromotor)
                proyectoService.delete(proyecto.getId());
        }

        userRepository.deleteById(id);
    }

    public int count() {
        return (int) userRepository.count();
    }

    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return userRepository.findAll(filter, pageable);
    }

    @Transactional
    public boolean registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));
        user.addRole(Role.USER);

        // Atributos para auditoría de tablas
        if(authenticatedUser.get().isPresent()) {
            user.setCreated_by(authenticatedUser.get().get().getUsername());
            user.setCreated_date(LocalDate.now());
        }

        try {
            userRepository.save(user);
            emailService.sendRegistrationEmail(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean activateUser(String email, String registerCode) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getRegisterCode().equals(registerCode)) {
            user.get().setActive(true);
            userRepository.save(user.get());
            return true;

        } else {
            return false;
        }
    }

    public List<User> getActiveUsers() {
        return userRepository.findByActiveIsTrue();
    }

    public List<User> getByRole(Role role) { return userRepository.findByRoles(role);}

    public void save (User user)
    {
        userRepository.save(user);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }
}
