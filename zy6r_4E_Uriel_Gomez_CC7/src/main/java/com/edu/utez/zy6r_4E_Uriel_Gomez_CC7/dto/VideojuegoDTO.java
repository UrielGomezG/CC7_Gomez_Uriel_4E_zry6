package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideojuegoDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La plataforma es obligatoria")
    private String plataforma;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
}