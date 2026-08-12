package com.parcial.capassb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {


    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<ErrorDetalles> manejarNoEncontrado(RecursoNoEncontrado ex, WebRequest request){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecursoDuplicado.class)
    public ResponseEntity<ErrorDetalles> manejarDuplicados(RecursoDuplicado ex, WebRequest request){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReglaNegocio.class)
    public ResponseEntity<ErrorDetalles> manejoReglaNegocio(ReglaNegocio ex, WebRequest request){
        ErrorDetalles error = new ErrorDetalles(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetalles> manejarTipoIncorrecto(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        String mensaje = String.format("El parámetro '%s' debe ser de tipo numérico (%s)",
                ex.getName(), ex.getRequiredType().getSimpleName());

        ErrorDetalles error = new ErrorDetalles(
                LocalDateTime.now(),
                mensaje,
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacionesCampos(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> erroresCampos = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                erroresCampos.put(err.getField(), err.getDefaultMessage())
        );

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("mensaje", "Falló la validación de los datos enviados");
        respuesta.put("errores", erroresCampos);
        respuesta.put("detalles", request.getDescription(false));

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarExcepcionGlobal(
            Exception ex, WebRequest request) {

        ErrorDetalles error = new ErrorDetalles(
                LocalDateTime.now(),
                "Ocurrió un error interno e inesperado en el servidor",
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
