package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String plataforma;

    @Column(nullable = false)
    private Boolean disponible = true;

    public Videojuego() {}

    public Videojuego(String titulo, String plataforma, Boolean disponible) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
