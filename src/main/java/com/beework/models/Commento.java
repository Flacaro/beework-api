package com.beework.models;

public class Commento {
    private String username;
    private String contenuto;

    public Commento(String username, String contenuto) {
        this.username = username;
        this.contenuto = contenuto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
}
