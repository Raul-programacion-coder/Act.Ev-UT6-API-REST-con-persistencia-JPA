package com.biblioteca.trabajo.controller;

import com.biblioteca.trabajo.model.autor;
import com.biblioteca.trabajo.service.autorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/autores")

public class autorController {

    private final autorService autorService;

    public autorController(autorService autorService){
        this.autorService = autorService;
    }

    @GetMapping
    public List<autor> getAll() {
        return autorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<autor> getById(@PathVariable Long id) {
        return autorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public autor create(@Valid @RequestBody autor autor) {
        return autorService.save(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<autor> update(
            @PathVariable Long id,@Valid @RequestBody autor datos) {

        return autorService.update(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
