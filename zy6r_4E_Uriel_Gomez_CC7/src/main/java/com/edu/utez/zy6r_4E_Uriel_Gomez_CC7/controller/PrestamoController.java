package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.controller;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto.PrestamoDTO;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.estado.EstadoPrestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.prestamo.Prestamo;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
@CrossOrigin({"*"})
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@Valid @RequestBody PrestamoDTO dto) {
        return new ResponseEntity<>(prestamoService.crearPrestamo(dto), HttpStatus.CREATED);
    }

    @PutMapping("/devolver/videojuego/{videojuegoId}")
    public ResponseEntity<Prestamo> devolverPorVideojuego(@PathVariable Long videojuegoId) {
        return ResponseEntity.ok(prestamoService.devolverPorVideojuego(videojuegoId));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Prestamo> cancelarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.cancelarPrestamo(id));
    }

    @PutMapping("/videojuego/{videojuegoId}/cancelar-todos")
    public ResponseEntity<List<Prestamo>> cancelarTodosDeVideojuego(@PathVariable Long videojuegoId) {
        return ResponseEntity.ok(prestamoService.cancelarTodosLosPrestamosDeVideojuego(videojuegoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Prestamo>> obtenerPorEstado(@PathVariable EstadoPrestamo estado) {
        return ResponseEntity.ok(prestamoService.obtenerPorEstado(estado));
    }
}
