package com.parcial.capassb.service.impl;
import com.parcial.capassb.exceptions.RecursoDuplicado;
import com.parcial.capassb.exceptions.RecursoNoEncontrado;
import com.parcial.capassb.exceptions.ReglaNegocio;
import com.parcial.capassb.model.CategoriaLibro;
import com.parcial.capassb.model.Disponibilidad;
import com.parcial.capassb.model.LibroEntity;
import com.parcial.capassb.repository.LibroRepository;
import com.parcial.capassb.service.LibroService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LibroServiceImpl implements LibroService {


    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroEntity registrarLibro(LibroEntity libro) {
        if (libroRepository.findByCodigo(libro.getCodigo()).isPresent()) {
            throw new RecursoDuplicado("El Codigo " + libro.getCodigo() + " ya esta registrado.");
        }
        return libroRepository.save(libro);
    }

    @Override
    public List<LibroEntity> listarLibros() {
       return libroRepository.findAll();
    }

    @Override
    public List<LibroEntity> listarPorCategoria(CategoriaLibro categoriaLibro) {
        return libroRepository.findByCategoriaLibro(categoriaLibro);
    }

    @Override
    public List<LibroEntity> listarPorDisponibilidad(Disponibilidad disponibilidad) {
        return libroRepository.findByDisponibilidad(disponibilidad);
    }

    @Override
    public List<LibroEntity> buscarPorNombre(String nombreLibro) {
        return libroRepository.findByNombreLibro(nombreLibro);
    }

    @Override
    public LibroEntity buscarPorId(Long idLibro) {
        return libroRepository.findById(idLibro)
                .orElseThrow(() -> new RecursoNoEncontrado("Libro no encontrado"));
    }

    @Override
    @Transactional
        public LibroEntity prestarLibro(Long idLibro) {

        LibroEntity libro = libroRepository.findById(idLibro).
                orElseThrow(()-> new RecursoNoEncontrado("¡El libro con id "+idLibro+" no se ha encontrado!"));


        if(libro.getStock()>0){
            libro.setStock(libro.getStock()-1);
            if(libro.getStock()==0){
                libro.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);
            }
            return libroRepository.save(libro);
        }
        throw new ReglaNegocio("¡Sin Unidades Disponibles!");
    }

    @Override
    @Transactional
    public LibroEntity actualizarLibro(Long idLibro, LibroEntity libro) {
        LibroEntity libroLocal = libroRepository.findById(idLibro).
                orElseThrow(()-> new RecursoNoEncontrado("¡Libro no encontrado!"));

        libroLocal.setNombreLibro(libro.getNombreLibro());
        libroLocal.setNombreAutor(libro.getNombreAutor());
        libroLocal.setCategoriaLibro(libro.getCategoriaLibro());

        if(!libroLocal.getCodigo().equals(libro.getCodigo())){
            if(libroRepository.findByCodigo(libro.getCodigo()).isPresent()){
                throw new RecursoDuplicado("¡El codigo "+libro.getCodigo()+" ya se encuentra en uso!");
            }
            libroLocal.setCodigo(libro.getCodigo());
        }


        return libroRepository.save(libroLocal);
    }

    @Override
    @Transactional
    public LibroEntity devolverLibro(Long idLibro) {

        LibroEntity libro = libroRepository.findById(idLibro).
        orElseThrow(()-> new RecursoNoEncontrado("Error al devolver:  ¡El libro no se encuentra en la base de datos!"));

        libro.setStock(libro.getStock()+1);
        libro.setDisponibilidad(Disponibilidad.DISPONIBLE);
        return libro;
    }


}
