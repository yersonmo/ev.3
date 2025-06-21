package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainAppController {

    @Autowired
    private Crud crud;

    // === ALUMNO ===
    @PostMapping("/alumno")
    public String crearAlumno(@RequestBody Alumno alumno) {
        return crud.create(alumno);
    }

    @GetMapping("/alumno")
    public List<Alumno> listarAlumnos() {
        return crud.readAllAlumnos();
    }

    @GetMapping("/alumno/{id}")
    public Alumno obtenerAlumno(@PathVariable int id) {
        return crud.readAlumno(id);
    }

    @PutMapping("/alumno/{id}")
    public String actualizarAlumno(@PathVariable int id, @RequestBody Alumno alumno) {
        return crud.updateAlumno(id, alumno);
    }

    @DeleteMapping("/alumno/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        return crud.deleteAlumno(id);
    }

    // === PROFESOR ===
    @PostMapping("/profesor")
    public String crearProfesor(@RequestBody Profesor profesor) {
        return crud.create(profesor);
    }

    @GetMapping("/profesor")
    public List<Profesor> listarProfesores() {
        return crud.readAllProfesores();
    }

    @GetMapping("/profesor/{id}")
    public Profesor obtenerProfesor(@PathVariable int id) {
        return crud.readProfesor(id);
    }

    @PutMapping("/profesor/{id}")
    public String actualizarProfesor(@PathVariable int id, @RequestBody Profesor profesor) {
        return crud.updateProfesor(id, profesor);
    }

    @DeleteMapping("/profesor/{id}")
    public String eliminarProfesor(@PathVariable int id) {
        return crud.deleteProfesor(id);
    }

    // === CURSO ===
    @PostMapping("/curso")
    public String crearCurso(@RequestBody Curso curso) {
        return crud.create(curso);
    }

    @GetMapping("/curso")
    public List<Curso> listarCursos() {
        return crud.readAllCursos();
    }

    @GetMapping("/curso/{id}")
    public Curso obtenerCurso(@PathVariable int id) {
        return crud.readCurso(id);
    }

    @PutMapping("/curso/{id}")
    public String actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        return crud.updateCurso(id, curso);
    }

    @DeleteMapping("/curso/{id}")
    public String eliminarCurso(@PathVariable int id) {
        return crud.deleteCurso(id);
    }
} 