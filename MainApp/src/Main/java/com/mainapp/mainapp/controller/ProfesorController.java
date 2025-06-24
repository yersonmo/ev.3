package com.mainapp.mainapp.controller;

import com.mainapp.mainapp.entity.Profesor;
import com.mainapp.mainapp.service.ProfesorService;
import com.mainapp.mainapp.assembler.ProfesorModelAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/profesores")
@Tag(name = "Profesores", description = "Operaciones relacionadas con los profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler assembler;

    @GetMapping
    @Operation(
        summary = "Obtener todos los profesores",
        description = "Devuelve una lista completa de los profesores registrados"
    )
    public CollectionModel<EntityModel<Profesor>> getAllProfesores() {
        List<EntityModel<Profesor>> profesores = profesorService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores,
                linkTo(methodOn(ProfesorController.class).getAllProfesores()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener un profesor por ID",
        description = "Devuelve los datos de un profesor específico utilizando su ID"
    )
    public EntityModel<Profesor> getProfesorById(@PathVariable Integer id) {
        return assembler.toModel(profesorService.findById(id));
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo profesor",
        description = "Registra un nuevo profesor en el sistema"
    )
    public EntityModel<Profesor> createProfesor(@Valid @RequestBody Profesor profesor) {
        Profesor saved = profesorService.save(profesor);
        return assembler.toModel(saved);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un profesor",
        description = "Actualiza los datos de un profesor ya registrado"
    )
    public EntityModel<Profesor> updateProfesor(@PathVariable Integer id, @Valid @RequestBody Profesor profesor) {
        profesor.setId(id);
        return assembler.toModel(profesorService.save(profesor));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un profesor",
        description = "Elimina un profesor del sistema utilizando su ID"
    )
    public void deleteProfesor(@PathVariable Integer id) {
        profesorService.deleteById(id);
    }
}
