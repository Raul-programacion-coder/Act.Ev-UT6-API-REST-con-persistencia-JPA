package com.biblioteca.trabajo.controller;

import com.biblioteca.trabajo.model.categoria;
import com.biblioteca.trabajo.service.categoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")

public class categoriaController {

    private final categoriaService categoriaService;

    public categoriaController(categoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<categoria> getAll() {
        return categoriaService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<categoria> getById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public categoria create(@Valid @RequestBody categoria categoria) {
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<categoria> update(@PathVariable Long id,@Valid @RequestBody categoria datos) {
        return categoriaService.update(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
