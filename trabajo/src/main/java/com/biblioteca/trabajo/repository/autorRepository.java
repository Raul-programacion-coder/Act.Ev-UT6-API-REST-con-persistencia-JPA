package com.biblioteca.trabajo.repository;

import com.biblioteca.trabajo.model.autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface autorRepository extends JpaRepository<autor, Long> {
}
