package es.uca.iw.carteratiuca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Proyecto extends AbstractEntity {

    @ManyToOne
    @NotNull
    private User solicitante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "justificacion_id", referencedColumnName = "id") // Clave foránea en la tabla "Padre"
    @NotNull
    private JustificacionProyecto justificacion;

    @ManyToOne
    @JoinColumn(name = "promotor_id")
    @NotNull
    private User promotor;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String nombreCorto;

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

    @Min(value = 0, message = "La valoración del CIO debe ser mayor a 0")
    private int valoracionCIO = 0;

    @Min(value = 0, message = "La valoración del OTP debe ser mayor a 0")
    private int valoracionOTP = 0;

    @Min(value = 0, message = "La valoración final debe ser mayor a 0")
    private int valoracionFinal = 0;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoProyecto estado;

    @Enumerated(EnumType.STRING)
    private EstadosAvalacionValoracion estadoAvalacion;

    @Enumerated(EnumType.STRING)
    private EstadosAvalacionValoracion estadoValoracionCIO;

    @Enumerated(EnumType.STRING)
    private EstadosAvalacionValoracion estadoValoracionOTP;

    //@NotNull
    @ManyToOne
    @JoinColumn(name = "convocatoria_id")
    private Convocatoria convocatoria;

    @Lob
    @NotNull
    private byte[] memoria;

    @Lob
    private byte[] especificacionesTecnicas;

    @Lob
    private byte[] presupuesto;

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }


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
        this.justificacion = justificacion;
    }

    public User getPromotor() {return promotor;}

    public void setPromotor(User promotor) {this.promotor = promotor;}

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


    public EstadosAvalacionValoracion getEstadoAvalacion() {
        return estadoAvalacion;
    }

    public void setEstadoAvalacion(EstadosAvalacionValoracion estadoAvalacion) {
        this.estadoAvalacion = estadoAvalacion;
    }

    public EstadosAvalacionValoracion getEstadoValoracionCIO() {
        return estadoValoracionCIO;
    }

    public void setEstadoValoracionCIO(EstadosAvalacionValoracion estadoValoracionCIO) {
        this.estadoValoracionCIO = estadoValoracionCIO;
    }

    public EstadosAvalacionValoracion getEstadoValoracionOTP() {
        return estadoValoracionOTP;
    }

    public void setEstadoValoracionOTP(EstadosAvalacionValoracion estadoValoracionOTP) {
        this.estadoValoracionOTP = estadoValoracionOTP;
    }

    @Min(value = 0, message = "La valoración final debe ser mayor a 0")
    public int getValoracionFinal() {
        return valoracionFinal;
    }

    public void setValoracionFinal(@Min(value = 0, message = "La valoración final debe ser mayor a 0") int valoracionFinal) {
        this.valoracionFinal = valoracionFinal;
    }

    @Min(value = 0, message = "La valoración del CIO debe ser mayor a 0")
    public int getValoracionCIO() {
        return valoracionCIO;
    }

    public void setValoracionCIO(@Min(value = 0, message = "La valoración del CIO debe ser mayor a 0") int valoracionCIO) {
        this.valoracionCIO = valoracionCIO;
    }

    @Min(value = 0, message = "La valoración del OTP debe ser mayor a 0")
    public int getValoracionOTP() {
        return valoracionOTP;
    }

    public void setValoracionOTP(@Min(value = 0, message = "La valoración del OTP debe ser mayor a 0") int valoracionOTP) {
        this.valoracionOTP = valoracionOTP;
    }
}
