package com.parcial.capassb.service;

import com.parcial.capassb.dtos.CategoriaCreateRequest;
import com.parcial.capassb.dtos.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    CategoriaResponseDTO registrarCategoria(CategoriaCreateRequest categoria);

    List<CategoriaResponseDTO> listarCategorias();

    CategoriaResponseDTO buscarPorId(Long idLibro);

}
