package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {
    @NotNull(message = "El ID del videojuego no puede ser nulo")
    private Long videojuegoId;
    @NotBlank(message = "El nombre del solicitante es obligatorio")
    private static String nombreSolicitante;

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }

    public static String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }

}
