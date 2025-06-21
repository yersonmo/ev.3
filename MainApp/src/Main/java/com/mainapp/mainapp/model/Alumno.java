package com.mainapp.mainapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;  // <-- Esto asume que tienes una entidad llamada Curso
}
