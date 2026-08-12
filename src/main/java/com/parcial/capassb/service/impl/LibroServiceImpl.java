package com.parcial.capassb.service.impl;

import com.parcial.capassb.dtos.LibroCreateRequestDTO;
import com.parcial.capassb.dtos.LibroResponseDTO;
import com.parcial.capassb.exceptions.RecursoDuplicado;
import com.parcial.capassb.exceptions.RecursoNoEncontrado;
import com.parcial.capassb.mapper.LibroMapper;
import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.Libro;
import com.parcial.capassb.repository.CategoriaRepository;
import com.parcial.capassb.repository.LibroRepository;
import com.parcial.capassb.service.LibroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class LibroServiceImpl implements LibroService {


    private final LibroRepository libroRepository;

    private final CategoriaRepository categoriaRepository;

    private final LibroMapper libroMapper;

    public LibroServiceImpl(CategoriaRepository categoriaRepository, LibroRepository libroRepository, LibroMapper libroMapper) {
        this.categoriaRepository = categoriaRepository;
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    @Override
    @Transactional
    public LibroResponseDTO registrarLibro(LibroCreateRequestDTO libroCreateRequestDTO) {

        if (libroRepository.existsByCodigo(libroCreateRequestDTO.codigo())) {
            throw new RecursoDuplicado("El Codigo " + libroCreateRequestDTO.codigo() + " ya esta registrado.");
        }

        Libro libroGuardar = libroMapper.toLibroG(libroCreateRequestDTO);

        if(libroCreateRequestDTO.idCategoriaLibro()!=null) {
            CategoriaLibro categoria = categoriaRepository.findById(libroCreateRequestDTO.idCategoriaLibro())
                    .orElseThrow(()-> new RecursoNoEncontrado("¡La categoria no se encuentra registrada!"));

             libroGuardar.setCategoriaLibro(categoria);
        }
        Libro libroGuardado = libroRepository.save(libroGuardar); 

        return libroMapper.toLibroResponseDTO(libroGuardado);
    }

    @Override
    public List<LibroResponseDTO> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .map(libro -> libroMapper.toLibroResponseDTO(libro))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroResponseDTO> listarPorCategoria(Long idCategoria) {
        List<Libro> libros = libroRepository.findByCategoriaLibro_IdCategoria(idCategoria);
        return libros.stream()
                .map(libro -> libroMapper.toLibroResponseDTO(libro))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroResponseDTO> listarPorDisponibilidad(Disponibilidad disponibilidad) {
        List<Libro> libros = libroRepository.findByDisponibilidad(disponibilidad);
        return libros.stream()
                .map(libro-> libroMapper.toLibroResponseDTO(libro))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibroResponseDTO> buscarPorNombre(String nombreLibro) {
        List<Libro> libros = libroRepository.findByNombreLibro(nombreLibro);
        return libros.stream()
                .map(libro-> libroMapper.toLibroResponseDTO(libro))
                .collect(Collectors.toList());
    }

    @Override
    public LibroResponseDTO buscarPorId(Long idLibro) {
         Libro libroEntity = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RecursoNoEncontrado("Libro no encontrado"));

         return libroMapper.toLibroResponseDTO(libroEntity);
    }


    @Override
    @Transactional
    public LibroResponseDTO actualizarLibro(Long idLibro, LibroCreateRequestDTO libroCreateRequestDTO) {

        Libro libroLocal = libroRepository.findById(idLibro).
                orElseThrow(()-> new RecursoNoEncontrado("¡Libro no encontrado!"));


        //Veririficamos que el codigo de libroaActualizar no sea el mismo a el de libroaIngresar
        if(!libroLocal.getCodigo().equals(libroCreateRequestDTO.codigo())){
            //Se verifica que ahora que el codigo no coincida con el resto de los libros
            if(libroRepository.existsByCodigo(libroCreateRequestDTO.codigo())){
                throw new RecursoDuplicado("¡El codigo ya se encuentra en uso!");
            }
        }
        //Se actualiza todo el libro
         libroMapper.updateLibro(libroCreateRequestDTO, libroLocal);

        if(libroCreateRequestDTO.idCategoriaLibro()!=null){
            CategoriaLibro categoriaLibro = categoriaRepository.findById(libroCreateRequestDTO.idCategoriaLibro()).
                    orElseThrow(()-> new RecursoNoEncontrado("Categoria no encontrada"));

            libroLocal.setCategoriaLibro(categoriaLibro);
        }else{
            libroLocal.setCategoriaLibro(null);
        }

        return libroMapper.toLibroResponseDTO(libroRepository.save(libroLocal));
    }




}
