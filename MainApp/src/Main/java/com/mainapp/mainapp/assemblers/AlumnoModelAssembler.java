package com.mainapp.mainapp.assembler;

import com.mainapp.mainapp.controller.AlumnoController;
import com.mainapp.mainapp.entity.Alumno;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AlumnoModelAssembler implements RepresentationModelAssembler<Alumno, EntityModel<Alumno>> {

    @Override
    public EntityModel<Alumno> toModel(Alumno alumno) {
        return EntityModel.of(alumno,
                linkTo(methodOn(AlumnoController.class).getAlumnoById(alumno.getId())).withSelfRel(),
                linkTo(methodOn(AlumnoController.class).getAllAlumnos()).withRel("alumnos"));
    }
}
