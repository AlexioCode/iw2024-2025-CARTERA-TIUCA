package es.uca.iw.carteratiuca.servicitoRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Promotor {
    private String id;
    private String nombre;
    private String cargo;

    public Promotor() {}

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }

}
