package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.repository;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository <Videojuego, Long>{
    List<Videojuego> findByDisponible(Boolean disponible);
}
