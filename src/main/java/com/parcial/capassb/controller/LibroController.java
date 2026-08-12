package com.parcial.capassb.controller;


import com.parcial.capassb.dtos.LibroCreateRequestDTO;
import com.parcial.capassb.dtos.LibroResponseDTO;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/api/libros")
public class LibroController {


    private final LibroService libroService;


    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroResponseDTO> registrarLibro(@Valid @RequestBody LibroCreateRequestDTO libroCreateRequestDTO){
        LibroResponseDTO nuevoLibro = libroService.registrarLibro(libroCreateRequestDTO);
            URI ubicacion = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevoLibro.idLibro())
                    .toUri();
            return ResponseEntity.created(ubicacion).body(nuevoLibro);
    }

    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> listarLibros(){
        return  ResponseEntity.ok(libroService.listarLibros());
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<LibroResponseDTO>> listarxCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok(libroService.listarPorCategoria(idCategoria));
    }

    @GetMapping("/disponibilidad/{disponibilidad}")
    public ResponseEntity<List<LibroResponseDTO>> listarxDisponiblidad(@PathVariable Disponibilidad disponibilidad){
        return ResponseEntity.ok(libroService.listarPorDisponibilidad(disponibilidad));
    }

    @GetMapping("/buscar/nombre/{nombreLibro}")
    public ResponseEntity<List<LibroResponseDTO>> buscarxNombre(@PathVariable String nombreLibro){
        return ResponseEntity.ok(libroService.buscarPorNombre(nombreLibro));
    }

    @GetMapping("/{idLibro}")
    public ResponseEntity<LibroResponseDTO> buscarxId(@PathVariable Long idLibro){
        return ResponseEntity.ok(libroService.buscarPorId(idLibro));
    }

    @PutMapping("/{idLibro}")
    public ResponseEntity<LibroResponseDTO> actualizarLibro(@Valid @PathVariable Long idLibro, @RequestBody LibroCreateRequestDTO libroCreateRequestDTO){
        LibroResponseDTO libro = libroService.actualizarLibro(idLibro,libroCreateRequestDTO);
        return ResponseEntity.ok(libro);
    }

}
