package com.mainapp.mainapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del profesor es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido del profesor es obligatorio")
    private String apellido;

    @NotNull(message = "La edad del profesor es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18")
    @Max(value = 80, message = "La edad máxima es 80")
    private Integer edad;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Curso> cursos; // Relación opcional si has definido curso.profesor
}
