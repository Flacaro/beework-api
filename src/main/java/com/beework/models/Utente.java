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

    public Utente() {
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

}
