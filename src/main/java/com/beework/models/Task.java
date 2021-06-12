package com.beework.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "ID_TASK", nullable = false)
    private Long id;
    private String nome;
    private LocalDate scadenza;
    private String priorita;
    private boolean completato;
    private String descrizione;
    private String etichetta;

    @ManyToOne
    private Progetto progetto;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commento> commenti = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("listaTask")
    private List<Utente> membri = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Task() {}

    public Task(String nome, LocalDate scadenza, String priorita, boolean completato, String descrizione, String etichetta, Progetto progetto) {
        this.nome = nome;
        this.scadenza = scadenza;
        this.priorita = priorita;
        this.completato = completato;
        this.descrizione = descrizione;
        this.etichetta = etichetta;
        this.progetto = progetto;
    }

    public List<Utente> getMembri() {
        return membri;
    }

    public void setMembri(List<Utente> membri) {
        this.membri = membri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
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

    public Progetto getProgetto() {
        return progetto;
    }

    public void setProgetto(Progetto progetto) {
        this.progetto = progetto;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
}
