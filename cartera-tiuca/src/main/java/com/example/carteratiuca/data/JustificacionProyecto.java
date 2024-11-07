package com.example.carteratiuca.data;

import java.util.Date;

public class JustificacionProyecto extends AbstractEntity{
    private boolean ActualizarOferta=false;
    private boolean AltaCalidad=false;
    private boolean AumentarInvestigacion=false;
    private boolean ConseguirTransparencia=false;
    private boolean GenerarValorCompartido=false;
    private boolean ConsolidarGobiernoSostenible=false;
    private boolean ReforzarPapelUCA=false;
    private String alcance;
    private Date fechaPuestaEnMarcha;
    private String normativa;

    public boolean isActualizarOferta() {return ActualizarOferta;}
    public void setActualizarOferta(boolean ActualizarOferta) {this.ActualizarOferta = ActualizarOferta;}
    public boolean isAltaCalidad() {return AltaCalidad;}
    public void setAltaCalidad(boolean AltaCalidad) {
        this.AltaCalidad = AltaCalidad;
    }
    public boolean isAumentarInvestigacion() {return AumentarInvestigacion;}
    public void setAumentarInvestigacion(boolean AumentarInvestigacion) {
        this.AumentarInvestigacion = AumentarInvestigacion;
    }
    public boolean isConseguirTransparencia() {return ConseguirTransparencia;}
    public void setConseguirTransparencia(boolean ConseguirTransparencia) {
        this.ConseguirTransparencia = ConseguirTransparencia;
    }
    public boolean isGenerarValorCompartido() {return GenerarValorCompartido;}
    public void setGenerarValorCompartido(boolean GenerarValorCompartido) {
        this.GenerarValorCompartido = GenerarValorCompartido;
    }
    public boolean isConsolidarGobiernoSostenible() {return ConsolidarGobiernoSostenible;}
    public void setConsolidarGobiernoSostenible(boolean ConsolidarGobiernoSostenible) {
        this.ConsolidarGobiernoSostenible = ConsolidarGobiernoSostenible;
    }
    public boolean isReforzarPapelUCA() {return ReforzarPapelUCA;}
    public void setReforzarPapel(boolean ReforzarPapelUCA) {
        this.ReforzarPapelUCA = ReforzarPapelUCA;
    }
    public String getAlcance() {return alcance;}
    public void setAlcance(String alcance) {this.alcance = alcance;}
    public Date getFechaPuestaEnMarcha() {return fechaPuestaEnMarcha;}
    public void setFechaPuestaEnMarcha(Date fechaPuestaEnMarcha) {
        this.fechaPuestaEnMarcha = fechaPuestaEnMarcha;
    }
    public String getNormativa() {return normativa;}
    public void setNormativa(String normativa) {
        this.normativa = normativa;
    }

}
