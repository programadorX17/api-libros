package com.parcial.capassb.service.impl;

import com.parcial.capassb.dtos.LibroCarritoRequestDTO;
import com.parcial.capassb.dtos.LibroCarritoResponseDTO;
import com.parcial.capassb.exceptions.RecursoNoEncontrado;
import com.parcial.capassb.exceptions.ReglaNegocio;
import com.parcial.capassb.mapper.LibroMapper;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.Libro;
import com.parcial.capassb.service.CarritoService;
import com.parcial.capassb.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoServiceImpl implements CarritoService {


    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;


    //Inyeccion por constructor
    public CarritoServiceImpl(LibroMapper libroMapper, LibroRepository libroRepository) {
        this.libroMapper = libroMapper;
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroCarritoResponseDTO agregarLibroAlCarrito(LibroCarritoRequestDTO requestDTO) {

        Libro libro = libroRepository.findById(requestDTO.idLibro())
                .orElseThrow(() -> new RecursoNoEncontrado("¡El libro solicitado no se encuentra en la base de datos!"));

        if(libro.getStock() < requestDTO.cantidad()){
            throw new ReglaNegocio("¡No hay suficientes unidades!, Stock disponible: " + libro.getStock());
        }

        libro.setStock(libro.getStock() - requestDTO.cantidad());

        if(libro.getStock() == 0){
            libro.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);
        }

        Libro libroGuardado = libroRepository.save(libro);

        return libroMapper.tolibroCarritoResponseDTO(libroGuardado);
    }

    @Override
    @Transactional
    public LibroCarritoResponseDTO devolverLibro(Long idLibro) {

        Libro libro = libroRepository.findById(idLibro).
                orElseThrow(()-> new RecursoNoEncontrado("Error al devolver:  ¡El libro no se encuentra en la base de datos!"));

        libro.setStock(libro.getStock()+1);
        if(libro.getDisponibilidad()==Disponibilidad.NO_DISPONIBLE) {
            libro.setDisponibilidad(Disponibilidad.DISPONIBLE);
        }

        Libro libroGuardado = libroRepository.save(libro);

        return libroMapper.tolibroCarritoResponseDTO(libroGuardado);
    }
}
