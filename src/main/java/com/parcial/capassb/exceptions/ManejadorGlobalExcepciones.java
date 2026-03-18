package com.parcial.capassb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<ErrorDetalles> manejarNoEncontrado(RecursoNoEncontrado ex){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), "El recurso solicitado no existe");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoDuplicado.class)
    public ResponseEntity<ErrorDetalles> manejarDuplicados(RecursoDuplicado ex){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), "Conflicto por dato existente");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReglaNegocio.class)
    public ResponseEntity<ErrorDetalles> manejoReglaNegocio(ReglaNegocio ex){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), "Error en la operacio");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
