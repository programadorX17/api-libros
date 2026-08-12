package com.parcial.capassb.service.impl;

import com.parcial.capassb.dtos.CategoriaCreateRequest;
import com.parcial.capassb.dtos.CategoriaResponseDTO;
import com.parcial.capassb.exceptions.RecursoDuplicado;
import com.parcial.capassb.exceptions.RecursoNoEncontrado;
import com.parcial.capassb.mapper.CategoriaMapper;
import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.repository.CategoriaRepository;
import com.parcial.capassb.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    CategoriaRepository categoriaRepository;
    CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper){
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaResponseDTO buscarPorId(Long idCategoria) {
        CategoriaLibro categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(()-> new RecursoNoEncontrado("La categoria con id: " + idCategoria + " no esta registrada."));
        return categoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO registrarCategoria(CategoriaCreateRequest categoria) {

        System.out.println("=== DIAGNÓSTICO SPRING ===");
        System.out.println("1. DTO de entrada -> Nombre: [" + categoria.nombreCategoria() + "], Código: [" + categoria.codCategoria() + "]");

        if (categoriaRepository.findByCodCategoria(categoria.codCategoria()).isPresent()){
           throw new RecursoDuplicado("El codigo " + categoria.codCategoria() + " ya esta registrado");
        }
        CategoriaLibro categoriaGuardar = categoriaMapper.toEntity(categoria);
        CategoriaLibro categoriaGuardada = categoriaRepository.save(categoriaGuardar);
        return categoriaMapper.toCategoriaResponseDTO(categoriaGuardada);
    }

    @Override
    public List<CategoriaResponseDTO> listarCategorias() {
        List<CategoriaLibro> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toCategoriaResponseDTO)
                .collect(Collectors.toList());
    }
}
