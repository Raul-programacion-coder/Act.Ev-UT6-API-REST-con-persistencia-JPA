package com.biblioteca.trabajo.service;

import com.biblioteca.trabajo.model.autor;
import com.biblioteca.trabajo.repository.autorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class autorService {

    private final autorRepository autorRepository;

    public autorService(autorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public List<autor> listAll() {
        return autorRepository.findAll();
    }

    public Optional<autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    public autor save(autor autor) {
        return autorRepository.save(autor);
    }


    public Optional<autor> update(Long id, autor datos) {
        return autorRepository.findById(id).map(autor -> {
            autor.setNombre(datos.getNombre());
            return autorRepository.save(autor);
        });
    }


    public void delete(Long id) {
        autorRepository.deleteById(id);
    }
}
