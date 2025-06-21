package com.mainapp.mainapp.service;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.repository.AlumnoRepository;
import com.mainapp.mainapp.repository.CursoRepository;
import com.mainapp.mainapp.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudImpl implements Crud {

    @Autowired
    private AlumnoRepository alumnoRepo;

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private CursoRepository cursoRepo;

    // === ALUMNO ===
    @Override
    public String create(Alumno alumno) {
        alumnoRepo.save(alumno);
        return "Alumno creado: " + alumno.getNombre();
    }

    @Override
    public Alumno readAlumno(int id) {
        return alumnoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Alumno> readAllAlumnos() {
        return alumnoRepo.findAll();
    }

    @Override
    public String updateAlumno(int id, Alumno alumno) {
        if (alumnoRepo.existsById(id)) {
            alumno.setId(id);
            alumnoRepo.save(alumno);
            return "Alumno actualizado: " + alumno.getNombre();
        } else {
            return "Alumno no encontrado con ID: " + id;
        }
    }

    @Override
    public String deleteAlumno(int id) {
        if (alumnoRepo.existsById(id)) {
            alumnoRepo.deleteById(id);
            return "Alumno eliminado con ID: " + id;
        } else {
            return "Alumno no encontrado con ID: " + id;
        }
    }

    // === PROFESOR ===
    @Override
    public String create(Profesor profesor) {
        profesorRepo.save(profesor);
        return "Profesor creado: " + profesor.getNombre();
    }

    @Override
    public Profesor readProfesor(int id) {
        return profesorRepo.findById(id).orElse(null);
    }

    @Override
    public List<Profesor> readAllProfesores() {
        return profesorRepo.findAll();
    }

    @Override
    public String updateProfesor(int id, Profesor profesor) {
        if (profesorRepo.existsById(id)) {
            profesor.setId(id);
            profesorRepo.save(profesor);
            return "Profesor actualizado: " + profesor.getNombre();
        } else {
            return "Profesor no encontrado con ID: " + id;
        }
    }

    @Override
    public String deleteProfesor(int id) {
        if (profesorRepo.existsById(id)) {
            profesorRepo.deleteById(id);
            return "Profesor eliminado con ID: " + id;
        } else {
            return "Profesor no encontrado con ID: " + id;
        }
    }

    // === CURSO ===
    @Override
    public String create(Curso curso) {
        cursoRepo.save(curso);
        return "Curso creado: " + curso.getNombre();
    }

    @Override
    public Curso readCurso(int id) {
        return cursoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Curso> readAllCursos() {
        return cursoRepo.findAll();
    }

    @Override
    public String updateCurso(int id, Curso curso) {
        if (cursoRepo.existsById(id)) {
            curso.setId(id);
            cursoRepo.save(curso);
            return "Curso actualizado: " + curso.getNombre();
        } else {
            return "Curso no encontrado con ID: " + id;
        }
    }

    @Override
    public String deleteCurso(int id) {
        if (cursoRepo.existsById(id)) {
            cursoRepo.deleteById(id);
            return "Curso eliminado con ID: " + id;
        } else {
            return "Curso no encontrado con ID: " + id;
        }
    }
} 


