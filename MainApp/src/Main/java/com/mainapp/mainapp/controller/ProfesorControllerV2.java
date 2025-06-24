package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.service.ProfesorService;
import com.mainapp.mainapp.assemblers.ProfesorModelAssembler;

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
@RequestMapping("/api/v2/profesores")
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Profesor>> getAllProfesores() {
        List<EntityModel<Profesor>> profesores = profesorService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores,
                linkTo(methodOn(ProfesorControllerV2.class).getAllProfesores()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Profesor> getProfesorById(@PathVariable Integer id) {
        Profesor profesor = profesorService.findById(id);
        return assembler.toModel(profesor);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Profesor>> createProfesor(@RequestBody Profesor profesor) {
        Profesor newProfesor = profesorService.save(profesor);
        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class).getProfesorById(newProfesor.getId())).toUri())
                .body(assembler.toModel(newProfesor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Profesor>> updateProfesor(@PathVariable Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor updatedProfesor = profesorService.save(profesor);
        return ResponseEntity.ok(assembler.toModel(updatedProfesor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProfesor(@PathVariable Integer id) {
        profesorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
