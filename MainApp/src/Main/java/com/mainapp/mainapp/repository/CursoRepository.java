package com.mainapp.mainapp.repository;
import com.mainapp.mainapp.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CursoRepository extends JpaRepository<Curso, Integer> {
}