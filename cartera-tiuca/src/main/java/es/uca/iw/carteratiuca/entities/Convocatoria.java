package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.Entity;

import java.util.Date;


@Entity
public class Convocatoria extends AbstractEntity {

    private Date fechaInicial;
    private Date fechaFinal;

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaIni) {
        fechaInicial = fechaIni;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFin) {
        fechaFinal = fechaFin;
    }
}
