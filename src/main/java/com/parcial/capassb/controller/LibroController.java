package com.parcial.capassb.controller;

import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.LibroEntity;
import com.parcial.capassb.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/libros")
public class LibroController {


    LibroService libroService;
    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    @PostMapping("/crear")
    public ResponseEntity<LibroEntity> registrarLibro(@RequestBody LibroEntity libro){
            LibroEntity nuevoLibro = libroService.registrarLibro(libro);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LibroEntity>> listarLibros(){
        return ResponseEntity.ok(libroService.listarLibros());
    }

    @GetMapping("/catlistar/{categoriaLibro}")
    public ResponseEntity<List<LibroEntity>> listarxCategoria(@PathVariable CategoriaLibro categoriaLibro){
        return ResponseEntity.ok(libroService.listarPorCategoria(categoriaLibro));
    }

    @GetMapping("/dislistar/{disponibilidad}")
    public ResponseEntity<List<LibroEntity>> listarxDisponiblidad(@PathVariable Disponibilidad disponibilidad){
        return ResponseEntity.ok(libroService.listarPorDisponibilidad(disponibilidad));
    }

    @GetMapping("/busqnombe/{nombreLibro}")
    public ResponseEntity<List<LibroEntity>> buscarxNombre(@PathVariable String nombreLibro){
        return ResponseEntity.ok(libroService.buscarPorNombre(nombreLibro));
    }

    @GetMapping("/busqid/{idLibro}")
    public ResponseEntity<LibroEntity> buscarxId(@PathVariable Long idLibro){
        return ResponseEntity.ok(libroService.buscarPorId(idLibro));
    }

    @GetMapping("/prestar/{idLibro}")
    public ResponseEntity<LibroEntity> prestarLibro(@PathVariable Long idLibro){
            return ResponseEntity.ok(libroService.prestarLibro(idLibro));
    }

    @PutMapping("/actualizar/{idLibro}")
    public ResponseEntity<LibroEntity> actualizarLibro(@PathVariable Long idLibro, @RequestBody LibroEntity libro){
        LibroEntity libroEntity = libroService.actualizarLibro(idLibro,libro);
        return ResponseEntity.ok(libroEntity);
    }

    @PutMapping("/devolver/{idLibro}")
    public ResponseEntity<LibroEntity> devolverLibro(@PathVariable Long idLibro){
            return ResponseEntity.ok(libroService.devolverLibro(idLibro));
    }

}
