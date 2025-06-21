package com.mainapp.mainapp.entity;

import jakarta.persistence.*;
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

    private String nombre;
    private String apellido;
    private Integer edad;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Curso> cursos; // Relación opcional: un profesor dicta muchos cursos
}
