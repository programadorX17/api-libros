package com.parcial.capassb.repository;


import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByNombreLibro(String nombreLibro);

    Boolean existsByCodigo(String codigo);

    Optional<Libro> findByCodigo(String codigo);

    List<Libro> findByCategoriaLibro_IdCategoria(Long idCategoria);

    List<Libro> findByDisponibilidad(Disponibilidad disponibilidad);

}
