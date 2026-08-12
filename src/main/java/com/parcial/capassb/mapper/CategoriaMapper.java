package com.parcial.capassb.mapper;

import com.parcial.capassb.dtos.CategoriaCreateRequest;
import com.parcial.capassb.dtos.CategoriaResponseDTO;
import com.parcial.capassb.model.CategoriaLibro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    private final ModelMapper modelMapper;

    public CategoriaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public CategoriaLibro toEntity(CategoriaCreateRequest categoriaCreateRequest){
        if(categoriaCreateRequest == null){
            return null;
        }

        CategoriaLibro categoria = new CategoriaLibro();
        categoria.setNombreCategoria(categoriaCreateRequest.nombreCategoria());
        categoria.setCodCategoria(categoriaCreateRequest.codCategoria());

        return categoria;
    }

    public CategoriaResponseDTO toCategoriaResponseDTO(CategoriaLibro categoriaLibro){
        if(categoriaLibro == null) return null;

        return new CategoriaResponseDTO(
                categoriaLibro.getIdCategoria(),
                categoriaLibro.getNombreCategoria(),
                categoriaLibro.getCodCategoria()
        );
    }

    public void updateLibro(CategoriaCreateRequest datosNuevos, CategoriaLibro datosViejos){
         modelMapper.map(datosNuevos, datosViejos);
    }

}
