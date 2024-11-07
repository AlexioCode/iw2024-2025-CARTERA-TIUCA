package com.example.carteratiuca.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Set;

@Entity

public class Proyecto extends AbstractEntity{

    private String titulo;
    private String nombreCorto;
    private BigDecimal coste;
    private int numEmpleados;
    private int importanciaPromotor;
    private BigDecimal interesados;
    private BigDecimal financiacionInteresado;
    private EstadoProyecto estado;
    private int gradoAvance;
    /* memoria: file?
    *  especificacionesTecnicas: file?
    *  presupuesto: File? */
    private JustificacionProyecto justificacion;

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
