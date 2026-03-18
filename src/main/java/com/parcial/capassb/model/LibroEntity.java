package com.parcial.capassb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @NotBlank
    @Size(min = 3)
    @Column(name = "nombre_libro", nullable = false, length = 80)
    private String nombreLibro;

    @NotBlank
    @Size(min = 2, max = 80)
    @Column(name = "autor", nullable = false, length = 80)
    private String nombreAutor;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$",
            message = "El codigo debe ser alfanumerico y tener exactamente 6 caracteres")
    @Column(name = "codigo", nullable = false, length = 6, unique = true)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaLibro categoriaLibro;

    @Min(0)
    @Column(name = "stock", nullable = false)
    private int stock;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Disponibilidad disponibilidad;
}
