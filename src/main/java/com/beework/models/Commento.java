package com.beework.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Commento {
    @Id
    @GeneratedValue
    @Column(name = "ID_COMMENTO", nullable = false, length = 255)
    private Long id;

    private String username;
    private String contenuto;


    public Commento() {}

    public Commento(Long id, String username, String contenuto, Task task) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commento commento = (Commento) o;
        return id.equals(commento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
