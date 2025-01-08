package es.uca.iw.carteratiuca.servicitoRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Json {

    private String status;
    private Promotor[] data;

    public Json(){}

    public Promotor[] getData() { return data; }
}
