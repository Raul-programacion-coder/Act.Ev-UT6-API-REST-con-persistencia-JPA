package com.biblioteca.trabajo.repository;

import com.biblioteca.trabajo.model.libro;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface libroRepository extends JpaRepository<libro, Long> {
    List<libro> findByAutorId(Long autorId);


    List<libro> findByTituloContainingIgnoreCase(
            String titulo,
            Sort sort
    );

    @Query("""
        SELECT COUNT(l)
        FROM libro l
        WHERE l.autor.id = :id
        """)
    Long contarLibrosPorAutor(@Param("id") Long id);
}
