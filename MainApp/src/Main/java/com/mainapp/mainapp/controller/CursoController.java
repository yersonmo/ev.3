package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.assembler.CursoModelAssembler;
import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los cursos")
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoController.class).getAllCursos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por ID")
    public EntityModel<Curso> getCursoById(@PathVariable Integer id) {
        return assembler.toModel(cursoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    public EntityModel<Curso> createCurso(@RequestBody Curso curso) {
        return assembler.toModel(cursoService.save(curso));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un curso")
    public EntityModel<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        curso.setId(id);
        return assembler.toModel(cursoService.save(curso));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso")
    public void deleteCurso(@PathVariable Integer id) {
        cursoService.deleteById(id);
    }
}
