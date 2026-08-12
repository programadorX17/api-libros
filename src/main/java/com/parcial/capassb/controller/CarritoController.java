package com.parcial.capassb.controller;

import com.parcial.capassb.dtos.LibroCarritoRequestDTO;
import com.parcial.capassb.dtos.LibroCarritoResponseDTO;
import com.parcial.capassb.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<LibroCarritoResponseDTO> agregarLibroAlCarrito(@Valid @RequestBody LibroCarritoRequestDTO libroCarritoRequestDTO) {
        return ResponseEntity.ok(carritoService.agregarLibroAlCarrito(libroCarritoRequestDTO));
    }

    @PutMapping("/devolver/{idLibro}")
    public ResponseEntity<LibroCarritoResponseDTO> devolverLibro(@PathVariable Long idLibro) {
        return ResponseEntity.ok(carritoService.devolverLibro(idLibro));
    }
}

