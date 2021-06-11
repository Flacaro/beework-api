package com.beework.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Notifica {
    @Id
    @GeneratedValue
    @Column(name = "ID_NOTIFICA", nullable = false)
    private Long id;
    private String nome;
    private String descrizione;
    private Utente membro;

    public Notifica() {
    }

    public Notifica(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
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

}
