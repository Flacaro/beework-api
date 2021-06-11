package com.beework.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commento {
    @Id
    @GeneratedValue
    @Column(name = "ID_COMMENTO", nullable = false)
    private Long id;
    private String username;
    private String contenuto;

    public Commento() {}

    public Commento(String username, String contenuto) {
        this.username = username;
        this.contenuto = contenuto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
