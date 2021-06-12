package com.beework.models;

import javax.persistence.*;

@Entity
public class Commento {
    @Id
    @GeneratedValue
    @Column(name = "ID_COMMENTO", nullable = false, length = 255)
    private Long id;
    private String contenuto;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Utente utente;

    public Commento() {}

    public Commento(String contenuto, Task task, Utente utente) {
        this.contenuto = contenuto;
        this.task = task;
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
