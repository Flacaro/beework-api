package com.beework.models.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Registrazione {
    @NotBlank(message = "Can't be empty")
    private String nome;

    @NotBlank(message = "Can't be empty")
    private String cognome;

    @NotBlank(message = "Can't be empty")
    @Email(message = "Should be an email")
    private String email;

    @NotBlank(message = "Can't be empty")
    private String password;

    @NotBlank(message = "Can't be empty")
    private String username;

    public Registrazione() {
    }

    public Registrazione(@NotBlank(message = "Can't be empty") String name,
                         @NotBlank(message = "Can't be empty") String lastname,
                         @NotBlank(message = "Can't be empty") @Email(message = "Should be an email") String email,
                         @NotBlank(message = "Can't be empty") String password, @NotBlank(message = "Can't be empty") String username
    ) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
