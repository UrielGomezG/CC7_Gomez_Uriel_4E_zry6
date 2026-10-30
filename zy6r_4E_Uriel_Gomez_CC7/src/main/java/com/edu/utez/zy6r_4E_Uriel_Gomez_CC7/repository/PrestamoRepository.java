package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.repository;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.estado.EstadoPrestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.prestamo.Prestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByEstado(EstadoPrestamo estadoPrestamo);
    List<Prestamo> findByVideojuegoAndEstado(Videojuego videojuego, EstadoPrestamo estadoPrestamo);
    Optional<Prestamo> findFirstByVideojuegoAndEstado(Videojuego videojuego, EstadoPrestamo estadoPrestamo);
}
