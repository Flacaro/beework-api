package com.beework.models;

import java.util.List;

public class Task {
    private String id;
    private String nome;
    private String scadenza;          // VEDERE SE FUNZIONA CON IL DB
    private String priorita;
    private boolean completato;
    private String descrizione;
    private String etichetta;
    private List<Commento> commenti;
    private String progettoId;


    public Task(String id, String nome, String scadenza, String priorita, boolean completato, String descrizione, String etichetta, List<Commento> commenti, String progettoId) {
        this.id = id;
        this.nome = nome;
        this.scadenza = scadenza;
        this.priorita = priorita;
        this.completato = completato;
        this.descrizione = descrizione;
        this.etichetta = etichetta;
        this.commenti = commenti;
        this.progettoId = progettoId;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public String getProgettoId() { return progettoId; }

    public void setProgettoId(String progettoId) { this.progettoId = progettoId; }
}
