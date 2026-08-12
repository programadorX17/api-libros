package com.parcial.capassb.dtos;

public record LibroCarritoResponseDTO(
        Long idLibro,

        String nombreLibro,

        String nombreAutor,

        CategoriaResponseDTO categoriaLibro
) {}
