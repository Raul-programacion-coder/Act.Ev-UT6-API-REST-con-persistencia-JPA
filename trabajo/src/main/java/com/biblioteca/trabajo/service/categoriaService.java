package com.biblioteca.trabajo.service;

import com.biblioteca.trabajo.model.categoria;
import com.biblioteca.trabajo.repository.categoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class categoriaService {

    private final categoriaRepository categoriaRepository;

    public categoriaService(categoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }


    public List<categoria> listAll() {
        return categoriaRepository.findAll();
    }


    public Optional<categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }


    public categoria save(categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    public Optional<categoria> update(Long id, categoria datos) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setTipo(datos.getTipo());
            return categoriaRepository.save(categoria);
        });
    }


    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
