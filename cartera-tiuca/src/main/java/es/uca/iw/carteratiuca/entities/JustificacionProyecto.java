package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
public class JustificacionProyecto extends AbstractEntity{

    private boolean actualizarOferta = false;
    private boolean altaCalidad = false;
    private boolean aumentarInvestigacion = false;
    private boolean conseguirTransparencia = false;
    private boolean generarValorCompartido = false;
    private boolean consolidarGobiernoSostenible = false;
    private boolean reforzarPapelUCA = false;
    @NotEmpty
    private String alcance;
    private LocalDate fechaPuestaEnMarcha;
    private String normativa;

    public boolean isActualizarOferta() {return actualizarOferta;}
    public void setActualizarOferta(boolean ActualizarOferta) {this.actualizarOferta = ActualizarOferta;}
    public boolean isAltaCalidad() {return altaCalidad;}
    public void setAltaCalidad(boolean AltaCalidad) {
        this.altaCalidad = AltaCalidad;
    }
    public boolean isAumentarInvestigacion() {return aumentarInvestigacion;}
    public void setAumentarInvestigacion(boolean AumentarInvestigacion) {
        this.aumentarInvestigacion = AumentarInvestigacion;
    }
    public boolean isConseguirTransparencia() {return conseguirTransparencia;}
    public void setConseguirTransparencia(boolean ConseguirTransparencia) {
        this.conseguirTransparencia = ConseguirTransparencia;
    }
    public boolean isGenerarValorCompartido() {return generarValorCompartido;}
    public void setGenerarValorCompartido(boolean GenerarValorCompartido) {
        this.generarValorCompartido = GenerarValorCompartido;
    }
    public boolean isConsolidarGobiernoSostenible() {return consolidarGobiernoSostenible;}
    public void setConsolidarGobiernoSostenible(boolean ConsolidarGobiernoSostenible) {
        this.consolidarGobiernoSostenible = ConsolidarGobiernoSostenible;
    }
    public boolean isReforzarPapelUCA() {return reforzarPapelUCA;}
    public void setReforzarPapel(boolean ReforzarPapelUCA) {
        this.reforzarPapelUCA = ReforzarPapelUCA;
    }
    public String getAlcance() {return alcance;}
    public void setAlcance(String alcance) {this.alcance = alcance;}
    public LocalDate getFechaPuestaEnMarcha() {return fechaPuestaEnMarcha;}
    public void setFechaPuestaEnMarcha(LocalDate fechaPuestaEnMarcha) {
        this.fechaPuestaEnMarcha = fechaPuestaEnMarcha;
    }
    public String getNormativa() {return normativa;}
    public void setNormativa(String normativa) {
        this.normativa = normativa;
    }


}
