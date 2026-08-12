package com.parcial.capassb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "libros")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "nombre_libro", nullable = false, length = 80)
    private String nombreLibro;

    @Column(name = "autor", nullable = false, length = 80)
    private String nombreAutor;

    @Column(name = "codigo", nullable = false, length = 6, unique = true)
    private String codigo;

    @Column(name = "stock", nullable = false)
    private int stock;

    //Es la forma en la que indicamos a JPA que guarde el texto literal en vez de guardar su indice numerico
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Disponibilidad disponibilidad;

    //Corta la cadena del bucle infinito(Libro->Categoria->Libros->Categoria->...->∞)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name = "id_categoria", referencedColumnName = "id_categoria")
    private CategoriaLibro categoriaLibro;

    public Libro() {
    }

    public Libro(CategoriaLibro categoriaLibro, String codigo, Disponibilidad disponibilidad, Long idLibro, String nombreAutor, String nombreLibro, int stock) {
        this.categoriaLibro = categoriaLibro;
        this.codigo = codigo;
        this.disponibilidad = disponibilidad;
        this.idLibro = idLibro;
        this.nombreAutor = nombreAutor;
        this.nombreLibro = nombreLibro;
        this.stock = stock;
    }

    public CategoriaLibro getCategoriaLibro() {
        return categoriaLibro;
    }

    public void setCategoriaLibro(CategoriaLibro categoriaLibro) {
        this.categoriaLibro = categoriaLibro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
