package com.beework.models;

import javax.persistence.*;
import java.util.List;

@Entity
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
    private List<Utente> membri;

    public Progetto() {}

    public Progetto(Long id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
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

    public List<Utente> getMembri() {
        return membri;
    }

    public void setMembri(List<Utente> membri) {
        this.membri = membri;
    }
}

