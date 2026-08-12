package com.parcial.capassb.repository;

import com.parcial.capassb.model.CategoriaLibro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaLibro, Long> {

    Optional<CategoriaLibro> findByNombreCategoria(String nombreCategoria);

    Optional<CategoriaLibro> findByCodCategoria(String codCategoria);


}
