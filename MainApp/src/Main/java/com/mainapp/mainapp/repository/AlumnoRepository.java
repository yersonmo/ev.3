package com.mainapp.mainapp.repository;

import com.mainapp.mainapp.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findByCursoId(Integer cursoId);
}