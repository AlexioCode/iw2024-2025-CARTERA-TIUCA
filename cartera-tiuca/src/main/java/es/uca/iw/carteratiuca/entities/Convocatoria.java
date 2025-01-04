package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.Entity;

import java.util.Date;


@Entity
public class Convocatoria extends AbstractEntity {

    private String nombre;
    private Date fecha_inicial;
    private Date fecha_final;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fechaIni) {
        fecha_inicial = fechaIni;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fechaFin) {
        fecha_final = fechaFin;
    }
}
