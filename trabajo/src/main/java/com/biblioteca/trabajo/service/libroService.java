package com.biblioteca.trabajo.service;


import com.biblioteca.trabajo.model.libro;
import com.biblioteca.trabajo.repository.libroRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

    @Service
    public class libroService {

        private final libroRepository libroRepository;

        public libroService(libroRepository libroRepository) {
            this.libroRepository = libroRepository;
        }


        public List<libro> listAll() {
            return libroRepository.findAll();
        }


        public Optional<libro> findById(Long id) {
            return libroRepository.findById(id);
        }


        public libro save(libro libro) {
            return libroRepository.save(libro);
        }


        public Optional<libro> update(Long id, libro datos) {
            return libroRepository.findById(id).map(libro -> {
                libro.setTitulo(datos.getTitulo());
                return libroRepository.save(libro);
            });
        }



        public void delete(Long id) {
            libroRepository.deleteById(id);
        }


        public List<libro> buscar(String titulo, Sort sort) {

            String t = (titulo == null ? "" : titulo);

            return libroRepository
                    .findByTituloContainingIgnoreCase(t, sort);
        }

        public List<libro> findByAutorId(Long autorId) {

            return libroRepository.findByAutorId(autorId);

        }

        public Long contarLibrosPorAutor(Long id) {

            return libroRepository.contarLibrosPorAutor(id);

        }
    }
