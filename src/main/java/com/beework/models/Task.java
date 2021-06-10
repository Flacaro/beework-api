package com.beework.models;

import java.util.ArrayList;

public class Task {
    private int id;
    private String nome;
    private String scadenza;
    private String priorita;
    private boolean completato;
    private String descrizione;
    private String etichetta;
    private ArrayList<Commento> commenti;

    public Task(int id, String nome, String scadenza, String priorita, boolean completato, String descrizione, String etichetta, ArrayList<Commento> commenti) {
        this.id = id;
        this.nome = nome;
        this.scadenza = scadenza;
        this.priorita = priorita;
        this.completato = completato;
        this.descrizione = descrizione;
        this.etichetta = etichetta;
        this.commenti = commenti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getPriorita() {
        return priorita;
    }

    public void setPriorita(String priorita) {
        this.priorita = priorita;
    }

    public boolean isCompletato() {
        return completato;
    }

    public void setCompletato(boolean completato) {
        this.completato = completato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getEtichetta() {
        return etichetta;
    }

    public void setEtichetta(String etichetta) {
        this.etichetta = etichetta;
    }

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(ArrayList<Commento> commenti) {
        this.commenti = commenti;
    }
}
