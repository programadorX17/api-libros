package com.parcial.capassb.mapper;


import com.parcial.capassb.dtos.CategoriaResponseDTO;
import com.parcial.capassb.dtos.LibroCarritoResponseDTO;
import com.parcial.capassb.dtos.LibroCreateRequestDTO;
import com.parcial.capassb.dtos.LibroResponseDTO;
import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

   public LibroResponseDTO toLibroResponseDTO(Libro libro){
       if(libro == null) return null;

       return new LibroResponseDTO(
               libro.getIdLibro(),
               libro.getNombreLibro(),
               libro.getNombreAutor(),
               libro.getCodigo(),
               libro.getDisponibilidad(),
               libro.getCategoriaLibro().getNombreCategoria());
   }

   public LibroCarritoResponseDTO tolibroCarritoResponseDTO(Libro libro){
       if(libro == null) return null;

       CategoriaResponseDTO categoriaResponseDTO = null;

       if(libro.getCategoriaLibro()!=null){
            categoriaResponseDTO = new CategoriaResponseDTO(
                   libro.getCategoriaLibro().getIdCategoria(),
                   libro.getCategoriaLibro().getNombreCategoria(),
                   libro.getCategoriaLibro().getCodCategoria()
           );
       }

       return new LibroCarritoResponseDTO(
               libro.getIdLibro(),
               libro.getNombreLibro(),
               libro.getNombreAutor(),
               categoriaResponseDTO
       );
   }


   public Libro toLibroG (LibroCreateRequestDTO libroCreateRequestDTO){
       if(libroCreateRequestDTO == null) return null;

       Libro libro = new Libro();
       libro.setNombreLibro(libroCreateRequestDTO.nombreLibro());
       libro.setNombreAutor(libroCreateRequestDTO.nombreAutor());
       libro.setCodigo(libroCreateRequestDTO.codigo());
       libro.setStock(libroCreateRequestDTO.stock());
       libro.setDisponibilidad(libroCreateRequestDTO.disponibilidad());

       return libro;
   }

   public void updateLibro(LibroCreateRequestDTO libroNuevo, Libro libroActual){
       if(libroNuevo == null || libroActual == null) return;

       libroActual.setNombreLibro(libroNuevo.nombreLibro());
       libroActual.setNombreAutor(libroNuevo.nombreAutor());
       libroActual.setCodigo(libroNuevo.codigo());
       libroActual.setStock(libroNuevo.stock());
       libroActual.setDisponibilidad(libroNuevo.disponibilidad());

   }
}

