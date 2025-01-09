package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;


@Entity
public class Convocatoria extends AbstractEntity {

    @NotEmpty
    private String nombre;
    private LocalDate fecha_inicial;
    private LocalDate fecha_final;
    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(LocalDate fechaIni) {
        fecha_inicial = fechaIni;
    }

    public LocalDate getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(LocalDate fechaFin) {
        fecha_final = fechaFin;
    }

    public boolean isActivo() { return activo; }

     public void setActivo(boolean activo) {this.activo = activo;}
}
