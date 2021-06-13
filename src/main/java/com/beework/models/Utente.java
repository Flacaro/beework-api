package com.beework.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Utente implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID_UTENTE", nullable = false)
    private Long id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String bio;
    @ManyToMany(mappedBy = "membri", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("membri")
    private List<Task> listaTask = new ArrayList<>();
    @ManyToMany(mappedBy = "membriProgetto", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("membriProgetto")
    private List<Progetto> listaProgetti = new ArrayList<>();

    public Utente(String nome, String cognome, String email, String password) {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id.equals(utente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Utente() { }

    public Utente(String username, String password, String nome, String cognome, String email, String bio) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Task> getListaTask() {
        return listaTask;
    }

    public void setListaTask(List<Task> listaTask) {
        this.listaTask = listaTask;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Progetto> getListaProgetti() { return listaProgetti; }

    public void setListaProgetti(List<Progetto> listaProgetti) { this.listaProgetti = listaProgetti; }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
