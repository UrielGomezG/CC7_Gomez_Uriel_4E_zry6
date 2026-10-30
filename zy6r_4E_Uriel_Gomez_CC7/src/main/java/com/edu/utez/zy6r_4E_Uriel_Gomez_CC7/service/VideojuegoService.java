package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.service;


import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto.VideojuegoDTO;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.exception.GlobalException;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.exception.ManejadorErrores;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.repository.VideojuegoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    public Videojuego crearVideojuego(VideojuegoDTO dto) {
        Videojuego videojuego = new Videojuego(dto.getTitulo(), dto.getPlataforma(), true);
        return videojuegoRepository.save(videojuego);
    }

    public List<Videojuego> obtenerDisponibles() {
        return videojuegoRepository.findByDisponible(true);
    }

    public List<Videojuego> obtenerTodos() {
        return videojuegoRepository.findAll();
    }

    public Videojuego buscarPorId(Long id) {
        return videojuegoRepository.findById(id)
                .orElseThrow(() -> new GlobalException("El videojuego con ID " + id + " no existe."));
    }
}

