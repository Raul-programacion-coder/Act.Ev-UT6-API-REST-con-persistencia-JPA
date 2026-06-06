package com.biblioteca.trabajo.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libros")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")

    @Size(
            min = 2,
            max = 100,
            message = "El título debe tener entre 2 y 100 caracteres"
    )
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @ToString.Exclude
    private autor autor;


    @ManyToMany
    @JoinTable(
            name = "libro_categoria",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )

    private Set<categoria> categorias = new HashSet<>();
}