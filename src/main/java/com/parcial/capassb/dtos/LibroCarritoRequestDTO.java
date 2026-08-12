package com.parcial.capassb.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LibroCarritoRequestDTO(

        @NotNull(message = "El ID del libro es obligatorio")
        Long idLibro,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad minima es 1")
        Integer cantidad
) {}
