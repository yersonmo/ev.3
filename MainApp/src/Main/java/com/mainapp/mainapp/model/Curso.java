package com.mainapp.mainapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Integer duracion;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Alumno> alumnos;  // Relación uno a muchos (opcional, pero recomendable)
}
