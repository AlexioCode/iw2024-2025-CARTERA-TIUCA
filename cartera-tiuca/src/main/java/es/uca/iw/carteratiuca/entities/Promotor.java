package es.uca.iw.carteratiuca.entities;

import jakarta.validation.constraints.NotEmpty;

public class Promotor extends User {

    @NotEmpty
    private String cargo;

    public String getCargo() {return cargo;}
    public void setCargo(String cargo) {this.cargo = cargo;}
}
