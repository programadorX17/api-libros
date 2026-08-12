package com.parcial.capassb.dtos;

import com.parcial.capassb.model.Disponibilidad;
import jakarta.validation.constraints.*;

public record LibroCreateRequestDTO(
        @NotBlank(message = "¡El nombre del libro es obligatorio!")
        @Size(min = 1, max = 100)
        String nombreLibro,

        @NotBlank(message = "¡El nombre de autor es obligatorio!")
        @Size(min = 3, max = 100)
        String nombreAutor,

        @NotBlank(message = "¡El codigo esta en blanco!")
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$",
                message = "El codigo debe ser alfanumerico y tener exactamente 6 caracteres")
        String codigo,

        @NotNull(message = "¡El id de la categoria es obligatoria!")
        Long idCategoriaLibro,

        @NotNull
        @Min(0)
        int stock,

        @NotNull
        Disponibilidad disponibilidad
) {}
