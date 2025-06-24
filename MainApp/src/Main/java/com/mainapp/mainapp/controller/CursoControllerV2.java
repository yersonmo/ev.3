package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Curso;
import com.mainapp.mainapp.service.CursoService;
import com.mainapp.mainapp.assemblers.CursoModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable Integer id) {
        Curso curso = cursoService.findById(id);
        return assembler.toModel(curso);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso) {
        Curso newCurso = cursoService.save(curso);
        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(newCurso.getId())).toUri())
                .body(assembler.toModel(newCurso));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso updatedCurso = cursoService.save(curso);
        return ResponseEntity.ok(assembler.toModel(updatedCurso));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable Integer id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

