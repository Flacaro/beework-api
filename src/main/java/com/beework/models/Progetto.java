package com.beework.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Progetto {
    @Id
    @GeneratedValue
    @Column(name = "ID_PROGETTO", nullable = false)
    private Long id;
    private String nome;
    private String descrizione;
    private int percentuale;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UTENTE_PARTECIPA_PROGETTO",
        joinColumns = { @JoinColumn(name = "ID_PROGETTO") }, inverseJoinColumns = { @JoinColumn(name = "ID_UTENTE") })
    @JsonIgnoreProperties("listaProgetti")
    private List<Utente> membriProgetto = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Progetto() {}

    public Progetto(String nome, String descrizione, int percentuale) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.percentuale = percentuale;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progetto progetto = (Progetto) o;
        return id.equals(progetto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    public List<Utente> getMembriProgetto() {
        return membriProgetto;
    }

    public void setMembriProgetto(List<Utente> membriProgetto) {
        this.membriProgetto = membriProgetto;
    }

}

