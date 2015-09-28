package br.com.timbrasil.operations.models;

/**
 * Created by IgorVasconcelos on 28/09/2015.
 */
public enum LogAcceptionType {
    P("Presencial"),
    S("Amostragem");

    private String name;

    LogAcceptionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
