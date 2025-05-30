package es.uca.iw.carteratiuca.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_users")
public class User extends AbstractEntity implements UserDetails {
    @NotEmpty
    private String username;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String unit;

    private String cargo;

    @JsonIgnore
    @NotEmpty
    @Size(min = 8)
    private String password;

    private String registerCode = "";

    private boolean active = false;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;

    // Auditoría de tablas
    private String created_by;
    private String modified_by;
    private LocalDate created_date;
    private LocalDate modified_date;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getCargo() {return cargo;}
    public void setCargo(String cargo) {this.cargo = cargo;}
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public void addRole(Role role) { this.roles.add(role); }
    public byte[] getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getRegisterCode() { return registerCode; }
    public void setRegisterCode(String registerCode) { this.registerCode = registerCode; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<GrantedAuthority> getAuthorities() {
        return this.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    // Metodo de Spring para comprobar si el usuario tiene acceso al sitio
    public boolean isEnabled() {
        return active;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getModified_date() {
        return modified_date;
    }

    public void setModified_date(LocalDate modified_date) {
        this.modified_date = modified_date;
    }
}
