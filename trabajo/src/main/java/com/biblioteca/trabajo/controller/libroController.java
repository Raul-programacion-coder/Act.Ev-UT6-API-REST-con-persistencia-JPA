package com.biblioteca.trabajo.controller;

import com.biblioteca.trabajo.model.libro;
import com.biblioteca.trabajo.service.libroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class libroController {

    private final libroService libroService;

    public libroController(libroService libroService) {
        this.libroService = libroService;
    }


    @GetMapping
    public List<libro> getAll() {
        return libroService.listAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<libro> getById(@PathVariable Long id) {

        return libroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public libro create(@Valid @RequestBody libro libro) {
        return libroService.save(libro);
    }


    @PutMapping("/{id}")
    public ResponseEntity<libro> update(
            @PathVariable Long id,
            @Valid @RequestBody libro datos) {

        return libroService.update(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        libroService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<libro>> buscar(

            @RequestParam(required = false)
            String titulo,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String order

    ) {

        Sort.Direction dir = order.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Sort sort = Sort.by(dir, sortBy);

        return ResponseEntity.ok(
                libroService.buscar(titulo, sort)
        );
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<List<libro>> getByAutor(

            @PathVariable Long id

    ) {

        return ResponseEntity.ok(
                libroService.findByAutorId(id)
        );
    }

    @GetMapping("/autor/{id}/count")
    public ResponseEntity<Long> contarPorAutor(

            @PathVariable Long id

    ) {

        return ResponseEntity.ok(
                libroService.contarLibrosPorAutor(id)
        );
    }


}