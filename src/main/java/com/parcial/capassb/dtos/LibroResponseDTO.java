package com.parcial.capassb.dtos;

import com.parcial.capassb.model.Disponibilidad;

public record LibroResponseDTO(
        Long idLibro,

        String nombreLibro,

        String nombreAutor,

        String codigo,

        Disponibilidad disponibilidad,

        String  nombreCategoria
) {}
