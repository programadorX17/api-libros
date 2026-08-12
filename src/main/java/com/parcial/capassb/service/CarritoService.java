package com.parcial.capassb.service;

import com.parcial.capassb.dtos.LibroCarritoRequestDTO;
import com.parcial.capassb.dtos.LibroCarritoResponseDTO;
import org.springframework.stereotype.Service;


public interface CarritoService {

    LibroCarritoResponseDTO agregarLibroAlCarrito(LibroCarritoRequestDTO requestDTO);

    public LibroCarritoResponseDTO devolverLibro(Long idLibro);
}
