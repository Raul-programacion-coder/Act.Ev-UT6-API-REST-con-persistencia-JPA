package com.biblioteca.trabajo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo de categoría es obligatorio")

    @Size(
            min = 3,
            max = 30,
            message = "La categoría debe tener entre 3 y 30 caracteres"
    )
    private String tipo;


    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    @ToString.Exclude
    private Set<libro> libros = new HashSet<>();


    public categoria(String tipo) {
        this.tipo = tipo;
    }

}
