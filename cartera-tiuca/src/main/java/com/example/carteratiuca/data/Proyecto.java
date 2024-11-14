package com.example.carteratiuca.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity

public class Proyecto {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    private String titulo;
    private String nombreCorto;
    private BigDecimal coste;
    private int numEmpleados;
    private int importanciaPromotor;
    private BigDecimal interesados;
    private BigDecimal financiacionInteresado;
    private EstadoProyecto estado;
    private int gradoAvance;

    @Embedded
    private JustificacionProyecto justificacion;

    private byte[] memoria;
    private byte[] especificacionesTecnicas;
    private byte[] presupuesto;

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

}
