package com.mainapp.mainapp.repository;
import com.mainapp.mainapp.entity.Profesor;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}