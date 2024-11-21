package es.uca.iw.carteratiuca.data;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Proyecto extends AbstractEntity {
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String nombreCorto;
    @NotEmpty
    private BigDecimal coste;
    @NotEmpty
    private int numEmpleados;
    @NotEmpty
    private int importanciaPromotor;
    @NotEmpty
    private BigDecimal interesados;
    @NotEmpty
    private BigDecimal financiacionInteresado;
    @NotEmpty
    private EstadoProyecto estado;
    private int gradoAvance = 0;

    @OneToOne
    private JustificacionProyecto justificacion;

    @NotEmpty
    private byte[] memoria;
    private byte[] especificacionesTecnicas;
    private byte[] presupuesto;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getNombreCorto() { return nombreCorto; }
    public void setNombreCorto(String nombreCorto) { this.nombreCorto = nombreCorto; }
    public BigDecimal getCoste() { return coste; }
    public void setCoste(BigDecimal coste) { this.coste = coste; }
    public int getNumEmpleados() { return numEmpleados; }
    public void setNumEmpleados(int numEmpleados) { this.numEmpleados = numEmpleados; }
    public int getImportanciaPromotor() { return importanciaPromotor; }
    public int getGradoAvance() { return gradoAvance; }
    public void setGradoAvance(int gradoAvance) { this.gradoAvance = gradoAvance; }

    public byte[] getMemoria() { return memoria; }
    public void setMemoria(byte[] memoria) {
        this.memoria = memoria;
    }
    public byte[] getEspecificacionesTecnicas() {
        return especificacionesTecnicas;
    }
    public void setEspecificacionesTecnicas(byte[] especificacionesTecnicas) {
        this.especificacionesTecnicas = especificacionesTecnicas;
    }
    public byte[] getPresupuesto() { return presupuesto; }
    public void setPresupuesto(byte[] presupuesto) {
        this.presupuesto = presupuesto;
    }

}
