package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Proyecto extends AbstractEntity {

    @ManyToOne
    // @NotNull
    private User solicitante;

    @OneToOne
    // @NotNull
    private JustificacionProyecto justificacion;

    @ManyToOne
    // @NotNull
    private User promotor;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String nombreCorto;

    @NotNull
    private BigDecimal coste;

    @Min(value = 0, message = "El número de empleados debe ser mayor de 0")
    private int numEmpleados;

    @NotNull
    private int importanciaPromotor;

    @NotNull
    private String interesados;

    @NotNull
    private BigDecimal financiacionInteresado;

    private Integer gradoAvance = 0;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoProyecto estado;

    @Enumerated(EnumType.STRING)
    private EstadoAvalacionProyecto estadoAvalacion;

    @Lob
    @NotNull
    private byte[] memoria;

    @Lob
    private byte[] especificacionesTecnicas;

    @Lob
    private byte[] presupuesto;


    public User getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(User solicitante) {
        this.solicitante = solicitante;
    }

    public JustificacionProyecto getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(JustificacionProyecto justificacion) {
        justificacion = justificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    public int getImportanciaPromotor() {
        return importanciaPromotor;
    }

    public void setImportanciaPromotor(int impPromotor) {
        this.importanciaPromotor = impPromotor;
    }

    public String getInteresados() {
        return interesados;
    }

    public void setInteresados(String interes) {
        this.interesados = interes;
    }

    public BigDecimal getFinanciacionInteresado() {
        return financiacionInteresado;
    }

    public void setFinanciacionInteresado(BigDecimal financiacion) {
        this.financiacionInteresado = financiacion;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public Integer getGradoAvance() {
        return gradoAvance;
    }

    public void setGradoAvance(int gradoAvance) {
        this.gradoAvance = gradoAvance;
    }

    public byte[] getMemoria() {
        return memoria;
    }

    public void setMemoria(byte[] memoria) {
        this.memoria = memoria;
    }

    public byte[] getEspecificacionesTecnicas() {
        return especificacionesTecnicas;
    }

    public void setEspecificacionesTecnicas(byte[] especificacionesTecnicas) {
        this.especificacionesTecnicas = especificacionesTecnicas;
    }

    public byte[] getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(byte[] presupuesto) {
        this.presupuesto = presupuesto;
    }


    public EstadoAvalacionProyecto getEstadoAvalacion() {
        return estadoAvalacion;
    }

    public void setEstadoAvalacion(EstadoAvalacionProyecto estadoAvalacion) {
        this.estadoAvalacion = estadoAvalacion;
    }
}
