package com.mainapp.mainapp.assembler;

import com.mainapp.mainapp.controller.ProfesorController;
import com.mainapp.mainapp.entity.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {

    @Override
    public EntityModel<Profesor> toModel(Profesor profesor) {
        return EntityModel.of(profesor,
                linkTo(methodOn(ProfesorController.class).getProfesorById(profesor.getId())).withSelfRel(),
                linkTo(methodOn(ProfesorController.class).getAllProfesores()).withRel("profesores"));
    }
}

