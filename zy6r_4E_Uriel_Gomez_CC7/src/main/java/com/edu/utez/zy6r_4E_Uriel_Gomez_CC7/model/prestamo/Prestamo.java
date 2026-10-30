package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.prestamo;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.estado.EstadoPrestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreSolicitante;

    @Column(nullable = false)
    private LocalDateTime fechaPrestamo;

    private LocalDateTime fechaDevolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPrestamo estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videojuego_id", nullable = false)
    private Videojuego videojuego;

    public Prestamo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }

    public LocalDateTime getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDateTime fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDateTime getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public EstadoPrestamo getEstado() { return estado; }
    public void setEstado(EstadoPrestamo estado) { this.estado = estado; }

    public Videojuego getVideojuego() { return videojuego; }
    public void setVideojuego(Videojuego videojuego) { this.videojuego = videojuego; }
}
