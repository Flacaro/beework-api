package com.beework.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Notifica {
    @Id
    @GeneratedValue
    @Column(name = "ID_NOTIFICA", nullable = false)
    private Long id;
    private String nome;
    private String descrizione;
    @ManyToOne
    private Utente utente;

    public Notifica() {}

    public Notifica(String nome, String descrizione, Utente utente) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.utente = utente;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Utente getUtente() { return utente; }

    public void setUtente(Utente utente) { this.utente = utente; }
}
