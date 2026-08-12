package com.parcial.capassb.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoriaCreateRequest(

        @NotBlank(message = "¡El nombre de la categoria es obligatoria!")
        @Size(min = 1, max = 80)
        String nombreCategoria,

        @NotBlank(message = "¡El codigo esta en blanco!")
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$",
                message = "El codigo debe ser alfanumerico y tener exactamente 6 caracteres")
        String codCategoria
) {}
