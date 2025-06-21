package com.mainapp.mainapp.assembler;

import com.mainapp.mainapp.controller.CursoController;
import com.mainapp.mainapp.entity.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"));
    }
}                                  