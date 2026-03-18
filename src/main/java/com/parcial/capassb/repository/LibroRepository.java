package com.parcial.capassb.repository;

import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    List<LibroEntity> findByNombreLibro(String nombreLibro);

    Optional<LibroEntity> findByCodigo(String codigo);

    List<LibroEntity> findByCategoriaLibro(CategoriaLibro categoriaLibro);

    List<LibroEntity> findByDisponibilidad(Disponibilidad disponibilidad);

}
