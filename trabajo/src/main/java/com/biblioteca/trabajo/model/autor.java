package com.biblioteca.trabajo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "autores")

public class autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El nombre del autor es obligatorio")

    @Size(
            min = 2,
            max = 50,
            message = "El nombre debe tener entre 2 y 50 caracteres"
    )
    private String nombre;


    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    @ToString.Exclude
    private List<libro> libroList = new ArrayList<>();

}
