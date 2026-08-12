package com.parcial.capassb.controller;

import com.parcial.capassb.dtos.CategoriaCreateRequest;
import com.parcial.capassb.dtos.CategoriaResponseDTO;
import com.parcial.capassb.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long idCategoria){
        return ResponseEntity.ok(categoriaService.buscarPorId(idCategoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> registrarCategoria(@Valid @RequestBody CategoriaCreateRequest categoria){
        CategoriaResponseDTO nuevaCategoria = categoriaService.registrarCategoria(categoria);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()//Toma la URI actual
                .path("/{id}")//Le añade un parametro a el final
                .buildAndExpand(nuevaCategoria.idCategoria())//Reemplaza el id por el nuevo que se le ha asignado enla base de datos
                .toUri();//Converted todo en un objet URI
        return ResponseEntity.created(ubicacion).body(nuevaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

}
