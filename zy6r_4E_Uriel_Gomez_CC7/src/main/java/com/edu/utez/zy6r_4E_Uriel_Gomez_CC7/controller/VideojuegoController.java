package com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.controller;

import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.dto.VideojuegoDTO;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.model.videojuego.Videojuego;
import com.edu.utez.zy6r_4E_Uriel_Gomez_CC7.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videojuegos")
@CrossOrigin({"*"})
public class VideojuegoController {
    private final VideojuegoService videojuegoService;
    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }
    @PostMapping
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody VideojuegoDTO dto) {
        return new ResponseEntity<>(videojuegoService.crearVideojuego(dto), HttpStatus.CREATED);
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<Videojuego>> obtenerDisponibles() {
        return ResponseEntity.ok(videojuegoService.obtenerDisponibles());
    }
    @GetMapping
    public ResponseEntity<List<Videojuego>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }
}