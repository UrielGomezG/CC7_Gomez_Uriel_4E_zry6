package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.service;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto.PrestamoDTO;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.exception.GlobalException;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.estado.EstadoPrestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.prestamo.Prestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.repository.PrestamoRepository;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.repository.VideojuegoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final VideojuegoRepository videojuegoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, VideojuegoRepository videojuegoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.videojuegoRepository = videojuegoRepository;
    }

    @Transactional
    public Prestamo crearPrestamo(PrestamoDTO dto) {
        Videojuego videojuego = videojuegoRepository.findById(dto.getVideojuegoId())
                .orElseThrow(() -> new GlobalException("El videojuego especificado no existe."));

        if (!videojuego.getDisponible()) {
            throw new GlobalException("El videojuego no está disponible para préstamo.");
        }

        videojuego.setDisponible(false);
        videojuegoRepository.save(videojuego);

        Prestamo prestamo = new Prestamo();
        prestamo.setNombreSolicitante(dto.getNombreSolicitante());
        prestamo.setFechaPrestamo(LocalDateTime.now());
        prestamo.setFechaDevolucion(null);
        prestamo.setEstado(EstadoPrestamo.ACTIVO);
        prestamo.setVideojuego(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo devolverPorVideojuego(Long videojuegoId) {
        Videojuego videojuego = videojuegoRepository.findById(videojuegoId)
                .orElseThrow(() -> new GlobalException("El videojuego con ID " + videojuegoId + " no existe."));

        Prestamo prestamo = prestamoRepository.findFirstByVideojuegoAndEstado(videojuego, EstadoPrestamo.ACTIVO)
                .orElseThrow(() -> new GlobalException("No existe un préstamo ACTIVO para este videojuego."));

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDateTime.now());

        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo cancelarPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new GlobalException("El préstamo con ID " + prestamoId + " no existe."));

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            throw new GlobalException("Solo se pueden cancelar préstamos en estado ACTIVO.");
        }

        prestamo.setEstado(EstadoPrestamo.CANCELADO);

        Videojuego videojuego = prestamo.getVideojuego();
        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public List<Prestamo> cancelarTodosLosPrestamosDeVideojuego(Long videojuegoId) {
        Videojuego videojuego = videojuegoRepository.findById(videojuegoId)
                .orElseThrow(() -> new GlobalException("El videojuego con ID " + videojuegoId + " no existe."));

        List<Prestamo> prestamosActivos = prestamoRepository.findByVideojuegoAndEstado(videojuego, EstadoPrestamo.ACTIVO);

        for (Prestamo prestamo : prestamosActivos) {
            prestamo.setEstado(EstadoPrestamo.CANCELADO);
        }

        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.saveAll(prestamosActivos);
    }

    public List<Prestamo> obtenerPorEstado(EstadoPrestamo estado) {
        return prestamoRepository.findByEstado(estado);
    }
}