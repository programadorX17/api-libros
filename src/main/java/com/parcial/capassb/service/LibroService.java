package com.parcial.capassb.service;


import com.parcial.capassb.dtos.LibroCreateRequestDTO;
import com.parcial.capassb.dtos.LibroResponseDTO;
import com.parcial.capassb.model.Disponibilidad;

import java.util.List;


public interface LibroService {


     LibroResponseDTO registrarLibro(LibroCreateRequestDTO libroCreateRequestDTO);

     List<LibroResponseDTO> listarLibros();

     List<LibroResponseDTO> listarPorCategoria(Long idCategoria);

     List<LibroResponseDTO> listarPorDisponibilidad(Disponibilidad disponibilidad);

     List<LibroResponseDTO> buscarPorNombre(String nombreLibro);

     LibroResponseDTO buscarPorId(Long idLibro);

     LibroResponseDTO actualizarLibro(Long idLibro, LibroCreateRequestDTO libroCreateRequestDTO);

}
