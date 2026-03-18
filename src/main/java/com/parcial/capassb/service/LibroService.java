package com.parcial.capassb.service;

import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.LibroEntity;
import java.util.List;


public interface LibroService {


     LibroEntity registrarLibro(LibroEntity libro);

     List<LibroEntity> listarLibros();

     List<LibroEntity> listarPorCategoria(CategoriaLibro categoriaLibro);

     List<LibroEntity> listarPorDisponibilidad(Disponibilidad disponibilidad);

     List<LibroEntity> buscarPorNombre(String nombreLibro);

     LibroEntity buscarPorId(Long idLibro);

     LibroEntity prestarLibro(Long idLibro);

     LibroEntity actualizarLibro(Long idLibro, LibroEntity libro);

     LibroEntity devolverLibro(Long idLibro);



}
