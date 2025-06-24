package com.mainapp.mainapp;

import com.mainapp.mainapp.entity.Alumno;
import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.repository.AlumnoRepository;
import com.mainapp.mainapp.repository.CursoRepository;
import com.mainapp.mainapp.repository.ProfesorRepository;

import net.datafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear profesores
        for (int i = 0; i < 5; i++) {
            Profesor profesor = new Profesor();
            profesor.setNombre(faker.name().firstName());
            profesor.setApellido(faker.name().lastName());
            profesor.setEdad(faker.number().numberBetween(30, 65));
            profesorRepository.save(profesor);
        }

        List<Profesor> profesores = profesorRepository.findAll();

        // Crear cursos
        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();
            curso.setNombre(faker.educator().course());
            curso.setDescripcion(faker.lorem().sentence(5));
            curso.setDuracion(faker.number().numberBetween(20, 80));
            curso.setProfesor(profesores.get(random.nextInt(profesores.size())));
            cursoRepository.save(curso);
        }

        List<Curso> cursos = cursoRepository.findAll();

        // Crear alumnos
        for (int i = 0; i < 30; i++) {
            Alumno alumno = new Alumno();
            alumno.setNombre(faker.name().firstName());
            alumno.setApellido(faker.name().lastName());
            alumno.setEdad(faker.number().numberBetween(18, 30));
            alumno.setCurso(cursos.get(random.nextInt(cursos.size())));
            alumnoRepository.save(alumno);
        }
    }
}


