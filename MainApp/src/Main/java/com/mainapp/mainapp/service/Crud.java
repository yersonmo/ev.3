package com.mainapp.mainapp.service;
import org.springframework.stereotype.Repository;
import com.mainapp.mainapp.entity.Alumno;



@Repository
public interface Crud {

    Object read(int id);

    void update(int id, Object object);

    void delete(int id);

    String create(Alumno alumnos);
}
