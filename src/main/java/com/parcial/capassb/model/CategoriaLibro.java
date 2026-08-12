package com.parcial.capassb.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre_categoria", nullable = false, length = 80)
    private String nombreCategoria;

    @Column(name = "cod_categoria")
    private String codCategoria;

    @OneToMany(mappedBy = "categoriaLibro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    public CategoriaLibro(String codCategoria, Long idCategoria, List<Libro> libros, String nombreCategoria) {
        this.codCategoria = codCategoria;
        this.idCategoria = idCategoria;
        this.libros = libros;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaLibro() {

    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
